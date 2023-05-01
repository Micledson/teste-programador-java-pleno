package org.acme.product;

import org.acme.product.dto.request.ProductCreateDto;
import org.acme.product.dto.request.ProductUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi")
public interface ProductMapper {
    Product fromDto(ProductCreateDto productDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ProductUpdateDto productDto, @MappingTarget Product product);
}
