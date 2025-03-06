package foc.es.banco.cuenta.model;

import foc.es.banco.client.model.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta", discriminatorType = DiscriminatorType.STRING)
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int numeroDeCuenta;

    @PositiveOrZero
    @Column(name = "saldo", nullable = false)
    protected double saldo;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Cliente titular;



    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    // Métodos abstractos
    public abstract void retirar(double cantidad);

    public abstract void actualizarSaldo();

}
