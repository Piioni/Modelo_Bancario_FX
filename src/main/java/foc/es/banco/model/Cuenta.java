package foc.es.banco.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta", discriminatorType = DiscriminatorType.STRING)
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int numeroDeCuenta;

    @Column(name = "saldo", nullable = false)
    protected double saldo;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Cliente titular;

    // Constructores
    public Cuenta(int numeroDeCuenta, double saldo, Cliente titular) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldo;
        this.titular = titular;
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

}
