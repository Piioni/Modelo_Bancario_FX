package foc.es.banco.modeloBancario.classes;

import foc.es.banco.modeloBancario.enums.TipoTransaccion;
import foc.es.banco.modeloBancario.interfaces.OperacionesBancarias;

public class CuentaAhorro extends Cuenta implements OperacionesBancarias {
    protected double interes;
    protected double saldoMinimo;

    public CuentaAhorro( double saldo, Cliente titular, double interes, double saldoMinimo) {
        super(saldo, titular);
        this.interes = interes;
        this.saldoMinimo = saldoMinimo;
    }

    // Metodos de cuenta abstracta
    @Override
    public void retirar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a retirar debe ser mayor que cero");
            return;
        }
        if (saldo >= cantidad && saldo - cantidad >= saldoMinimo) {
            saldo -= cantidad;
            transacciones.agregarTransaccion(new Transaccion(cantidad, TipoTransaccion.RETIRO));
            System.out.println("Retiro realizado con éxito");
        } else {
            System.out.println("No se puede realizar el retiro, saldo insuficiente");
        }
    }


    @Override
    public void transferir(Cuenta destino, double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a transferir debe ser mayor que cero");
            return;
        }
        if (saldo >= cantidad && saldo - cantidad >= saldoMinimo) {
            saldo -= cantidad;
            destino.ingresar(cantidad);
            transacciones.agregarTransaccion(new Transaccion(cantidad, TipoTransaccion.TRANSFERENCIA));
            System.out.println("Transferencia realizada con éxito");
        } else {
            System.out.println("No se puede realizar la transferencia, saldo insuficiente");
        }
    }

    @Override
    public void depositar(Cuenta cuenta, double cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a depositar debe ser mayor que cero");
            return;
        }
        cuenta.ingresar(cantidad);
        transacciones.agregarTransaccion(new Transaccion(cantidad, TipoTransaccion.DEPOSITO));
        System.out.println("Depósito realizado con éxito");
    }

    @Override
    public void consultarSaldo(Cuenta cuenta) {
        System.out.println("El saldo de la cuenta es: " + cuenta.getSaldo());
    }

    @Override
    public void actualizarSaldo() {
        if (saldo >= saldoMinimo) {
            saldo += saldo * interes;
            System.out.println("Saldo actualizado con interés de cuenta de ahorro. Nuevo saldo: " + saldo);
        } else {
            System.out.println("No se puede actualizar el saldo, saldo insuficiente para aplicar interés.");
        }
    }

}
