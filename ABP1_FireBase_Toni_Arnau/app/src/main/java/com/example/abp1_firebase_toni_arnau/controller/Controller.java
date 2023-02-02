package com.example.abp1_firebase_toni_arnau.controller;

import com.example.abp1_firebase_toni_arnau.view.LoginActivity;
import com.example.abp1_firebase_toni_arnau.view.MainActivity;
import com.example.abp1_firebase_toni_arnau.view.SplashActivity;

public class Controller implements ControllerInterface{
    //Definición de todas las activities como variables globales
    private MainActivity mainActivity;
    private SplashActivity splashActivity;
    private LoginActivity loginActivity;

    //Singleton
    private static Controller controller;

    public static Controller getInstance() {
        if (controller == null) controller = new Controller();
        return  controller;
    }

    //Se instancia todas las activities en el constructor para prevenir nullPointers
    public Controller() {
        this.mainActivity = new MainActivity();
        this.splashActivity = new SplashActivity();
        this.loginActivity = new LoginActivity();
    }

    public void mainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.createAllItemsAsGlobalWithGetters();
    }

    public void splashActivity (SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
        this.splashActivity.createAllItemsAsGlobalWithGetters();
        switchActivity(this.splashActivity, this.mainActivity);
    }

    public void loginActivity (LoginActivity loginActivity){
        this.loginActivity = loginActivity;
        this.loginActivity.createAllItemsAsGlobalWithGetters();
    }

    @Override
    public void createActivityButtons() {

    }
}
