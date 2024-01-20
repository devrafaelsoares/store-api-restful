package br.devrafaelsoares.storeapirestful.repositories;

import br.devrafaelsoares.storeapirestful.domain.cart_product.CartProduct;
import br.devrafaelsoares.storeapirestful.domain.cart_product.pk.CartProductPK;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductPK> {

    @Query("SELECT cp FROM CartProduct cp WHERE cp.id.cart.id = :id")
    List<CartProduct> findByCartId(@NotNull UUID id);

}
