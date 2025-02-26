package modeloBancario;

public class CuentaCorriente extends Cuenta implements OperacionesBancarias {
    private final double interes = 0.15;

    public CuentaCorriente(int numeroDeCuenta, double saldo, Cliente titular) {
        super(numeroDeCuenta, saldo, titular);
    }

    public CuentaCorriente() {
        super();
    }

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
