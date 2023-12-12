package br.devrafaelsoares.storeapirestful.domain.validations.validators;

import br.devrafaelsoares.storeapirestful.domain.user.Role;
import br.devrafaelsoares.storeapirestful.domain.validations.constraints.RoleValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RoleValidator implements ConstraintValidator<RoleValid, String> {

    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {

        if(role == null || role.isEmpty()) return true;

        return Role.getAllRoles().contains(role);
    }
}
