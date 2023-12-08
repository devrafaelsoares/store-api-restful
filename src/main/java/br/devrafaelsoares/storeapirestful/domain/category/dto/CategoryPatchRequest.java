package br.devrafaelsoares.storeapirestful.domain.category.dto;

import lombok.Getter;

@Getter
public record CategoryPatchRequest (
        String name
) implements CategoryUpdate
{}
