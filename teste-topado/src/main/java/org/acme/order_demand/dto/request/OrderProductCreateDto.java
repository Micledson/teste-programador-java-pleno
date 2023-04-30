package org.acme.order_demand.dto.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderProductCreateDto {

    @NotNull
    private Long id;


    @NotNull
    private int quantity;

}
