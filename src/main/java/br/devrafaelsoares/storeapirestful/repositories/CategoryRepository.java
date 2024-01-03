package br.devrafaelsoares.storeapirestful.repositories;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(
            @NotNull String name
    );
}
