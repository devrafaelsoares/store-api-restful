package br.devrafaelsoares.storeapirestful.domain.category.dto;


import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;

public record CategoryCreateRequest(
        @NotBlankOrNull(message = "Campo obrigatório")
        String name
) {}
