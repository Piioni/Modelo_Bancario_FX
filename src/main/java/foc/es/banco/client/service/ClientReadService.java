package foc.es.banco.client.service;

import foc.es.banco.client.model.Client;
import foc.es.banco.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientReadService {
    private final ClientRepository clientRepository;

    public ClientReadService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> get() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(Integer id) {
        return clientRepository.findById(id);
    }

    public List<Client> getByName (String name) {
        return clientRepository.findByName(name);
    }





}
