package br.devrafaelsoares.storeapirestful.domain.cart.dto;

import jakarta.validation.constraints.NotNull;

public record CartRequestCreate(
        @NotNull String cartId,
        @NotNull String productId,
        @NotNull Integer quantity
) {
}
