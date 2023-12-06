package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.domain.product.ProductCreateRequest;
import br.devrafaelsoares.storeapirestful.repositories.ProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

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

    private boolean isExistsProductByName(
            String name
    ) {
        return productRepository
                .findByName(name)
                .isPresent();
    }

    public Product save(
            ProductCreateRequest productCreateRequest
    ) {

        if (isExistsProductByName(productCreateRequest.name())) {
            throw new EntityExistsException("Já existe um produto com esse nome cadastrado no sistema");
        }

        Category category = categoryService.findByName(productCreateRequest.category());

        return productRepository.save(Product
                .builder()
                    .name(productCreateRequest.name())
                    .description(productCreateRequest.description())
                    .category(category)
                    .price(productCreateRequest.price())
                .build());
    }
}
