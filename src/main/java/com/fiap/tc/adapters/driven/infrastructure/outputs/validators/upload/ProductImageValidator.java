package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.upload;

import java.util.List;

public interface ProductImageValidator {
    void execute(ProductImageValidatorWrapper wrapper, List<String> errors);
}
