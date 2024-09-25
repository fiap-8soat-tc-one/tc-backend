package com.fiap.tc.infrastructure.gateways;

import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import com.fiap.tc.domain.entities.Category;
import com.fiap.tc.domain.exceptions.BadRequestException;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.infrastructure.persistence.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants.CATEGORY_MAPPER;
import static java.lang.String.format;

@Service
public class CategoryGateway implements CategoryGatewaySpec {

    private final CategoryRepository repository;

    public CategoryGateway(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category load(UUID id) {
        var categoryEntityOptional = repository.findByUuid(id);
        if (categoryEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Category with uuid %s not found!", id));
        }
        return CATEGORY_MAPPER.fromEntity(categoryEntityOptional.get());
    }

    @Override
    public Category register(String name, String description) {
        var categoryEntityOptional = repository.findByName(name);

        if (categoryEntityOptional.isPresent()) {
            var categoryEntity = categoryEntityOptional.get();
            categoryEntity.setDescription(description);
            categoryEntity.getAudit().setUpdatedDate(LocalDateTime.now());
            return CATEGORY_MAPPER.fromEntity(repository.save(categoryEntity));

        }
        return CATEGORY_MAPPER.fromEntity(repository.save(buildCategoryEntity(name, description)));
    }

    @Override
    public Category update(UUID id, String name, String description) {
        var categoryEntity = validate(id, name);
        return updateCategory(name, description, categoryEntity);
    }

    @Override
    public Page<Category> list(Pageable pageable) {
        Page<CategoryEntity> categories = repository.findAll(pageable);
        return categories.map(CATEGORY_MAPPER::fromEntity);
    }

    @Override
    public void delete(UUID idCategory) {
        var categoryEntityOptional = repository.findByUuid(idCategory);
        categoryEntityOptional.ifPresent(repository::delete);
    }

    private CategoryEntity buildCategoryEntity(String name, String description) {
        var newCategory = new CategoryEntity();
        newCategory.setName(name);
        newCategory.setDescription(description);
        newCategory.setUuid(UUID.randomUUID());
        newCategory.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());
        return newCategory;
    }

    private CategoryEntity validate(UUID idCategory, String name) {
        var categoryEntityOptional = repository.findByUuid(idCategory);

        if (categoryEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Category with id %s not found!", idCategory));
        }

        var categoryExpectedEntityOptional = repository.findByName(name);

        var categoryEntity = categoryEntityOptional.get();
        var notSameCategoryName = !(categoryEntity.getName().equals(name));

        var hasInvalidCategoryName = notSameCategoryName && categoryExpectedEntityOptional.isPresent();

        if (hasInvalidCategoryName) {
            throw new BadRequestException(format("Category with expected name '%s' already exists!", name));
        }

        return categoryEntityOptional.get();
    }

    private Category updateCategory(String name, String description, CategoryEntity categoryEntity) {
        categoryEntity.setName(name);
        categoryEntity.setDescription(description);
        categoryEntity.getAudit().setUpdatedDate(LocalDateTime.now());

        return CATEGORY_MAPPER.fromEntity(repository.save(categoryEntity));
    }
}
