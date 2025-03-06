package foc.es.banco.client.service;

import foc.es.banco.repository.ClientRepository;
import foc.es.banco.client.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientWriteService {
    private final ClientRepository clientRepository;

    public ClientWriteService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void create(Client cliente) {
        clientRepository.save(cliente);
    }

    public void update(Client cliente) {
        clientRepository.save(cliente);
    }

    public void delete(Client cliente) {
        clientRepository.delete(cliente);
    }

}
