package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Paraula {
    private String email;
    private int numPalabras;
    private int count;
    private String[] respuestas;
    private String[][] matriz;
    private char[] p1 = {'p', 'r', 'a', 'n', 'i', 'o'};
    private char[] p2 = {'t', 'e', 'l', 'd', 'o', 's', 'a'};


    public Paraula() {
    }

    public Paraula(String email, int numPalabras, int count) {
        this.email = email;
        this.numPalabras = Constants.paraulogics.length;
        this.count = count;
        this.matriz = Constants.paraulogics;
    }

    public boolean siExiste(String palabraIn) {
        String p1String = p1.toString();
        String p2String = p2.toString();

        if (eleccion() == 1) {
            for (int i = 0; i < palabraIn.length(); i++) {
                for (int j = 0; j < p1String.length(); j++) {
                    if (palabraIn.charAt(i) == p1String.charAt(j)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            for (int i = 0; i < palabraIn.length(); i++) {
                for (int j = 0; j < p2String.length(); j++) {
                    if (palabraIn.charAt(i) == p2String.charAt(j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public int eleccion() {
        Random num = new Random();
        int ale = num.nextInt(2) + 1;
        return ale;
    }

    public String[] escogeJuego() {
        int ale = eleccion();
        String[][] matriz = Constants.paraulogics;
        return matriz[ale];
    }

    public boolean ocurrencia(String[] matriz, String palabra) {
        for (String n : matriz) {
            if (n.contains(palabra)) {
                count++;
                return true;
            }
        }
        return false;
    }

    public String[] respAñadida(String palagra) {
        int num = eleccion();
        String[] resp = Constants.paraulogics[num];
        ArrayList<String> temp = new ArrayList<>();
        Arrays.asList(temp);
        temp.add(palagra);
        return (String[]) temp.toArray();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    public void setNumPalabras(int numPalabras) {
        this.numPalabras = numPalabras;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }
}



