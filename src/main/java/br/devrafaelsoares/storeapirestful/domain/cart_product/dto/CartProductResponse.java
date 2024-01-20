package br.devrafaelsoares.storeapirestful.domain.cart_product.dto;

import br.devrafaelsoares.storeapirestful.domain.cart_product.CartProduct;
import br.devrafaelsoares.storeapirestful.domain.product.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProductResponse implements Serializable {

    private ProductResponse product;

    private Integer quantity;

    private Double subTotal;
    public CartProductResponse(CartProduct cartProduct) {
        this.product = new ProductResponse(cartProduct.getId().getProduct());
        this.quantity = cartProduct.getQuantity();
        this.subTotal = cartProduct.getSubTotal();
    }
}
