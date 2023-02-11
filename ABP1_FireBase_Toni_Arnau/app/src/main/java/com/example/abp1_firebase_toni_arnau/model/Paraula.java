package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.utils.Constants;

import java.util.Random;

public class Paraula {
    private User user;
    private int ganadasPara;
    private int numPalabras;
    private int count;

    public Paraula() {
    }

    public Paraula(User user, int ganadas,int numPalabras, int count) {
        this.user = user;
        this.ganadasPara = ganadasPara;
        this.count = count;
        this.numPalabras = numPalabras;
    }


    public int escogerJuego(){
        String[][] matriz = Constants.paraulogics;
        Random r = new Random();
        int x = (int) (r.nextInt() * 100);
        return x;
    }

    public String[] escogePartida() {
        int num = escogerJuego();
        String[][] matriz = Constants.paraulogics;
        String[]partida = matriz[num];
        return partida;
    }

    public boolean palabraExiste(String palabraInput) {
        String[] temp =escogePartida();
        for (String m : temp) {
                if (m.equals(palabraInput)) {
                    count++;
                    return true;
                }
        }
        return false;
    }

    public void insertPalabra(String input){
        String[] matriz = escogePartida();
        matr
        for(String m : matriz){


        }
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




