package br.devrafaelsoares.storeapirestful.domain.product.dto;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.image.dto.Image;
import br.devrafaelsoares.storeapirestful.domain.product.Product;

import java.io.Serializable;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        Category category,
        Double price,
        Image image
) implements Serializable {
    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice(), product.getImage());
    }
}
