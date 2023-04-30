package org.acme.client;

import org.acme.client.dto.request.ClientCreateRequestDto;
import org.acme.client.dto.request.ClientUpdateRequestDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "cdi")
public interface ClientMapper {
    Client fromDto(ClientCreateRequestDto clientDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ClientUpdateRequestDto clientUpdateDto, @MappingTarget Client client);

}
