package org.acme.order;

import org.acme.order.dto.request.OrderCreateDto;
import org.acme.order.dto.request.OrderUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
public interface OrderMapper {
    Order fromDto(OrderCreateDto orderDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(OrderUpdateDto orderDto, @MappingTarget Order order);
}
