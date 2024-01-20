package br.devrafaelsoares.storeapirestful.domain.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestUpdate implements Serializable {

    @NotNull
    private  Integer quantity;

}
