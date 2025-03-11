package foc.es.banco.modeloBancario.classes;

public class Cliente {
    private int id;
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
