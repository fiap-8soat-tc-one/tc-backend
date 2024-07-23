package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.CategoryRepository;
import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.port.out.DeleteCategoryOutputPort;
import com.fiap.tc.core.port.out.ListCategoriesOutputPort;
import com.fiap.tc.core.port.out.LoadCategoryOutputPort;
import com.fiap.tc.core.port.out.SaveCategoryOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.CATEGORY_MAPPER;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
public class CategoryOutputAdapter implements SaveCategoryOutputPort, LoadCategoryOutputPort,
        ListCategoriesOutputPort, DeleteCategoryOutputPort {
    private final CategoryRepository categoryPersistenceRepository;

    public CategoryOutputAdapter(CategoryRepository categoryPersistenceRepository) {
        this.categoryPersistenceRepository = categoryPersistenceRepository;
    }

    @Override
    public void delete(UUID uuid) {
        var category = categoryPersistenceRepository.findByUuid(uuid);
        if (nonNull(category)) {
            categoryPersistenceRepository.delete(category);
        }
    }

    @Override
    public Page<Category> list(Pageable pageable) {
        Page<CategoryEntity> categories = categoryPersistenceRepository.findAll(pageable);
        return categories.map(CATEGORY_MAPPER::fromEntity);
    }

    @Override
    public Category load(UUID uuid) {
        var category = categoryPersistenceRepository.findByUuid(uuid);
        if (isNull(category)) {
            throw new NotFoundException(format("Category with uuid %s not found!", uuid));
        }
        return CATEGORY_MAPPER.fromEntity(category);
    }

    @Override
    public Category save(String name, String description, boolean active) {
        var categoryEntity = categoryPersistenceRepository.findByNameOrDescription(name, description);
        if (isNull(categoryEntity)) {
            return CATEGORY_MAPPER.fromEntity(categoryPersistenceRepository.save(buildCategoryEntity(name, description, active)));
        }
        categoryEntity.setName(name);
        categoryEntity.setDescription(description);
        categoryEntity.setActive(active);
        return CATEGORY_MAPPER.fromEntity(categoryPersistenceRepository.save(categoryEntity));
    }

    private CategoryEntity buildCategoryEntity(String name, String description, boolean active) {
        var newCategory = new CategoryEntity();
        newCategory.setName(name);
        newCategory.setDescription(description);
        newCategory.setUuid(UUID.randomUUID());
        newCategory.setActive(active);
        return newCategory;
    }

}
