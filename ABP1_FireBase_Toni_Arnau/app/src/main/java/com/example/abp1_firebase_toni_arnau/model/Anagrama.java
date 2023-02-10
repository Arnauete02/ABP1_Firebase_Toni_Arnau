package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.Random;

public class Anagrama {
    private User user;
    private String palabraInput;
    private String palabraUno;
    private String palabraDos;

    public Anagrama() {
    }

    public Anagrama(User user, String palabraInput, String palabraUno, String palabraDos) {
        this.user = user;
        this.palabraInput = palabraInput;
        this.palabraUno = palabraUno;
        this.palabraDos = palabraDos;
    }

    public Boolean palabrafinal(String palabraInput) {
        if (palabraInput.equals(palabraDos)) {
            return true;
        }
        return false;
    }

    // tiene que ir a pares -> Split - A y b
    public String palabra() {
        String[] matriz = Constants.anagrama;
        Random r = new Random();
        int x = r.nextInt(matriz.length);
        String p = matriz[x];
        String[] ppartes = p.split("-");
        palabraUno = ppartes[0];
        palabraDos = ppartes[1];
        return palabraUno;
    }

    public String getPalabraUno() {
        return palabraUno;
    }

    public String getPalabraDos() {
        return palabraDos;
    }
}
