package foc.es.banco.modeloBancario.classes;

import foc.es.banco.modeloBancario.enums.TipoTransaccion;

import java.util.Date;

public class Transaccion {
    private int id;
    private double cantidad;
    private Date fecha;
    private TipoTransaccion tipo;

    public Transaccion(int id, double cantidad, TipoTransaccion tipo) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = new Date();
        this.tipo = tipo;
    }

    public Transaccion() {
        this.id = 0;
        this.cantidad = 0;
        this.fecha = null;
        this.tipo = null;
    }

    public int getId() {
        return id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }
}
