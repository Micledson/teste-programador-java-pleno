package org.acme.product.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ProductUpdateDto {

    private String description;

    @Min(0)
    private int unities;

    @Min(1)
    private Double price;

}
