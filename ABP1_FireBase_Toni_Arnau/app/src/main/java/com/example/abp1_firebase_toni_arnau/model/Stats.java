package com.example.abp1_firebase_toni_arnau.model;

import com.google.firebase.firestore.FieldValue;

public class Stats {
    private  int ganadasAhorcado;
    private  int ganadasParaulogic;
    private FieldValue fecha;
    private User user;

    public Stats() {
    }

    public Stats(int ganadasAhorcado, int ganadasParaulogic, FieldValue fecha, User user) {
        this.ganadasAhorcado = ganadasAhorcado;
        this.ganadasParaulogic = ganadasParaulogic;
        this.fecha = fecha;
        this.user = user;
    }

    public int getGanadasAhorcado() {
        return ganadasAhorcado;
    }

    public int getGanadasParaulogic() {
        return ganadasParaulogic;
    }

    public User getUser() {
        return user;
    }

    public FieldValue getFecha() {
        return fecha;
    }

    public void setGanadasAhorcado(int ganadasAhorcado) {
        this.ganadasAhorcado = ganadasAhorcado;
    }

    public void setGanadasParaulogic(int ganadasParaulogic) {
        this.ganadasParaulogic = ganadasParaulogic;
    }

    public void setFecha(FieldValue fecha) {
        this.fecha = fecha;
    }
}

