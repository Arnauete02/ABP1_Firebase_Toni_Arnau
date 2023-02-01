package com.example.abp1_firebase_toni_arnau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.view.ViewActivity;

public class ExtraActivity extends AppCompatActivity implements ViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.Theme_ABP1_FireBase_Toni_Arnau);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().extraActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {

    }
}