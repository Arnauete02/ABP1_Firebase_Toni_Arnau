package com.example.abp1_firebase_toni_arnau.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.lang.reflect.Field;
import java.util.Date;

public class Stats {
    private String email;
    private int ganadasAhorcado;
    private int ganadasParaulogic;
    private int numeroInicios;
    private String fecha;

    public Stats() {
    }

    public Stats(String email, int ganadasAhorcado, int ganadasParaulogic, int numeroInicios, String fecha) {
        this.email = email;
        this.ganadasAhorcado = ganadasAhorcado;
        this.ganadasParaulogic = ganadasParaulogic;
        this.numeroInicios = numeroInicios;
        this.fecha = fecha;
    }

    public Stats(String email, int numeroInicios, String fecha) {
        this.email = email;
        this.numeroInicios = numeroInicios;
        this.fecha = fecha;
    }

    public void setGanadasAhorcado(int ganadasAhorcado) {
        this.ganadasAhorcado = ganadasAhorcado;
    }

    public void setGanadasParaulogic(int ganadasParaulogic) {
        this.ganadasParaulogic = ganadasParaulogic;
    }

    public String getEmail() {
        return email;
    }

    public int getGanadasAhorcado() {
        return ganadasAhorcado;
    }

    public int getGanadasParaulogic() {
        return ganadasParaulogic;
    }

    public int getNumeroInicios() {
        return numeroInicios;
    }

    public String getFecha() {
        return fecha;
    }
}

