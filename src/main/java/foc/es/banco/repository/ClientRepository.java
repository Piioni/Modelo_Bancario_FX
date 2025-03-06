package foc.es.banco.repository;

import foc.es.banco.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByName(String name);
}
