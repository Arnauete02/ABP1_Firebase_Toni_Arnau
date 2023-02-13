package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    private String email;
    private String palabra;
    private char[] respuestas;
    private int intentos;

    public Ahorcado() {
    }

    public Ahorcado(String email, String palabra, char[] respuestas, int intentos) {
        this.email = email;
        this.palabra = palabra;
        this.respuestas = respuestas;
        this.intentos = intentos;
    }

    // METHOD TO GIVE A RANDOM WORD
    public String palabraRandom() {
        String[] palabra = Constants.ahorcado;

        return palabra[new Random().nextInt(palabra.length)];
    }

    public String getEmail() {
        return email;
    }

    public String getPalabra() {
        return palabra;
    }

    public char[] getRespuestas() {
        return respuestas;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setRespuestas(char[] respuestas) {
        this.respuestas = respuestas;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}

