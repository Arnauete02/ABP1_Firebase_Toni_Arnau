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
    char[] palabraConGuiones;

    public Ahorcado() {
    }

    public Ahorcado(String email, String palabra, char[] respuestas, int intentos) {
        this.email = email;
        this.palabra = palabraRandom();
        this.respuestas = respuestas;
        this.intentos = 5;
        this.palabraConGuiones = cambioGuiones(this.palabra);
    }

    // METHOD TO GIVE A RANDOM WORD
    public String palabraRandom() {
        String[] palabra = Constants.ahorcado;

        return palabra[new Random().nextInt(palabra.length)];
    }

    public   char[] cambioGuiones (String palabra){
        char[] guiones = new char[palabra.length()];

        for (int i = 0; i < guiones.length; i++) {
            guiones[i] = '_';
        }
        return guiones;
    }

    public   boolean aunGuiones(char[]array){
        for(char c:array){
            if(c=='_')return true;
        }
        return false;
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

    public char[] getPalabraConGuiones() {
        return palabraConGuiones;
    }
}

