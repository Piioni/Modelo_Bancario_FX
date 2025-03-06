package foc.es.banco.transaction.model;

public enum TipoTransaccion {
    CREATION("Creation"),
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    ANNULMENT("Annulment"),
    REINTEGRATE("Reintegrate");

    private String name;

    TipoTransaccion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
