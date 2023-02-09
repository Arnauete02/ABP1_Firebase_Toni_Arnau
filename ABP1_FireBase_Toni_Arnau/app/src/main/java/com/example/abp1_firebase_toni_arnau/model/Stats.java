package com.example.abp1_firebase_toni_arnau.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.util.Date;

public class Stats {
    private String email;
    private int ganadasAhorcado;
    private int ganadasParaulogic;
    private int numeroInicios;
    private Date fecha;

    public Stats() {
    }

    public Stats(String email, int ganadasAhorcado, int ganadasParaulogic, int numeroInicios, Date fecha) {
        this.email = email;
        this.ganadasAhorcado = ganadasAhorcado;
        this.ganadasParaulogic = ganadasParaulogic;
        this.numeroInicios = numeroInicios;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }
}

