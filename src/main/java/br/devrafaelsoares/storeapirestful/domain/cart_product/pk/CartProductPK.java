package br.devrafaelsoares.storeapirestful.domain.cart_product.pk;

import br.devrafaelsoares.storeapirestful.domain.cart.Cart;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartProductPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
