package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class EstadisticasActivity extends AppCompatActivity implements ViewActivity {

    private TextView ganadasAhorcado;
    private TextView ganadasParaulogic;
    private TextView inicioSesion;
    private TextView ultimasesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        createAllItemsAsGlobalWithGetters();
        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().estadisticasActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        ganadasAhorcado = findViewById(R.id.ganadasAhorcado);
        ganadasParaulogic = findViewById(R.id.ganadasParaulogic);
        inicioSesion = findViewById(R.id.inicioSesion);
        ultimasesion = findViewById(R.id.ultimaSesion);
    }

    public TextView getGanadasAhorcado() {
        return ganadasAhorcado;
    }

    public TextView getGanadasParaulogic() {
        return ganadasParaulogic;
    }

    public TextView getInicioSesion() {
        return inicioSesion;
    }

    public TextView getUltimasesion() {
        return ultimasesion;
    }
}
