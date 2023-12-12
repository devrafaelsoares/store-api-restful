package br.devrafaelsoares.storeapirestful.domain.validations.validators;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankOrNullValidator implements ConstraintValidator<NotBlankOrNull, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return false;

        return !value.trim().isEmpty();
    }
}
