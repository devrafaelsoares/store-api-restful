package br.devrafaelsoares.storeapirestful.exceptions.authentication;

import org.springframework.security.core.AuthenticationException;

public class UserExistsException extends AuthenticationException {
    public UserExistsException(String message) {
        super(message);
    }
}
