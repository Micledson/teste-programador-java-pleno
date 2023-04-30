package org.acme.client;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClientService {
    @Inject
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return this.clientRepository.findAll().list();
    }

}
