package br.devrafaelsoares.storeapirestful.domain.product;

import lombok.Getter;

@Getter
public record ProductUpdateRequest (
        String name,
        String description,
        String category,
        Double price
) implements ProductUpdate
{}
