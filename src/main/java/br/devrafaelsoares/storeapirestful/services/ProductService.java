package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(
            UUID id
    ) {

        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não foi possível encontrar um produto com o id '%s'", id)));
    }
}
