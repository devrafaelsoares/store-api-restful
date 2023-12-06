package br.devrafaelsoares.storeapirestful.domain.product.dto;

public record ProductCreateRequest(
        String name,
        String description,
        String category,
        Double price
) {}
