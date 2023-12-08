package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.product.*;
import br.devrafaelsoares.storeapirestful.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> index() {

        List<ProductResponse> productResponseList = productService
                .findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();

        return ResponseEntity.ok(productResponseList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> show(
            @PathVariable UUID id
    ) {

        Product foundProduct = productService.findById(id);

        ProductResponse productResponse = new ProductResponse(foundProduct);

        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> store(
            @RequestBody ProductCreateRequest productRequest
    ) {

        Product savedProduct = productService.save(productRequest);

        ProductResponse productResponse = new ProductResponse(savedProduct);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/product")
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(productResponse);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable UUID id,
            @RequestBody ProductUpdateRequest productRequest
    ) {

        ProductResponse productResponse = new ProductResponse(productService.update(id, productRequest));

        return ResponseEntity.ok(productResponse);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable UUID id,
            @RequestBody ProductPatchRequest productRequest
    ) {

        ProductResponse productResponse = new ProductResponse(productService.update(id, productRequest));

        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
