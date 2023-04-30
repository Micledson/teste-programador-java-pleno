package org.acme.order.dto.request;

import lombok.Data;

@Data
public class OrderCreateDto {
    private String description;

    private Long clientId;
}
