package br.devrafaelsoares.storeapirestful.domain.category.dto;

import lombok.Getter;

@Getter
public record CategoryUpdateRequest (
        String name
) implements CategoryUpdate
{}

