package br.devrafaelsoares.storeapirestful.exceptions.auth;

import org.springframework.security.core.AuthenticationException;

public class UserExistsException extends AuthenticationException {
    public UserExistsException(String message) {
        super(message);
    }
}
