package foc.es.banco.modeloBancario.classes;

public class Cliente {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private static int contador = 0;

    public Cliente(String name, String direccion, String telefono) {
        this.id = contador++;
        this.nombre = name;
        this.direccion = direccion;
        this.telefono = telefono;
        System.out.println("Cliente creado con exito con id: " + id);
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

}
