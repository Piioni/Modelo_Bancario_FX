package foc.es.banco.modeloBancario.classes;

import foc.es.banco.modeloBancario.interfaces.OperacionesBancarias;

public class CuentaAhorro extends Cuenta implements OperacionesBancarias {
    private double interes;
    private double saldoMinimo;
    private HistorialTransacciones transacciones;

    public CuentaAhorro(int numeroDeCuenta, double saldo, Cliente titular, double interes, double saldoMinimo) {
        super(numeroDeCuenta, saldo, titular);
        this.interes = interes;
        this.saldoMinimo = saldoMinimo;
        this.transacciones = new HistorialTransacciones();
    }

    public CuentaAhorro() {
        super();
        this.interes = 0;
        this.saldoMinimo = 0;
        this.transacciones = new HistorialTransacciones();
    }

    // Metodos de cuenta abstracta
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

    // Metodos de la interfaz
    @Override
    public void transferir(Cuenta destino, double cantidad) {

    }

    @Override
    public void depositar(Cuenta cuenta, double cantidad) {

    }

    @Override
    public void consultarSaldo(Cuenta cuenta) {

    }
}
