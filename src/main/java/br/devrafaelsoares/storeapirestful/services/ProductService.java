package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.category.Category;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.domain.product.ProductCreateRequest;
import br.devrafaelsoares.storeapirestful.domain.product.ProductUpdate;
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

    public Product update(
            UUID id,
            ProductUpdate productUpdateRequest
    ) {

        Product foundProduct = findById(id);

        updateProductData(productUpdateRequest, foundProduct);

        productRepository.save(foundProduct);

        return foundProduct;

    }

    public void delete(
            UUID id
    ) {

        Product foundProduct = findById(id);

        productRepository.delete(foundProduct);
    }

    private void updateProductData(
            ProductUpdate productUpdateRequest,
            Product product
    ) {

        Category category = null;

        if (productUpdateRequest.getCategory() != null) {
            category = categoryService.findByName(productUpdateRequest.getCategory());
        }

        product.setName(productUpdateRequest.getName() != null ? productUpdateRequest.getName() : product.getName());
        product.setDescription(productUpdateRequest.getDescription() != null ? productUpdateRequest.getDescription() : product.getDescription());
        product.setCategory(productUpdateRequest.getCategory() != null ? category : product.getCategory());
        product.setPrice(productUpdateRequest.getPrice() != null ? productUpdateRequest.getPrice() : product.getPrice());
    }
}
