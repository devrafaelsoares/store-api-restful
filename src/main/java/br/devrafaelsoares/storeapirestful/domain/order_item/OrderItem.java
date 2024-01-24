package br.devrafaelsoares.storeapirestful.domain.order_item;

import br.devrafaelsoares.storeapirestful.domain.order.Order;
import br.devrafaelsoares.storeapirestful.domain.order_item.pk.OrderItemPK;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "order_items")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    @Column(nullable = false)
    private Integer quantity;

    @Transient
    private Product product;

    @Transient
    private Order order;

    public Double subTotal() {
        double discount = id.getProduct().getDiscount();
        double totalPriceProduct = id.getProduct().getPrice() * quantity;

        return
                discount > 0.0 ? totalPriceProduct - (totalPriceProduct * discount) : totalPriceProduct;
    }
}
