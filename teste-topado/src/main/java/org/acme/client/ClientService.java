package org.acme.client;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.client.dto.request.ClientCreateRequestDto;
import org.acme.client.dto.request.ClientUpdateRequestDto;
import utils.ValidatorUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientService {
    @Inject
    private ClientRepository clientRepository;

    @Inject
    private ClientMapper clientMapper;

    @Inject
    private ValidatorUtils validator;

    public List<Client> findAll() {
        PanacheQuery<Client> clients = this.clientRepository.findAll();
        return clients.list();
    }


    public Client create(ClientCreateRequestDto clientDto) {
        validator.validators(clientDto);

        Client client = this.clientMapper.fromDto(clientDto);

        verifyFieldsToCreate(client);

        this.clientRepository.persistAndFlush(client);
        return client;
    }

    public Client update(ClientUpdateRequestDto clientDto, Long id) {
        validator.validators(clientDto);

        Client entity = this.clientRepository.findById(id);
        if (entity == null) throw new WebApplicationException("Client not Found", Response.Status.NOT_FOUND);

        this.clientMapper.updateFromDto(clientDto, entity);

        verifyFieldsToUpdate(entity);

        this.clientRepository.persist(entity);
        return entity;

    }

    public boolean delete(Long id) {
        return this.clientRepository.deleteById(id);
    }

    private Exception isUniqueEmail(String email, Optional<Long> id) {
        Client client = this.clientRepository.find("email = ?1", email).firstResult();

        if (client == null || id.isPresent() && client.getId().equals(id.get())) return null;

        throw new WebApplicationException("Email already used", Response.Status.BAD_REQUEST);
    }

    private Exception isUniqueCPF(String CPF, Optional<Long> id) {
        Client client = this.clientRepository.find("CPF = ?1", CPF).firstResult();

        if (client == null || id.isPresent() && client.getId().equals(id.get())) return null;
        throw new WebApplicationException("CPF already used", Response.Status.CONFLICT);
    }

    private Exception isUniquePhone(String phone, Optional<Long> id) {
        Client client = this.clientRepository.find("phone = ?1", phone).firstResult();

        if (client == null || id.isPresent() && client.getId().equals(id.get())) return null;

        throw new WebApplicationException("Phone already used", Response.Status.BAD_REQUEST);
    }

    private void verifyFieldsToCreate(Client client) {
        this.isUniqueCPF(client.getCPF(), Optional.empty());
        this.isUniqueEmail(client.getEmail(), Optional.empty());
        this.isUniquePhone(client.getPhone(), Optional.empty());
    }

    private void verifyFieldsToUpdate(Client client) {
        this.isUniqueEmail(client.getEmail(), Optional.of(client.getId()));
        this.isUniquePhone(client.getPhone(), Optional.of(client.getId()));
    }

}
