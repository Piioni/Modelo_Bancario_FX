package foc.es.banco.modeloBancario.classes;

import foc.es.banco.modeloBancario.enums.TipoTransaccion;
import foc.es.banco.modeloBancario.interfaces.OperacionesBancarias;

public class CuentaCorriente extends Cuenta implements OperacionesBancarias {
    private final double interes = 0.15;


    public CuentaCorriente(double saldo, Cliente titular) {
        super(saldo, titular);
    }

    // Metodos de cuenta abstracta
    @Override
    public void retirar(double cantidad) {
        if (saldo + interes >= cantidad) {
            saldo -= cantidad;
            transacciones.agregarTransaccion(new Transaccion(cantidad, TipoTransaccion.RETIRO));

            System.out.println("Retiro realizado con exito");
        } else {
            System.out.println("No se puede realizar el retiro, saldo insuficiente");
        }

    }

    @Override
    public void actualizarSaldo() {
        saldo -= 10;
    }

    // Metodos de la interfaz
    @Override
    public void transferir(Cuenta destino, double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            destino.ingresar(cantidad);
            transacciones.agregarTransaccion(new Transaccion(cantidad, TipoTransaccion.TRANSFERENCIA));
        }

    }

    @Override
    public void depositar(Cuenta cuenta, double cantidad) {
        cuenta.ingresar(cantidad);
        transacciones.agregarTransaccion(new Transaccion(cantidad, TipoTransaccion.DEPOSITO));

    }

    @Override
    public void consultarSaldo(Cuenta cuenta) {
        System.out.println("El saldo de la cuenta es: " + cuenta.getSaldo());
    }
}
