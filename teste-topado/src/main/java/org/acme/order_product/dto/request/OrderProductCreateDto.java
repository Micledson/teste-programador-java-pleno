package org.acme.order_product.dto.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderProductCreateDto {

    @NotNull
    private Long id;


    @NotNull
    private int quantity;

}
