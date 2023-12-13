package br.devrafaelsoares.storeapirestful.exceptions.authentication;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

}
