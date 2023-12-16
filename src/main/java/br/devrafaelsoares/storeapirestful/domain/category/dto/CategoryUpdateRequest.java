package br.devrafaelsoares.storeapirestful.domain.category.dto;

import br.devrafaelsoares.storeapirestful.domain.validations.constraints.NotBlankOrNull;
import lombok.Getter;

@Getter
public class CategoryUpdateRequest implements CategoryUpdate {

        @NotBlankOrNull(message = "Campo obrigatório")
        private String name;
}

