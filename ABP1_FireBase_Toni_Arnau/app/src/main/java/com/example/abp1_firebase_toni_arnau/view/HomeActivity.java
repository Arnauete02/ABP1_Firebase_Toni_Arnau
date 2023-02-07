package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class HomeActivity extends AppCompatActivity implements ViewActivity {
    private Button botonLogout;
    private Button botonPeril;
    private Button botonPalabra;
    private Button botonAhorcado;
    private Button botonLetras;
    private Button botonStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        createAllItemsAsGlobalWithGetters();
        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().homeActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        botonLogout =  findViewById(R.id.buttonLogout);
        botonPeril = findViewById(R.id.buttonPerfil);
        botonPalabra = findViewById(R.id.buttonPalabra);
        botonAhorcado = findViewById(R.id.buttonAhorcado);
        botonLetras = findViewById(R.id.buttonLetras);
        botonStats = findViewById(R.id.buttonStats);
    }


    public Button getBotonLogout() {
        return botonLogout;
    }

    public Button getBotonPeril() {
        return botonPeril;
    }

    public Button getBotonPalabra() {
        return botonPalabra;
    }

    public Button getBotonAhorcado() {
        return botonAhorcado;
    }

    public Button getBotonLetras() {
        return botonLetras;
    }

    public Button getBotonStats() {
        return botonStats;
    }
}