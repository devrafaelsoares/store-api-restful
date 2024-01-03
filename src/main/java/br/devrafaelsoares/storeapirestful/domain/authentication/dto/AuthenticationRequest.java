package br.devrafaelsoares.storeapirestful.domain.authentication.dto;


import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;

public record AuthenticationRequest(
        @NotBlankOrNull(message = "Campo obrigatório")
        String username,
        @NotBlankOrNull(message = "Campo obrigatório")
        String password
) {}
