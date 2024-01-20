package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.cart.Cart;
import br.devrafaelsoares.storeapirestful.domain.cart.dto.CartRequestCreate;
import br.devrafaelsoares.storeapirestful.domain.cart_product.CartProduct;
import br.devrafaelsoares.storeapirestful.domain.cart_product.pk.CartProductPK;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.exceptions.product.ProductExistsInCart;
import br.devrafaelsoares.storeapirestful.repositories.CartProductRepository;
import br.devrafaelsoares.storeapirestful.repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartProductRepository cartProductRepository;

    public List<CartProduct> findByCartId(
            @NotNull UUID id
    ) {
        return cartProductRepository.findByCartId(id);
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public CartProduct addProductToCart(
            @NotNull CartRequestCreate cartRequestCreate
    ) {
        UUID cartUUID = UUID.fromString(cartRequestCreate.cartId());
        UUID productUUID = UUID.fromString(cartRequestCreate.productId());

        Cart cart = cartRepository.findById(cartUUID)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Carrinho com o id '%s' não existe", cartUUID)));

        Product product = productService.findById(productUUID);

        CartProduct cartProduct = cartProductRepository.findById(
                CartProductPK
                    .builder()
                        .cart(cart)
                        .product(product)
                    .build()
        ).orElse(null);

        if (cartProduct != null) {
            throw new ProductExistsInCart("Produto já se encontra no carrinho");
        }

        CartProduct newCartProduct = new CartProduct(cart, product, cartRequestCreate.quantity());

        cartProductRepository.save(newCartProduct);

        return newCartProduct;
    }

    public CartProduct updateProductQuantityInCart(
            @NotNull UUID cartId,
            @NotNull UUID productId,
            @NotNull Integer quantity
    ) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Carrinho com o id '%s' não existe", cartId)));

        Product product = productService.findById(productId);

        CartProduct cartProduct = cartProductRepository.findById(
                CartProductPK
                        .builder()
                            .cart(cart)
                            .product(product)
                        .build()
        )
                .orElseThrow(() -> new ProductExistsInCart("Produto já se encontra no carrinho"));

        cartProduct.setQuantity(quantity);
        cartProductRepository.save(cartProduct);

        return cartProduct;
    }

    public void delete(
            @NotNull UUID cartId,
            @NotNull UUID productId
    ) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Carrinho com o id '%s' não existe", cartId)));

        Product product = productService.findById(productId);

        CartProduct cartProduct = cartProductRepository.findById(
                        CartProductPK
                                .builder()
                                    .cart(cart)
                                    .product(product)
                                .build()
                )
                .orElseThrow(() -> new EntityNotFoundException("Produto não se encontra no carrinho"));

        cartProductRepository.delete(cartProduct);

    }

}
