package br.devrafaelsoares.storeapirestful.domain.cart.dto;


import br.devrafaelsoares.storeapirestful.domain.cart.Cart;
import br.devrafaelsoares.storeapirestful.domain.cart_product.dto.CartProductResponse;
import br.devrafaelsoares.storeapirestful.domain.user.dto.UserCartResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse implements Serializable {

    private UUID id;

    private UserCartResponse user;

    private List<CartProductResponse> cartItems;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.user = new UserCartResponse(cart.getUser());
        this.cartItems = cart.getCartProducts()
                .stream()
                .map(CartProductResponse::new)
                .toList();
    }
}
