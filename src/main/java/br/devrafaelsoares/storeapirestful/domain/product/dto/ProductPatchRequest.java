package br.devrafaelsoares.storeapirestful.domain.product.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.Categories;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;

@Getter
public class ProductPatchRequest implements ProductUpdate {

        private String name;

        private String description;

        @Categories(message = "Categoria inválida ou não existe")
        private String category;

        @DecimalMin(value = "0.0", message = "Valor do produto deve ser no mínimo 0.0")
        @DecimalMax(value = "1000000.0", message = "Valor do produto deve ser no máximo 1000000.0")
        private Double price;
}
