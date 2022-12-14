package Clases;

import java.io.Serializable;
import java.util.List;

public class Acount implements Serializable {


    private long idBA;
    private String iban;

    private double balance;

    private User user;

    public Acount(String iban, double balance, User user) {
        this.iban = iban;
        this.balance = balance;
        this.user = user;
    }

    public long idBA() {
        return idBA;
    }

    public Acount setIdBA(long idBA) {
        this.idBA = idBA;
        return this;
    }

    public String iban() {
        return iban;
    }

    public Acount setIban(String iban) {
        this.iban = iban;
        return this;
    }

    public double balance() {
        return balance;
    }

    public Acount setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public User user() {
        return user;
    }

    public Acount setUser(User user) {
        this.user = user;
        return this;
    }
}
