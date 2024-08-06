package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.CategoryRepository;
import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.port.out.category.DeleteCategoryOutputPort;
import com.fiap.tc.core.port.out.category.ListCategoriesOutputPort;
import com.fiap.tc.core.port.out.category.LoadCategoryOutputPort;
import com.fiap.tc.core.port.out.category.SaveCategoryOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.CATEGORY_MAPPER;
import static java.lang.String.format;

@Service
public class CategoryOutputAdapter implements SaveCategoryOutputPort, LoadCategoryOutputPort,
        ListCategoriesOutputPort, DeleteCategoryOutputPort {
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

}
