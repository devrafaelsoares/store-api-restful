package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.file.dto.Image;
import br.devrafaelsoares.storeapirestful.domain.file.dto.ImageResponse;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.services.ImageService;
import br.devrafaelsoares.storeapirestful.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ImageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/product/{id}/image")
    public RedirectView show(
            @PathVariable UUID id
    ) {
        Image image = imageService.findByIdProduct(id);

        String domain =  ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();

        return new RedirectView(String.format("%s/%s", domain, image.getPath()));
    }

    @PostMapping("/product/{id}/image")
    public ResponseEntity<ImageResponse> store(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        Product product = productService.findById(id);

        Image imageSaved = imageService.save(file, product);

        ImageResponse imageResponse = new ImageResponse(imageSaved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/products/image")
                .path("/{fileName}")
                .buildAndExpand(imageSaved.getFileName())
                .toUri();

        return ResponseEntity.created(location).body(imageResponse);
    }

    @PutMapping("/product/{id}/image")
    public ResponseEntity<ImageResponse> update(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Product product = productService.findById(id);

        Image imageSaved = imageService.update(file, product);

        ImageResponse imageResponse = new ImageResponse(imageSaved);

        return ResponseEntity.ok(imageResponse);
    }

    @DeleteMapping("/product/{id}/image")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) throws IOException {
        Product product = productService.findById(id);

        imageService.delete(product);

        return ResponseEntity.noContent().build();
    }
}