package foc.es.banco.repository;

import foc.es.banco.cuenta.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
