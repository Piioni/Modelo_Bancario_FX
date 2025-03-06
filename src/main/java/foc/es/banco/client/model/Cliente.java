package foc.es.banco.client.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String name;

    @NotNull(message = "La dirección es obligatoria")
    @Column(name = "direccion")
    private String direccion;

    @NotNull(message = "El teléfono es obligatorio")
    @Column(name = "telefono")
    private String telefono;



}
