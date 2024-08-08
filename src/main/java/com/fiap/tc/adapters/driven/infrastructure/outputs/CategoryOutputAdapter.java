package com.fiap.tc.adapters.driven.infrastructure.outputs;

import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.CategoryRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.core.application.port.out.category.*;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperConstants.CATEGORY_MAPPER;
import static java.lang.String.format;

@Service
public class CategoryOutputAdapter implements SaveCategoryOutputPort, LoadCategoryOutputPort,
        ListCategoriesOutputPort, DeleteCategoryOutputPort, UpdateCategoryOutputPort {
    private final CategoryRepository repository;

    public CategoryOutputAdapter(CategoryRepository categoryPersistenceRepository) {
        this.repository = categoryPersistenceRepository;
    }

    @Override
    public void delete(UUID uuid) {
        var categoryEntityOptional = repository.findByUuid(uuid);
        categoryEntityOptional.ifPresent(repository::delete);
    }

    @Override
    public Page<Category> list(Pageable pageable) {
        Page<CategoryEntity> categories = repository.findAll(pageable);
        return categories.map(CATEGORY_MAPPER::fromEntity);
    }

    @Override
    public Category load(UUID uuid) {
        var categoryEntityOptional = repository.findByUuid(uuid);
        if (categoryEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Category with uuid %s not found!", uuid));
        }
        return CATEGORY_MAPPER.fromEntity(categoryEntityOptional.get());
    }

    @Override
    public Category saveOrUpdate(String name, String description, boolean active) {
        var categoryEntityOptional = repository.findByName(name);
        if (categoryEntityOptional.isPresent()) {
            var categoryEntity = categoryEntityOptional.get();
            categoryEntity.setName(name);
            categoryEntity.setDescription(description);
            categoryEntity.getAudit().setActive(active);
            categoryEntity.getAudit().setUpdatedDate(LocalDateTime.now());
            return CATEGORY_MAPPER.fromEntity(repository.save(categoryEntity));

        }

        return CATEGORY_MAPPER.fromEntity(repository.save(buildCategoryEntity(name, description, active)));

    }

    private CategoryEntity buildCategoryEntity(String name, String description, boolean active) {
        var newCategory = new CategoryEntity();
        newCategory.setName(name);
        newCategory.setDescription(description);
        newCategory.setUuid(UUID.randomUUID());
        newCategory.setAudit(Audit.builder().active(active).registerDate(LocalDateTime.now()).build());
        return newCategory;
    }

    @Override
    public Category update(UUID idCategory, String name, String description, boolean active) {
        var categoryEntity = validate(idCategory, name);
        return updateCategory(name, description, active, categoryEntity);
    }

    private CategoryEntity validate(UUID idCategory, String name) {
        var categoryEntityOptional = repository.findByUuid(idCategory);

        if (categoryEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Category with id %s not found!", idCategory));
        }

        var categoryExpectedEntityOptional = repository.findByName(name);

        if (categoryExpectedEntityOptional.isPresent()) {
            throw new BadRequestException(format("Category with expected name %s already exists!", name));
        }
        return categoryEntityOptional.get();
    }

    private Category updateCategory(String name, String description, boolean active, CategoryEntity categoryEntity) {
        categoryEntity.setName(name);
        categoryEntity.setDescription(description);
        categoryEntity.getAudit().setUpdatedDate(LocalDateTime.now());
        categoryEntity.getAudit().setActive(active);

        return CATEGORY_MAPPER.fromEntity(repository.save(categoryEntity));
    }
}
