package br.devrafaelsoares.storeapirestful.domain.image.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageRequestCreate(
        MultipartFile image

) {}
