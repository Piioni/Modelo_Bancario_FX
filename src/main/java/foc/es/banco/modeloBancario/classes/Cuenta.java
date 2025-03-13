package foc.es.banco.modeloBancario.classes;

import java.util.List;

public abstract class Cuenta {
    protected final int numeroDeCuenta;
    protected double saldo;
    protected Cliente titular;
    protected HistorialTransacciones transacciones;
    private static int contador = 0;


    // Constructores
    public Cuenta( double saldo, Cliente titular) {
        this.numeroDeCuenta = contador++;
        this.saldo = saldo;
        this.titular = titular;
        this.transacciones = new HistorialTransacciones();
        System.out.println("Cuenta creada con exito con numero de cuenta: " + numeroDeCuenta + " e id de cliente: " + titular.getId());
    }


    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    // Métodos abstractos
    public abstract void retirar(double cantidad);

    public abstract void actualizarSaldo();

    // Getters and Setters
    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }


    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void consultarMovimientos() {
        List<Transaccion> trans = transacciones.getTransacciones();
        System.out.println("Transacciones de la cuenta: ");
        for (Transaccion t : trans) {
            System.out.printf("Transacción: %s, Cantidad: %.2f, Tipo: %s%n", t.getFecha(), t.getCantidad(), t.getTipo());
        }

    }
}
