package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

public class Paraula {
    private User user;
    private int ganadas;
    private int count;
    private boolean fin;

    public Paraula() {
    }

    public Paraula(User user, int ganadas, int count, boolean fin) {
        this.user = user;
        this.ganadas = ganadas;
        this.count = count;
        this.fin = fin;
    }


    public boolean juegofin() {
        String[][] matriz = Constants.paraulogics;
        if (count == matriz.length) {
            return true;
        }
        return false;
    }

    public boolean palabraExiste(String palabraInput) {
        String[][] matriz = Constants.paraulogics;
        for (String[] m : matriz) {
            for (String n : m) {
                if (n.contains(palabraInput)) {
                    count++;
                    return true;
                }
            }
        }
        return false;
    }

    public int getGanadas() {
        return ganadas;
    }

    public int getCount() {
        return count;
    }

    public boolean isFin() {
        return fin;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }
}




