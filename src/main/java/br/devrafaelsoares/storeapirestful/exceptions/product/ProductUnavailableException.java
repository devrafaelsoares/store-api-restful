package br.devrafaelsoares.storeapirestful.exceptions.product;

public class ProductUnavailableException extends RuntimeException{

    public ProductUnavailableException(String message) {
        super(message);
    }
}
