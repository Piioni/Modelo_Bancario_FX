package foc.es.banco.modeloBancario.interfaces;

import foc.es.banco.modeloBancario.classes.Cuenta;

public interface OperacionesBancarias {
    void transferir(Cuenta destino, double cantidad);

    void depositar(Cuenta cuenta, double cantidad);

    void consultarSaldo(Cuenta cuenta);
}
