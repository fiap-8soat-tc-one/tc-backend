package com.fiap.tc.core.domain.requests.validators.impl;

import com.fiap.tc.core.domain.requests.validators.Document;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentValidator implements ConstraintValidator<Document, String> {

    @Override
    public void initialize(Document constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(String document, ConstraintValidatorContext constraintValidatorContext) {
        if (document == null) {
            return false;
        }
        document = document.replaceAll("\\D", "");
        if (document.length() != 11) {
            return false;
        }
        return isValidDocument(document);
    }

    private boolean isValidDocument(String document) {
        try {
            String calculatedDigits = calculateDigits(document);
            String actualDigits = document.substring(document.length() - 2);
            return actualDigits.equals(calculatedDigits);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String calculateDigits(String document) {
        int sum1 = 0, sum2 = 0;
        int digit1, digit2, remainder;

        for (int i = 1; i < document.length() - 1; i++) {
            int digit = Integer.parseInt(document.substring(i - 1, i));
            sum1 += (11 - i) * digit;
            sum2 += (12 - i) * digit;
        }

        digit1 = calculateDigit(sum1);
        sum2 += 2 * digit1;
        digit2 = calculateDigit(sum2);

        return String.valueOf(digit1) + String.valueOf(digit2);
    }

    private int calculateDigit(int sum) {
        int remainder = sum % 11;
        if (remainder < 2) {
            return 0;
        } else {
            return 11 - remainder;
        }
    }

}
