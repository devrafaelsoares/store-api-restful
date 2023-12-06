package br.devrafaelsoares.storeapirestful.domain.product;

public record ProductCreateRequest(
        String name,
        String description,
        String category,
        Double price
) {}
