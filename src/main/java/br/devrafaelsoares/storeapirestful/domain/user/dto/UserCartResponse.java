package br.devrafaelsoares.storeapirestful.domain.user.dto;
import br.devrafaelsoares.storeapirestful.domain.user.User;

import java.util.UUID;

public record UserCartResponse(
    UUID id,
    String name,
    String email,
    String username
) {
    public UserCartResponse(
            User user
    ) {
        this(user.getId(), user.getName(), user.getEmail(), user.getUsername());
    }
}
