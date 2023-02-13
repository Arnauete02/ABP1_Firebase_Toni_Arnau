package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.Random;

public class Paraula {
    private String email;
    private int ganadasPara;
    private int numPalabras;
    private int count;
    private String imagen;

    public Paraula() {
    }

    public Paraula(User user, int ganadas, int numPalabras, int count) {
        this.email = email;
        this.ganadasPara = ganadasPara;
        this.count = count;
        this.numPalabras = numPalabras;
    }


    public boolean palabraExiste(String palabraInput) {
        String[][] matriz = Constants.paraulogics;
        for (String[] m : matriz) {
            for(String n : m) {
                if (m.equals(palabraInput)) {
                    count++;
                    return true;
                }
            }
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGanadasPara() {
        return ganadasPara;
    }

    public void setGanadasPara(int ganadasPara) {
        this.ganadasPara = ganadasPara;
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

    public Object getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}




