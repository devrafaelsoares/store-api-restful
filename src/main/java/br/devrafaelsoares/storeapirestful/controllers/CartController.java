package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.cart.dto.CartRequestCreate;
import br.devrafaelsoares.storeapirestful.domain.cart.dto.CartRequestUpdate;
import br.devrafaelsoares.storeapirestful.domain.cart.dto.CartResponse;
import br.devrafaelsoares.storeapirestful.domain.cart_product.CartProduct;
import br.devrafaelsoares.storeapirestful.domain.cart_product.dto.CartProductResponse;
import br.devrafaelsoares.storeapirestful.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CartProductResponse>> show(
            @PathVariable UUID id
            ) {

        List<CartProductResponse> cartResponseList = cartService
                .findByCartId(id)
                .stream()
                .map(CartProductResponse::new)
                .toList();

        return ResponseEntity.ok(cartResponseList);
    }

    @GetMapping("/admin/carts")
    public ResponseEntity<List<CartResponse>> index() {

        List<CartResponse> cartResponse = cartService
                .findAll()
                .stream()
                .map(CartResponse::new)
                .toList();

        return ResponseEntity.ok(cartResponse);
    }

    @PostMapping("/cart")
    public ResponseEntity<CartProductResponse> store(
            @RequestBody CartRequestCreate cartRequestCreate
            ) {

        CartProduct cartProduct = cartService.addProductToCart(cartRequestCreate);

        CartProductResponse cartResponse = new CartProductResponse(cartProduct);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/cart")
                .path("/{id}")
                .buildAndExpand(cartProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(cartResponse);
    }

    @PutMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<CartProductResponse> update(
            @PathVariable UUID cartId,
            @PathVariable UUID productId,
            @RequestBody CartRequestUpdate cartRequestUpdate
            ) {

        CartProduct cartProduct = cartService.updateProductQuantityInCart(
                cartId,
                productId,
                cartRequestUpdate.getQuantity()
        );

        CartProductResponse cartResponse = new CartProductResponse(cartProduct);

        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/cart/{cartId}/product/{productId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID cartId,
            @PathVariable UUID productId
    ) {

        cartService.delete(cartId, productId);

        return ResponseEntity.noContent().build();
    }
}
