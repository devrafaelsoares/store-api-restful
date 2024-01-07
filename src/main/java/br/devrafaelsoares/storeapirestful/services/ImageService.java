package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.image.dto.Image;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.repositories.ImageRepository;
import br.devrafaelsoares.storeapirestful.exceptions.product.ImageExistsException;
import br.devrafaelsoares.storeapirestful.util.File;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductService productService;

    public Image findByIdProduct(
            @NotNull UUID id
    ) {
        return productService.findById(id).getImage();
    }

    public Image save(
            @NotNull MultipartFile file,
            @NotNull Product product
    ) throws IOException {

        if(product.getImage() != null) {
            throw new ImageExistsException("Produto já possui uma imagem registrada");
        }

        String newFileName = File.generateName(file, product.getName());

        File.upload(file, newFileName, File.PRODUCTS_IMAGE_PATH);

        Image imageSaved = imageRepository.save(Image
                .builder()
                        .originalName(file.getOriginalFilename())
                        .fileName(newFileName)
                        .path(File.PRODUCTS_IMAGE_PATH + newFileName)
                .build());

        productService.updateImage(product, imageSaved);

        return imageSaved;
    }

    public Image update(
            @NotNull MultipartFile file,
            @NotNull Product product
    ) throws IOException {

        String nameFile = product.getImage().getFileName();

        if(!File.generateName(file, product.getName()).equals(nameFile)) {
            File.delete(nameFile, File.PRODUCTS_IMAGE_PATH);
            nameFile = File.generateName(file, product.getName());
        }

        File.upload(file, nameFile, File.PRODUCTS_IMAGE_PATH);

        Image imageSaved = imageRepository.save(Image
                .builder()
                    .originalName(file.getOriginalFilename())
                    .fileName(nameFile)
                    .path(File.PRODUCTS_IMAGE_PATH + nameFile)
                .build());

        productService.updateImage(product, imageSaved);

        return imageSaved;
    }

    public void delete(
            @NotNull Product product
    ) throws IOException {
        productService.deleteImage(product);
    }
}