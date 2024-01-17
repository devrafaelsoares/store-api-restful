package br.devrafaelsoares.storeapirestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StoreApiRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApiRestfulApplication.class, args);
    }

}
