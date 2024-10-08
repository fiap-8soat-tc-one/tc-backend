package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.ports.in.category.RegisterCategoryInputPort;
import com.fiap.tc.core.application.ports.out.category.RegisterCategoryOutputPort;
import com.fiap.tc.core.domain.entities.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterCategoryUseCase implements RegisterCategoryInputPort {

    private final RegisterCategoryOutputPort saveCategoryOutputPort;

    public RegisterCategoryUseCase(RegisterCategoryOutputPort saveCategoryOutputPort) {
        this.saveCategoryOutputPort = saveCategoryOutputPort;
    }

    @Override
    public Category register(String name, String description) {
        return saveCategoryOutputPort.saveOrUpdate(name, description);
    }
}


