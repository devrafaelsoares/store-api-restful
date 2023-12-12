package br.devrafaelsoares.storeapirestful.domain.category.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;
import lombok.Getter;

@Getter
public record CategoryUpdateRequest (
        @NotBlankOrNull(message = "Campo obrigatório")
        String name
) implements CategoryUpdate
{}

