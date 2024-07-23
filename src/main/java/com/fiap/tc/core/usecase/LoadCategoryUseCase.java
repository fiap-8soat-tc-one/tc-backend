package com.fiap.tc.core.usecase;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.port.in.LoadCategoryInputPort;
import com.fiap.tc.core.port.in.LoadCustomerInputPort;
import com.fiap.tc.core.port.out.LoadCategoryOutputPort;
import com.fiap.tc.core.port.out.LoadCustomerOutputPort;
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


