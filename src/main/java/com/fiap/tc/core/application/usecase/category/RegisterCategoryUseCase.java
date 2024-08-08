package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.port.in.category.RegisterCategoryInputPort;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import com.fiap.tc.core.application.port.out.category.SaveCategoryOutputPort;
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


