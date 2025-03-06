package foc.es.banco.cuenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@DiscriminatorValue("Corriente")
public class CuentaCorriente extends Cuenta implements OperacionesBancarias {

    @PositiveOrZero
    @Column
    private final double interes = 0.15;


    @Override
    public void retirar(double cantidad) {
        if (saldo + interes >= cantidad) {
            saldo -= cantidad;
        }
    }

    @Override
    public void actualizarSaldo() {
        saldo -= 10;
    }


    @Override
    public void transferir(Cuenta origen, Cuenta destino, double cantidad) {

    }

    @Override
    public void depositar(Cuenta cuenta, double cantidad) {

    }

    @Override
    public void consultarSaldo(Cuenta cuenta) {

    }
}
