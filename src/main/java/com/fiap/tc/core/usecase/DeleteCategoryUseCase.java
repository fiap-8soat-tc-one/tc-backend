package com.fiap.tc.core.usecase;

import com.fiap.tc.core.port.in.DeleteCategoryInputPort;
import com.fiap.tc.core.port.out.DeleteCategoryOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DeleteCategoryUseCase implements DeleteCategoryInputPort {

    private final DeleteCategoryOutputPort deleteCategoryOutputPort;

    public DeleteCategoryUseCase(DeleteCategoryOutputPort deleteCategoryOutputPort) {
        this.deleteCategoryOutputPort = deleteCategoryOutputPort;
    }


    @Override
    public void delete(UUID uuid) {
        this.deleteCategoryOutputPort.delete(uuid);
    }
}


