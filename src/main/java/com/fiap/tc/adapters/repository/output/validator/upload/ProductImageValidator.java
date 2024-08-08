package com.fiap.tc.adapters.repository.output.validator.upload;

import java.util.List;

public interface ProductImageValidator {
    void execute(ProductImageValidatorWrapper wrapper, List<String> errors);
}
