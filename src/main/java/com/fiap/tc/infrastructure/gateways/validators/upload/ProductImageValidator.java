package com.fiap.tc.infrastructure.gateways.validators.upload;

import java.util.List;

public interface ProductImageValidator {
    void execute(ProductImageValidatorWrapper wrapper, List<String> errors);
}
