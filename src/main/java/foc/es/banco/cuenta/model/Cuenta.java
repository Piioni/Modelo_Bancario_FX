package foc.es.banco.cuenta.model;

import foc.es.banco.client.model.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta", discriminatorType = DiscriminatorType.STRING)
@Table(name = "cuenta")
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int numeroDeCuenta;

    @PositiveOrZero
    @Column(name = "saldo", nullable = false)
    protected double saldo;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    protected Client titular;



    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    // Métodos abstractos
    public abstract void retirar(double cantidad);

    public abstract void actualizarSaldo();

}
