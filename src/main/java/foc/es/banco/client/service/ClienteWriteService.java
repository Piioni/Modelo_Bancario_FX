package foc.es.banco.client.service;

import foc.es.banco.ClienteRepository;
import foc.es.banco.client.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteWriteService {
    private final ClienteRepository clientRepository;

    public ClienteWriteService(ClienteRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void create(Cliente cliente) {
        clientRepository.save(cliente);
    }

    public void update(Cliente cliente) {
        clientRepository.save(cliente);
    }

    public void delete(Cliente cliente) {
        clientRepository.delete(cliente);
    }

}
