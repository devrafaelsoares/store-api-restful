package br.devrafaelsoares.storeapirestful.domain.cart_product;

import br.devrafaelsoares.storeapirestful.domain.cart.Cart;
import br.devrafaelsoares.storeapirestful.domain.cart_product.pk.CartProductPK;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "cart_products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartProduct implements Serializable {

    @EmbeddedId
    private CartProductPK id = new CartProductPK();

    private Integer quantity;

    public CartProduct(Cart cart, Product product, Integer quantity) {
        id.setCart(cart);
        id.setProduct(product);
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return id.getProduct().getPrice() * quantity;
    }
}