package foc.es.banco.client.service;

import foc.es.banco.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientReadService {
    private final ClientRepository clientRepository;

    public ClientReadService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void get() {
        clientRepository.findAll();
    }

    public void getById() {
        clientRepository.findById(1);
    }

//    public void getByDni() {
//        clientRepository.findByDni("12345678A");
//    }
//
//    public void getByNombre() {
//        clientRepository.findByNombre("Pepe");
//    }
//
//    public void getByApellidos() {
//        clientRepository.findByApellidos("García");
//    }
//
//    public void getByDireccion() {
//        clientRepository.findByDireccion("Calle Falsa 123");
//    }
//
//    public void getByTelefono() {
//        clientRepository.findByTelefono("123456789");
//    }




}
