package br.devrafaelsoares.storeapirestful.domain.validations.constraints;

import br.devrafaelsoares.storeapirestful.domain.validations.validators.NotBlankOrNullValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlankOrNullValidator.class)
public @interface NotBlankOrNull {
    String message() default "Campo está vazio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
