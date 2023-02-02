package com.example.abp1_firebase_toni_arnau.controller;

import com.example.abp1_firebase_toni_arnau.view.MainActivity;

public class Controller implements ControllerInterface{
    //Definición de todas las activities como variables globales
    private MainActivity mainActivity;

    //Singleton
    private static Controller controller;

    public static Controller getInstance() {
        if (controller == null) controller = new Controller();
        return  controller;
    }

    //Se instancia todas las activities en el constructor para prevenir nullPointers
    public Controller() {
        this.mainActivity = new MainActivity();
    }

    public void mainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.createAllItemsAsGlobalWithGetters();
    }

    @Override
    public void createActivityButtons() {

    }
}
