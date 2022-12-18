package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private  long idUser;
    private  String name;
    private  String dni;
    private  String surName;

    private  String pass;

    private  List<Acount> acountList = new ArrayList<>();


    public User() {
    }

    public User(String dni, String pass) {
        this.dni = dni;
        this.pass = pass;
    }


    public User(String name, String dni, String surName, String pass) {
        this.name = name;
        this.dni = dni;
        this.surName = surName;
        this.pass = pass;
    }

    public long idUser() {
        return idUser;
    }

    public User setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }

    public String name() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String dni() {
        return dni;
    }

    public User setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String surName() {
        return surName;
    }

    public User setSurName(String surName) {
        this.surName = surName;
        return this;
    }

    public String pass() {
        return pass;
    }

    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public List<Acount> acountList() {
        return acountList;
    }

    public User setAcountList(List<Acount> acountList) {
        this.acountList = acountList;
        return this;
    }
}
