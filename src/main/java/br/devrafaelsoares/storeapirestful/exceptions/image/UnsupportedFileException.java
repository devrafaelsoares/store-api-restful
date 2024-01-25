package br.devrafaelsoares.storeapirestful.exceptions.image;

public class UnsupportedFileException extends RuntimeException{
    public UnsupportedFileException(String message) {
        super(message);
    }
}
