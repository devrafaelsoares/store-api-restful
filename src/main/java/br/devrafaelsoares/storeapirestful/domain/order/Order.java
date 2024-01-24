package br.devrafaelsoares.storeapirestful.domain.order;

import br.devrafaelsoares.storeapirestful.domain.coupon.Coupon;
import br.devrafaelsoares.storeapirestful.domain.order_item.OrderItem;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Instant moment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Double total() {
        return orderItems
                .stream()
                .mapToDouble(OrderItem::subTotal)
                .reduce(0, Double::sum);
    }
}
