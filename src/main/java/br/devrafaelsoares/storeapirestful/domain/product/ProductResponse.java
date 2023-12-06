package br.devrafaelsoares.storeapirestful.domain.product;

import br.devrafaelsoares.storeapirestful.domain.category.Category;

import java.io.Serializable;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        Category category,
        Double price
) implements Serializable {
    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice());
    }
}
