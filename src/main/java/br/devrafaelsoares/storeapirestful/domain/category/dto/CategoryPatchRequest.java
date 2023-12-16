package br.devrafaelsoares.storeapirestful.domain.category.dto;

import lombok.Getter;

@Getter
public class CategoryPatchRequest implements CategoryUpdate {

    private String name;

}
