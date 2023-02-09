package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    private User user;
    private int ganadas;
    private int intentos;
    private boolean acierto;
    private boolean finJuego;

    public Ahorcado(User user, int ganadas, boolean acierto, boolean finJuego) {
        this.user = user;
        this.ganadas = ganadas;
        this.intentos = 5;
        this.acierto = acierto;
        this.finJuego = finJuego;
    }


    private void init() {
    }

    String palabraSecreta = palabraFIn();
    char[] palabraGuiones = cambioGuiones(palabraSecreta);

        do

    {
        //mostrar TextView1 de guiones
        //mostrar TextView2 "Intro letra"
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == leerInput()) {
                palabraGuiones[i] = leerInput();
                acierto = true;
            }
        }
        if (!acierto) intentos--; // mostrar TextView2 "Error not found"

        if (intentos == 0) ; // TextView "Has perdido"

        else {
            finJuego = !aunGuiones(palabraGuiones);
            if (finJuego) ganadas++;

        }
    } while(!finJuego);

}

    private String palabraFIn() {
        String[] matriz = Constants.ahorcado;
        Random r = new Random();
        int x = r.nextInt(matriz.length);
        return matriz[x];
    }

    private char[] cambioGuiones(String palabra) {
        int numletras = palabra.length();
        char[] guiones = new char[numletras];

        for (int i = 0; i < guiones.length; i++) {
            guiones[i] = '_';
        }
        return guiones;
    }

    private boolean aunGuiones(char[] array) {
        for (char c : array) {
            if (c == '_') return true;
        }
        return false;
    }

}

