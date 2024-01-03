package br.devrafaelsoares.storeapirestful.repositories;

import br.devrafaelsoares.storeapirestful.domain.user.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(
            @NotNull String username
    );
}
