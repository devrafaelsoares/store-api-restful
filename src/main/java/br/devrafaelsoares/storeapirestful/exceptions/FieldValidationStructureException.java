package br.devrafaelsoares.storeapirestful.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidationStructureException {

    private String field;
    private String message;
}
