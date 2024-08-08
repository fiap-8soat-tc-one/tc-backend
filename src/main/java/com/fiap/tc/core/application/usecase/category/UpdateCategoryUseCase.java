package com.fiap.tc.core.usecase.category;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import com.fiap.tc.core.port.in.category.UpdateCategoryInputPort;
import com.fiap.tc.core.port.out.category.UpdateCategoryOutputPort;
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
