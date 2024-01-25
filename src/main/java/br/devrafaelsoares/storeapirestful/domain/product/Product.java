package br.devrafaelsoares.storeapirestful.domain.product;

import br.devrafaelsoares.storeapirestful.domain.cart_product.CartProduct;
import br.devrafaelsoares.storeapirestful.domain.order_item.OrderItem;
import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.image.dto.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, length = 500)
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Category category;

    private Double price;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Image image;

    @OneToMany(mappedBy = "id.product", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToMany(mappedBy = "id.product", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<CartProduct> cartProducts = new HashSet<>();
}
