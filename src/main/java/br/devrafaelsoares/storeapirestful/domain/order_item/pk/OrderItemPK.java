package br.devrafaelsoares.storeapirestful.domain.order_item.pk;

import br.devrafaelsoares.storeapirestful.domain.order.Order;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
