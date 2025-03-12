package foc.es.banco.modeloBancario.classes;

public class Cliente {
    private int id;
    private String name;
    private String direccion;
    private String telefono;
    private static int contador = 0;

    public Cliente(String name, String direccion, String telefono) {
        this.id = contador++;
        this.name = name;
        this.direccion = direccion;
        this.telefono = telefono;
        System.out.println("Cliente creado con exito con id: " + id);
    }

    public Cliente() {
        this.id = 0;
        this.name = null;
        this.direccion = null;
        this.telefono = null;
    }

    public int getId() {
        return id;
    }
}
