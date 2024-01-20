package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.image.dto.Image;
import br.devrafaelsoares.storeapirestful.domain.product.Product;
import br.devrafaelsoares.storeapirestful.repositories.ImageRepository;
import br.devrafaelsoares.storeapirestful.exceptions.product.ImageExistsException;
import br.devrafaelsoares.storeapirestful.repositories.ProductRepository;
import br.devrafaelsoares.storeapirestful.util.File;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    @Autowired
    private ProductRepository productRepository;

    public Image findByProductId(
            @NotNull UUID id
    ) {
        return imageRepository.findByProductId(id)
                .orElseThrow(() -> new EntityNotFoundException("Este produto não possui uma imagem registrada"));
    }

    public byte[] load(String fileName) throws IOException {
        return File.load(fileName, File.PRODUCTS_IMAGE_PATH).getContentAsByteArray();
    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Image save(
            @NotNull MultipartFile file,
            @NotNull Product product
    ) throws IOException {

        if(product.getImage() != null) {
            throw new ImageExistsException("Produto já possui uma imagem registrada");
        }

        String newFileName = File.generateName(file, product.getName());

        Image image = Image
                .builder()
                    .originalName(file.getOriginalFilename())
                    .fileName(newFileName)
                    .path(File.PRODUCTS_IMAGE_PATH + newFileName)
                    .product(product)
                .build();

        Image imageSaved = imageRepository.save(image);

        File.upload(file, newFileName, File.PRODUCTS_IMAGE_PATH);

        return imageSaved;
    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public Image update(
            @NotNull MultipartFile file,
            @NotNull Product product
    ) throws IOException {

        Image image = findByProductId(product.getId());

        String nameFile = image.getFileName();

        if(!FilenameUtils.removeExtension(File.generateName(file, product.getName())).equals(FilenameUtils.removeExtension(nameFile))) {
                File.delete(nameFile, File.PRODUCTS_IMAGE_PATH);
            nameFile = File.generateName(file, product.getName());
        }

        File.upload(file, nameFile, File.PRODUCTS_IMAGE_PATH);

        image.setOriginalName(file.getOriginalFilename());

        image.setFileName(nameFile);

        image.setPath(File.PRODUCTS_IMAGE_PATH + nameFile);

        return imageRepository.save(image);
    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public void delete(
            @NotNull Product product,
            @NotNull Image image
    ) throws IOException {

        image.setProduct(null);

        imageRepository.delete(image);

        File.delete(product.getImage().getFileName(), File.PRODUCTS_IMAGE_PATH);
    }
}