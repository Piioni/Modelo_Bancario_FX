package foc.es.banco.transaction.model;

import foc.es.banco.cuenta.model.Cuenta;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "amount")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;




}
