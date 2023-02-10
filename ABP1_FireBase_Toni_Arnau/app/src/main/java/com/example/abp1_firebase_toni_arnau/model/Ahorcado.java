package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    private User user;
    private int ganadas;
    private int intentos;
    private boolean acierto;
    private boolean finJuego;

    public Ahorcado() {
    }

    public Ahorcado(User user, int ganadas, boolean acierto, boolean finJuego) {
        this.user = user;
        this.ganadas = ganadas;
        this.intentos = 5;
        this.acierto = acierto;
        this.finJuego = false;
    }


    public char[] cambioLetraGuion(String palabraSecreta, char[] palabraGuiones) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == Controller.getInstance().letraAhorcado()) {
                palabraGuiones[i] = Controller.getInstance().letraAhorcado();
                return palabraGuiones;
            }
        }
        return null;
    }

    public String palabraFIn() {
        String[] matriz = Constants.ahorcado;
        Random r = new Random();
        int x = r.nextInt(matriz.length);
        return matriz[x];
    }

    public char[] cambioGuiones(String palabra) {
        int numletras = palabra.length();
        char[] guiones = new char[numletras];

        for (int i = 0; i < guiones.length; i++) {
            guiones[i] = '_';
        }
        return guiones;
    }

    public boolean aunGuiones(char[] array) {
        for (char c : array) {
            if (c == '_') return true;
        }
        return false;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getIntentos() {
        return intentos;
    }

    public boolean isAcierto() {
        return acierto;
    }

    public boolean isFinJuego() {
        return finJuego;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public void setAcierto(boolean acierto) {
        this.acierto = acierto;
    }

    public void setFinJuego(boolean finJuego) {
        this.finJuego = finJuego;
    }
}

