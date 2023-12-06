package br.devrafaelsoares.storeapirestful.domain.product;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
}
