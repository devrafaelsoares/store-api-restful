package br.devrafaelsoares.storeapirestful.domain.user.dto;

import br.devrafaelsoares.storeapirestful.domain.user.Role;
import br.devrafaelsoares.storeapirestful.domain.user.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String username,
        Role role
) {
    public  UserResponse(
            User user
    ) {
        this(user.getId(), user.getName(), user.getEmail(), user.getUsername(), user.getRole());
    }
}
