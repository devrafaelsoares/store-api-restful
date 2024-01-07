package br.devrafaelsoares.storeapirestful.domain.image.dto;

import java.util.UUID;

public record ImageResponse(
        UUID id,
        String originalName,
        String fileName,
        String path
) {
    public ImageResponse(Image image) { this(image.getId(), image.getOriginalName(), image.getFileName(), image.getPath()); }
}