package com.fiap.tc.application.usecase.category;

import com.fiap.tc.application.port.in.category.LoadCategoryInputPort;
import com.fiap.tc.application.port.out.category.LoadCategoryOutputPort;
import com.fiap.tc.core.domain.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class LoadCategoryUseCase implements LoadCategoryInputPort {

    private final LoadCategoryOutputPort loadCategoryOutputPort;

    public LoadCategoryUseCase(LoadCategoryOutputPort loadCategoryOutputPort) {
        this.loadCategoryOutputPort = loadCategoryOutputPort;
    }


    @Override
    public Category load(UUID uuid) {
        return loadCategoryOutputPort.load(uuid);
    }
}


