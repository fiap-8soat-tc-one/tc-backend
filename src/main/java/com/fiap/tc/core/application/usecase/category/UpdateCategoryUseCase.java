package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.usecase.ports.in.category.UpdateCategoryInputPort;
import com.fiap.tc.core.application.usecase.ports.out.category.UpdateCategoryOutputPort;
import com.fiap.tc.core.domain.entities.Category;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCategoryUseCase implements UpdateCategoryInputPort {

    private final UpdateCategoryOutputPort updateCategoryOutputPort;

    public UpdateCategoryUseCase(UpdateCategoryOutputPort updateCategoryOutputPort) {
        this.updateCategoryOutputPort = updateCategoryOutputPort;
    }

    @Override
    public Category update(UUID idCategory, String name, String description) {
        return updateCategoryOutputPort.update(idCategory, name, description);
    }
}
