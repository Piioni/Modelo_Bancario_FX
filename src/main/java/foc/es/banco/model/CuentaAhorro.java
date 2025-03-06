package foc.es.banco.model;

public class CuentaAhorro extends Cuenta implements OperacionesBancarias {
    private double interes;
    private double saldoMinimo;

    public CuentaAhorro(int numeroDeCuenta, double saldo, Cliente titular, double interes, double saldoMinimo) {
        super(numeroDeCuenta, saldo, titular);
        this.interes = interes;
        this.saldoMinimo = saldoMinimo;
    }

    public CuentaAhorro() {
        super();
        this.interes = 0;
        this.saldoMinimo = 0;
    }

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
