package foc.es.banco.modeloBancario.enums;

public enum TipoTransaccion {
    CREACION("Creacion"),
    DEPOSITO("Deposito"),
    REINTEGRO("Reintegro"),
    ANULACION("Anulacion"),;


    private String tipoTransaccion;

    TipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }


}
