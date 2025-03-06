package foc.es.banco.client.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "The name is required")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Address is required")
    @Column(name = "address")
    private String address;

    @NotNull(message = "A phone number is required")
    @Column(name = "phone")
    private String phone;



}
