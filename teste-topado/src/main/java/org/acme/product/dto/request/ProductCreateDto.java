package org.acme.product.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductCreateDto {

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Min(1)
    private int unities;

    @NotNull
    @Min(1)
    private Double price;

}
