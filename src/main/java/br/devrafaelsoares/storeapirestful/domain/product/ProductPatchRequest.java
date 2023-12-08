package br.devrafaelsoares.storeapirestful.domain.product;

import lombok.Getter;

@Getter
public record ProductPatchRequest (
        String name,
        String description,
        String category,
        Double price
) implements ProductUpdate
{}
