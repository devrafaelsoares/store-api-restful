package br.devrafaelsoares.storeapirestful.domain.file.dto;

import br.devrafaelsoares.storeapirestful.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String path;

    @JsonIgnore
    @OneToOne(mappedBy = "image")
    private Product product;
}
