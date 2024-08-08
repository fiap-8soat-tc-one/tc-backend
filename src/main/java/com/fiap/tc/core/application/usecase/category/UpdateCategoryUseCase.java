package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.port.in.category.UpdateCategoryInputPort;
import com.fiap.tc.core.application.port.out.category.UpdateCategoryOutputPort;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCategoryUseCase implements UpdateCategoryInputPort {

    private final UpdateCategoryOutputPort updateCategoryOutputPort;

    public UpdateCategoryUseCase(UpdateCategoryOutputPort updateCategoryOutputPort) {
        this.updateCategoryOutputPort = updateCategoryOutputPort;
    }

    @Override
    public Category update(UUID idCategory, CategoryRequest request) {
        return updateCategoryOutputPort.update(idCategory, request.getName(),
                request.getDescription(), request.getActive());
    }
}
