package foc.es.banco.cuenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@DiscriminatorValue("Ahorro")
public class CuentaAhorro extends Cuenta implements OperacionesBancarias {

    @PositiveOrZero(message = "El interés debe ser positivo o cero")
    @Column(name = "interes")
    private double interes;

    @PositiveOrZero(message = "El saldo mínimo debe ser positivo o cero")
    @Column(name = "saldo_minimo")
    private double saldoMinimo;


    @Override
    public void retirar(double cantidad) {
        if (saldo >= cantidad && saldo - cantidad >= saldoMinimo) {
            saldo -= cantidad;
        }
    }

    @Override
    public void actualizarSaldo() {
        saldo += saldo * interes;
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
