package br.devrafaelsoares.storeapirestful.exceptions.auth;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

}
