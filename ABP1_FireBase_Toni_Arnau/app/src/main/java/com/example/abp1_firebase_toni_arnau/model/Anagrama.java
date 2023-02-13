package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.Random;

public class Anagrama {
    private String email;
    private String palabraInput;
    private String palabraGrupo;
    private String palabraUno;
    private String palabraDos;
    private String[] respuestas;
    private int ganadasAna;

    public Anagrama() {
    }

    public Anagrama(String email, String palabraInput, String palabraGrupo, String palabraUno, String palabraDos) {
        this.email = email;
        this.palabraInput = palabraInput;
        this.palabraGrupo = palabraGrupo();
        this.palabraUno = palabraUno();
        this.palabraDos = palabraDos();
        this.ganadasAna = 0;
    }

    public Boolean palabrafinal(String palabraInput) {
        if (palabraInput.equals(palabraDos())) {
            return true;
        }
        return false;
    }

    // tiene que ir a pares -> Split - A y b
    public String palabraGrupo() {
        String[] matriz = Constants.anagrama;
        Random r = new Random();
        int x = r.nextInt(matriz.length);
        String p = matriz[x];
        return p;
    }

    public String palabraUno() {
        String []p = palabraGrupo().split("-");
        palabraUno = p[0];
        return palabraUno;
    }

    public String palabraDos() {
        String []p = palabraGrupo().split("-");
        palabraDos = p[1];
        return palabraDos;

    }

    public void setPalabraInput(String palabraInput) {
        this.palabraInput = palabraInput;
    }

    public void setPalabraGrupo(String palabraGrupo) {
        this.palabraGrupo = palabraGrupo;
    }

    public void setPalabraUno(String palabraUno) {
        this.palabraUno = palabraUno;
    }

    public void setPalabraDos(String palabraDos) {
        this.palabraDos = palabraDos;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPalabraInput() {
        return palabraInput;
    }

    public String getPalabraGrupo() {
        return palabraGrupo;
    }

    public String getPalabraUno() {
        return palabraUno;
    }

    public String getPalabraDos() {
        return palabraDos;
    }

    public int getGanadasAna() {
        return ganadasAna;
    }

    public void setGanadasAna(int ganadasAna) {
        this.ganadasAna = ganadasAna;
    }
}
