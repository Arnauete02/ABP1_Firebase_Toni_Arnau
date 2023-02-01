package com.example.abp1_firebase_toni_arnau.controller;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.abp1_firebase_toni_arnau.ExtraActivity;
import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.utils.Constants;
import com.example.abp1_firebase_toni_arnau.view.AhorcadoActivity;
import com.example.abp1_firebase_toni_arnau.view.EstadisticasActivity;
import com.example.abp1_firebase_toni_arnau.view.HomeActivity;
import com.example.abp1_firebase_toni_arnau.view.LoginActivity;
import com.example.abp1_firebase_toni_arnau.view.MainActivity;
import com.example.abp1_firebase_toni_arnau.view.ParaulogicActivity;
import com.example.abp1_firebase_toni_arnau.view.PerfilActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Controller implements ControllerInterface{
    //Definición de todas las activities como variables globales
    private MainActivity mainActivity;
    private LoginActivity loginActivity;
    private HomeActivity homeActivity;
    private AhorcadoActivity ahorcadoActivity;
    private ParaulogicActivity paraulogicActivity;
    private PerfilActivity perfilActivity;
    private EstadisticasActivity estadisticasActivity;
    private ExtraActivity extraActivity;

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

    public void loginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.loginActivity.createAllItemsAsGlobalWithGetters();
        createActivityButtons();
    }

    public void homeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
        this.homeActivity.createAllItemsAsGlobalWithGetters();
    }

    public void ahorcadoActivity(AhorcadoActivity ahorcadoActivity) {
        this.ahorcadoActivity = ahorcadoActivity;
        this.ahorcadoActivity.createAllItemsAsGlobalWithGetters();
    }

    public void paraulogicActivity(ParaulogicActivity paraulogicActivity) {
        this.paraulogicActivity = paraulogicActivity;
        this.paraulogicActivity.createAllItemsAsGlobalWithGetters();
    }

    public void perfilActivity(PerfilActivity perfilActivity) {
        this.perfilActivity = perfilActivity;
        this.perfilActivity.createAllItemsAsGlobalWithGetters();
    }

    public void estadisticasActivity(EstadisticasActivity estadisticasActivity) {
        this.estadisticasActivity = estadisticasActivity;
        this.estadisticasActivity.createAllItemsAsGlobalWithGetters();
    }

    public void extraActivity(ExtraActivity extraActivity) {
        this.extraActivity = extraActivity;
        this.extraActivity.createAllItemsAsGlobalWithGetters();
    }

    @Override
    public void createActivityButtons() {
        Button registerButton = this.loginActivity.getRegisterButton();
        Button loginButton = this.loginActivity.getLoginButton();
        Button googleButton = this.loginActivity.getGoogleButton();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = loginActivity.getMail().getText().toString();
                String password = loginActivity.getPassword().getText().toString();

                if (!mail.isEmpty() && !password.isEmpty()) {

                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(mail, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //TODO: código cuando el login es correcto.
                                    } else {
                                        //TODO: código cuando el login no es correcto.
                                    }
                                }
                            });
                } else {
                    showAlert(loginActivity, "Error en el login");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = loginActivity.getMail().getText().toString();
                String password = loginActivity.getPassword().getText().toString();

                if (!mail.isEmpty() && !password.isEmpty()) {

                    FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(mail, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //TODO: código cuando el login es correcto.
                                    } else {
                                        //TODO: código cuando el login no es correcto.
                                    }

                                }
                            });
                } else {
                    showAlert(loginActivity, "Error en el login");
                }
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
