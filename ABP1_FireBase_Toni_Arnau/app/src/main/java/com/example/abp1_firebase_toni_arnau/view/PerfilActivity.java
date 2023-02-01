package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class PerfilActivity extends AppCompatActivity  implements ViewActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.Theme_ABP1_FireBase_Toni_Arnau);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().perfilActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {

    }
}