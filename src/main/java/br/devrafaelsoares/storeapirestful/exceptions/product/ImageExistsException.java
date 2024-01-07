package br.devrafaelsoares.storeapirestful.exceptions.product;

public class ImageExistsException extends RuntimeException {

    public ImageExistsException(String message) {
        super(message);
    }
}