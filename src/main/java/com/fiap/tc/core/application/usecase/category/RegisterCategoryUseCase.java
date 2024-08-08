package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.ports.in.category.RegisterCategoryInputPort;
import com.fiap.tc.core.domain.entities.Category;
import com.fiap.tc.adapters.driver.presentation.requests.CategoryRequest;
import com.fiap.tc.core.application.ports.out.category.SaveCategoryOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterCategoryUseCase implements RegisterCategoryInputPort {

    private final SaveCategoryOutputPort saveCategoryOutputPort;

    public RegisterCategoryUseCase(SaveCategoryOutputPort saveCategoryOutputPort) {
        this.saveCategoryOutputPort = saveCategoryOutputPort;
    }

    @Override
    public Category register(CategoryRequest categoryRequest) {
        return saveCategoryOutputPort.saveOrUpdate(categoryRequest.getName(), categoryRequest.getDescription(), categoryRequest.getActive());
    }
}


