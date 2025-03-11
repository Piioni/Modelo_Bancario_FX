package foc.es.banco.modeloBancario.classes;

import foc.es.banco.modeloBancario.interfaces.OperacionesBancarias;

public class CuentaCorriente extends Cuenta implements OperacionesBancarias {
    private final double interes = 0.15;
    private HistorialTransacciones transacciones;



    public CuentaCorriente(int numeroDeCuenta, double saldo, Cliente titular) {
        super(numeroDeCuenta, saldo, titular);
        this.transacciones = new HistorialTransacciones();
    }

    public CuentaCorriente() {
        super();
        this.transacciones = new HistorialTransacciones();
    }

    // Metodos de cuenta abstracta
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

    // Metodos de la interfaz
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
