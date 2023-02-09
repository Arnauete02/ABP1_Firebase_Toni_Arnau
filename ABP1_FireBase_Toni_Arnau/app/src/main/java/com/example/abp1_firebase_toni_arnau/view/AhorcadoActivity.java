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
    private EditText editTextLetra;
    private TextView textViewGuiones;
    private Button buttonBomb;
    private ImageView imageViewBomb;

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
        textViewGuiones = findViewById(R.id.palabraGuionesBomb);
        imageViewBomb = findViewById(R.id.imageBomb);
        editTextLetra = findViewById(R.id.textLetraBomb);
        buttonBomb = findViewById(R.id.buttonBomb);
    }

    public EditText getEditTextLetra() {
        return editTextLetra;
    }

    public TextView getTextViewGuiones() {
        return textViewGuiones;
    }

    public Button getButtonBomb() {
        return buttonBomb;
    }

    public ImageView getImageViewBomb() {
        return imageViewBomb;
    }


    public void setEditTextLetra(EditText editTextLetra) {
        this.editTextLetra = editTextLetra;
    }

    public void setTextViewGuiones(TextView textViewGuiones) {
        this.textViewGuiones = textViewGuiones;
    }

    public void setButtonBomb(Button buttonBomb) {
        this.buttonBomb = buttonBomb;
    }

    public void setImageViewBomb(ImageView imageViewBomb) {
        this.imageViewBomb = imageViewBomb;
    }
}