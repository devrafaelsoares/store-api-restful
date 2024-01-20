package br.devrafaelsoares.storeapirestful.repositories;

import br.devrafaelsoares.storeapirestful.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {}
