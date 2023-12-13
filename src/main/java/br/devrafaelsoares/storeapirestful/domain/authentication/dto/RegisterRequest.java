package br.devrafaelsoares.storeapirestful.domain.authentication.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;
import br.devrafaelsoares.storeapirestful.domain.validations.constraints.RoleValid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest(
        @NotBlank(message = "Campo obrigatório")
        String username,
        @NotBlank(message = "Campo obrigatório")
        @Length(min = 8, message = "A senha deve ter no mínimo oito caracteres")
        String password,
        @RoleValid(message = "Permissão inválida")
        @NotBlankOrNull(message = "Campo obrigatório")
        String role
) {}
