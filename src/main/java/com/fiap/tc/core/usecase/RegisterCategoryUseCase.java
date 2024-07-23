package com.fiap.tc.core.usecase;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.model.CategoryRequest;
import com.fiap.tc.core.port.in.RegisterCategoryInputPort;
import com.fiap.tc.core.port.out.SaveCategoryOutputPort;
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
        return saveCategoryOutputPort.save(categoryRequest.getName(), categoryRequest.getDescription(), categoryRequest.getActive());
    }
}
