package com.fiap.tc.adapter.repository.output.validator.upload;

import java.util.List;

public interface ProductImageValidator {
    void execute(ProductImageValidatorWrapper wrapper, List<String> errors);
}
