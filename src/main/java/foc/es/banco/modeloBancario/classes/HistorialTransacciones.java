package foc.es.banco.modeloBancario.classes;

import java.util.ArrayList;
import java.util.List;

public class HistorialTransacciones {
    List<Transaccion> transacciones;

    public HistorialTransacciones() {
        this.transacciones = new ArrayList<>();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}
