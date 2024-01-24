package br.devrafaelsoares.storeapirestful.repositories;

import br.devrafaelsoares.storeapirestful.domain.order_item.OrderItem;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Query("SELECT ci.id.product FROM OrderItem ci WHERE ci.id.order.id = ?1 AND ci.id.product.id = ?2")
    Optional<Product> findProductByOrderIdAndProductId(@NotNull UUID idOrder, @NotNull UUID idProduct);
}
