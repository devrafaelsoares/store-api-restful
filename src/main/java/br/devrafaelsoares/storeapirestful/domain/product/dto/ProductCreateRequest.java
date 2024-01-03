package br.devrafaelsoares.storeapirestful.domain.product.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.Categories;
import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record ProductCreateRequest(
        @NotBlankOrNull(message = "Campo obrigatório")

        String name,

        String description,
        @Categories(message = "Categoria inválida ou não existe")
        @NotBlankOrNull(message = "Campo obrigatório")
        String category,
        @DecimalMin(value = "0.0", message = "Valor do produto deve ser no mínimo 0.0")
        @DecimalMax(value = "1000000.0", message = "Valor do produto deve ser no máximo 1000000.0")
        Double price
) {}
