package br.devrafaelsoares.storeapirestful.exceptions.product;

public class ProductExistsInCart extends RuntimeException {

    public ProductExistsInCart(String message) {
        super(message);
    }
}
