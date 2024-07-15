package com.fiap.tc.usecase;

import com.fiap.tc.domains.products.Category;
import com.fiap.tc.dto.CategoryDto;
import com.fiap.tc.exception.NotFoundException;
import com.fiap.tc.mappers.base.MapperConstants;
import com.fiap.tc.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class CategoryUseCase {
    private final CategoryRepository repository;

    public CategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public Page<CategoryDto> list(Pageable pageable) {
        Page<Category> categories =  repository.findAll(pageable);
        return categories.map(MapperConstants.CATEGORY_MAPPER::fromEntity);
    }
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = repository.findByNameOrDescription(categoryDto.getName(), categoryDto.getDescription());
        if (isNull(category)) {
            var newCategory = MapperConstants.CATEGORY_MAPPER.toEntity(categoryDto);
            newCategory.setUuid(UUID.randomUUID());
            newCategory.setActive(isNull(categoryDto.getActive()));
            return MapperConstants.CATEGORY_MAPPER.fromEntity(repository.save(newCategory));

        }
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setActive(isNull(categoryDto.getActive()));
        return MapperConstants.CATEGORY_MAPPER.fromEntity(repository.save(category));
    }

    public CategoryDto find(UUID uuid) {
        var category = repository.findByUuid(uuid);
        if(isNull(category)) {
            throw new NotFoundException(format("Category with uuid %s not found!", uuid));
        }
        return MapperConstants.CATEGORY_MAPPER.fromEntity(category);

    }
    public void delete(UUID uuid) {
        var category = repository.findByUuid(uuid);
        if (nonNull(category)) {
            repository.delete(category);
        }

    }
}
