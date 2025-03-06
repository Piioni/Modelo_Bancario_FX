package foc.es.banco.transaction.model;

import lombok.Data;

import java.util.List;

@Data
public class TransactionHistory {
    List<Transaction> transactionList;

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public void removeTransaction(Transaction transaction) {
        transactionList.remove(transaction);
    }

}
