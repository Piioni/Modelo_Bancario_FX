package foc.es.banco.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;
    private String direccion;
    private String telefono;

    public Cliente(int id, String nome, String direccion, String telefono) {
        this.id = id;
        this.nome = nome;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente() {
        this.id = 0;
        this.nome = null;
        this.direccion = null;
        this.telefono = null;
    }
}
