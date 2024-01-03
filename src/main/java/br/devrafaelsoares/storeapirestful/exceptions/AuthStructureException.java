package br.devrafaelsoares.storeapirestful.exceptions;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthStructureException {

    @NotNull
    private ZonedDateTime moment;

    @NotNull
    private Integer status;

    @NotNull
    private String error;

    private String message;

    @NotNull
    private String path;

}
