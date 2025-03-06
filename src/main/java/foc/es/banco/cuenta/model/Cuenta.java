package foc.es.banco.cuenta.model;

import foc.es.banco.client.model.Client;
import foc.es.banco.transaction.model.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta", discriminatorType = DiscriminatorType.STRING)
@Table(name = "cuenta")
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_cuenta")
    protected int numeroDeCuenta;

    @PositiveOrZero
    @Column(name = "saldo", nullable = false)
    protected double saldo;

    @NotNull
    @OneToOne
    @JoinColumn(name = "cliente_id")
    protected Client titular;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Transaction> transacciones = new ArrayList<>();


    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    // Métodos abstractos
    public abstract void retirar(double cantidad);

    public abstract void actualizarSaldo();

}
