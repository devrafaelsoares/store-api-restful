package br.devrafaelsoares.storeapirestful.domain.cart;

import br.devrafaelsoares.storeapirestful.domain.cart_product.CartProduct;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "carts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "id.cart", fetch = FetchType.EAGER)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void addCartProduct(CartProduct cartProduct) {
        cartProducts.add(cartProduct);
    }
}