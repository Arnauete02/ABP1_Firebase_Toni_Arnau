package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class AhorcadoActivity extends AppCompatActivity implements ViewActivity {
    private TextView palabraGuionesBomb;
    private ImageView imageBomb;
    private EditText textLetraBomb;
    private TextView textRespuestasBomb;
    private Button buttonBomb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);
        createAllItemsAsGlobalWithGetters();
        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().ahorcadoActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        palabraGuionesBomb = findViewById(R.id.palabraGuionesBomb);
        imageBomb = findViewById(R.id.imageBomb);
        textLetraBomb = findViewById(R.id.textLetraBomb);
        textRespuestasBomb = findViewById(R.id.textRespuestasBomb);
        buttonBomb = findViewById(R.id.buttonBomb);
    }

    public TextView getPalabraGuionesBomb() {
        return palabraGuionesBomb;
    }

    public void setPalabraGuionesBomb(TextView palabraGuionesBomb) {
        this.palabraGuionesBomb = palabraGuionesBomb;
    }

    public ImageView getImageBomb() {
        return imageBomb;
    }

    public void setImageBomb(ImageView imageBomb) {
        this.imageBomb = imageBomb;
    }

    public EditText getTextLetraBomb() {
        return textLetraBomb;
    }

    public void setTextLetraBomb(EditText textLetraBomb) {
        this.textLetraBomb = textLetraBomb;
    }

    public TextView getTextRespuestasBomb() {
        return textRespuestasBomb;
    }

    public void setTextRespuestasBomb(TextView textRespuestasBomb) {
        this.textRespuestasBomb = textRespuestasBomb;
    }

    public Button getButtonBomb() {
        return buttonBomb;
    }

    public void setButtonBomb(Button buttonBomb) {
        this.buttonBomb = buttonBomb;
    }
}