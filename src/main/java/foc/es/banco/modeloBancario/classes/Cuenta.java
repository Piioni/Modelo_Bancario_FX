package foc.es.banco.modeloBancario.classes;

public abstract class Cuenta {
    protected int numeroDeCuenta;
    protected double saldo;
    protected Cliente titular;
    private static int contador = 0;

    // Constructores
    public Cuenta( double saldo, Cliente titular) {
        this.numeroDeCuenta = contador++;
        this.saldo = saldo;
        this.titular = titular;
        System.out.println("Cuenta creada con exito con numero de cuenta: " + numeroDeCuenta + " e id de cliente: " + titular.getId());
    }

    public Cuenta() {
        this.numeroDeCuenta = 0;
        this.saldo = 0;
        this.titular = null;
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

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
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
}
