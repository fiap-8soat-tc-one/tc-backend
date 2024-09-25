package com.fiap.tc.application.usecases.category;

import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCategoryUseCase {

    private final CategoryGatewaySpec categoryGateway;

    public DeleteCategoryUseCase(CategoryGatewaySpec categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void delete(UUID id) {
        this.categoryGateway.delete(id);
    }
}


