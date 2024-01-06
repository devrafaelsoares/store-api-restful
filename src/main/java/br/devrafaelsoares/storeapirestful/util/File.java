package br.devrafaelsoares.storeapirestful.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Path;

public class File {

    public static final String PRODUCTS_IMAGE_PATH = "products/image/";
    private static final String STATIC_PATH = "/static";

    private static final char PATH_SEPARATOR = '/';
    public static void upload(
            MultipartFile file,
            String newFileName,
            String path
    ) throws IOException {

        Path destinationPath = new ClassPathResource(STATIC_PATH + PATH_SEPARATOR + path).getFile().toPath();
        Path filePath = destinationPath.resolve(newFileName);
        file.transferTo(filePath);
    }

}
