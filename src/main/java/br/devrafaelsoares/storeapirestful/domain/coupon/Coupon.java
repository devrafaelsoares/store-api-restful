package br.devrafaelsoares.storeapirestful.domain.coupon;

import br.devrafaelsoares.storeapirestful.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Coupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code;

    private Date expiration;

    private Double percentage;

    @OneToOne(mappedBy = "coupon")
    private Order order;
}
