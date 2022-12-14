package Clases;

import java.io.Serializable;
import java.util.Date;

public class transaction implements Serializable {
    private long idTrans;
    private long idAccountStart;
    private long idAccountEnd;

    private double money;

    private Date date;

    public transaction(long idTrans, long idAccountStart, long idAccountEnd, double money, Date date) {
        this.idTrans = idTrans;
        this.idAccountStart = idAccountStart;
        this.idAccountEnd = idAccountEnd;
        this.money = money;
        this.date = date;
    }

    public long idTrans() {
        return idTrans;
    }

    public transaction setIdTrans(long idTrans) {
        this.idTrans = idTrans;
        return this;
    }

    public long idAccountStart() {
        return idAccountStart;
    }

    public transaction setIdAccountStart(long idAccountStart) {
        this.idAccountStart = idAccountStart;
        return this;
    }

    public long idAccountEnd() {
        return idAccountEnd;
    }

    public transaction setIdAccountEnd(long idAccountEnd) {
        this.idAccountEnd = idAccountEnd;
        return this;
    }

    public double money() {
        return money;
    }

    public transaction setMoney(double money) {
        this.money = money;
        return this;
    }

    public Date date() {
        return date;
    }

    public transaction setDate(Date date) {
        this.date = date;
        return this;
    }
}
