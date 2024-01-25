package br.devrafaelsoares.storeapirestful.exceptions.image;

public class ImageExistsException extends RuntimeException {

    public ImageExistsException(String message) {
        super(message);
    }
}