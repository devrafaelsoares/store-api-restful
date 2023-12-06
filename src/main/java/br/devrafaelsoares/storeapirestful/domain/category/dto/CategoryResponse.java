package br.devrafaelsoares.storeapirestful.domain.category.dto;

import br.devrafaelsoares.storeapirestful.domain.category.Category;

import java.io.Serializable;
import java.util.UUID;

public record CategoryResponse (
        UUID id,
        String name
) implements Serializable {
    public CategoryResponse(Category category) {
        this(category.getId(), category.getName());
    }
}
