package br.devrafaelsoares.storeapirestful.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN, SELLER, CLIENT;

    public static Collection<String> getAllRoles() {
        return List.of(ADMIN.name(), SELLER.name(), CLIENT.name());
    }

}