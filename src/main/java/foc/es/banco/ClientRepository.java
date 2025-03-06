package foc.es.banco;

import foc.es.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente, Integer> {
}
