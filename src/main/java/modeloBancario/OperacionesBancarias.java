package modeloBancario;

public interface OperacionesBancarias {
    void transferir(Cuenta origen, Cuenta destino, double cantidad);

    void depositar(Cuenta cuenta, double cantidad);

    void consultarSaldo(Cuenta cuenta);
}
