package br.devrafaelsoares.storeapirestful.exceptions.image;

public class FileNotSentException extends RuntimeException{
    public FileNotSentException(String message) {
        super(message);
    }
}
