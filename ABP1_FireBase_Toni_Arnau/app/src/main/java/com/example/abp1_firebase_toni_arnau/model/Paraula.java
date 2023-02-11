package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

public class Paraula {
    private User user;
    private int ganadasPara;
    private int count;

    public Paraula() {
    }

    public Paraula(User user, int ganadas, int count) {
        this.user = user;
        this.ganadasPara = ganadasPara;
        this.count = count;
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

    public int getGanadasPara() {
        return ganadasPara;
    }

    public int getCount() {
        return count;
    }

    public void setGanadasPara(int ganadas) {
        this.ganadasPara = ganadas;
    }

    public void setCount(int count) {
        this.count = count;
    }

}




