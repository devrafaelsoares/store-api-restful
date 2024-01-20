package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.image.dto.Image;
import br.devrafaelsoares.storeapirestful.domain.image.dto.ImageResponse;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.services.ImageService;
import br.devrafaelsoares.storeapirestful.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
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
    public ResponseEntity<byte[]> show(
            @PathVariable UUID id
    ) throws IOException {

        Image image = imageService.findByProductId(id);

        byte[] byteImage = imageService.load(image.getFileName());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(byteImage);
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
        Image image = imageService.findByProductId(id);
        imageService.delete(product, image);

        return ResponseEntity.noContent().build();
    }
}