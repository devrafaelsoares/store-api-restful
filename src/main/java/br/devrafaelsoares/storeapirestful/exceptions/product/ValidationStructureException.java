package br.devrafaelsoares.storeapirestful.exceptions.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationStructureException {

    private ZonedDateTime moment;

    private Integer status;

    private List<FieldValidationStructureException> errors;

    private String path;
}
