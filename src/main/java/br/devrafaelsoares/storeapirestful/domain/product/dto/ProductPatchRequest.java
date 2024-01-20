package br.devrafaelsoares.storeapirestful.domain.product.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.Categories;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ProductPatchRequest implements ProductUpdate {

        @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Não é permitido caracteres vázios")
        private String name;

        @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Não é permitido caracteres vázios")
        private String description;

        @Categories(message = "Categoria inválida ou não existe")
        private String category;

        @DecimalMin(value = "0.0", message = "Valor do produto deve ser no mínimo 0.0")
        @DecimalMax(value = "1000000.0", message = "Valor do produto deve ser no máximo 1000000.0")
        Double price;
}
