package br.devrafaelsoares.storeapirestful.domain.product.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.Categories;
import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductUpdateRequest implements ProductUpdate {
        @NotBlankOrNull(message = "Campo obrigatório")
        private String name;

        @NotBlankOrNull(message = "Campo obrigatório")
        private String description;

        @Categories(message = "Categoria inválida ou não existe")
        @NotBlankOrNull(message = "Campo obrigatório")
        private String category;

        @NotNull(message = "Campo obrigatório")
        @DecimalMin(value = "0.0", message = "Valor do produto deve ser no mínimo 0.0")
        @DecimalMax(value = "1000000.0", message = "Valor do produto deve ser no máximo 1000000.0")
        Double price;
}
