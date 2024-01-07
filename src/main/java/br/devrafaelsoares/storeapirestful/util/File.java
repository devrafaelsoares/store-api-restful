package br.devrafaelsoares.storeapirestful.util;

import jakarta.validation.constraints.NotNull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class File {

    public static final String PRODUCTS_IMAGE_PATH = "products/image/";
    private static final String STATIC_PATH = "src/main/resources/static";

    private static final char PATH_SEPARATOR = '/';
    public static void upload(
            @NotNull MultipartFile file,
            @NotNull String newFileName,
            @NotNull String path
    ) throws IOException {

        Path destinationPath = Path.of(STATIC_PATH + PATH_SEPARATOR + path);
        Path filePath = destinationPath.resolve(newFileName);
        file.transferTo(filePath);
    }

    public static void delete(
            @NotNull String fileName,
            @NotNull String path
    ) throws IOException {
        Path destinationPath = Path.of(STATIC_PATH + PATH_SEPARATOR + path);
        Path filePath = destinationPath.resolve(fileName);
        Files.delete(filePath);
    }

    public static String generateName(MultipartFile file, String name) {
        return  String.format("%s.%s", Slug.make(name), FilenameUtils.getExtension(file.getOriginalFilename()));
    }

}
