package com.example.abp1_firebase_toni_arnau.controller;

import static android.provider.Settings.System.getString;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.model.User;
import com.example.abp1_firebase_toni_arnau.utils.Constants;
import com.example.abp1_firebase_toni_arnau.view.ExtraActivity;
import com.example.abp1_firebase_toni_arnau.utils.Providers;
import com.example.abp1_firebase_toni_arnau.view.AhorcadoActivity;
import com.example.abp1_firebase_toni_arnau.view.EstadisticasActivity;
import com.example.abp1_firebase_toni_arnau.view.HomeActivity;
import com.example.abp1_firebase_toni_arnau.view.LoginActivity;
import com.example.abp1_firebase_toni_arnau.view.MainActivity;
import com.example.abp1_firebase_toni_arnau.view.ParaulogicActivity;
import com.example.abp1_firebase_toni_arnau.view.PerfilActivity;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Controller implements ControllerInterface{
    public static final String default_web_client_id = "28931008152-jgtpdrmfpcdeoffse8luipdme6g3unn3.apps.googleusercontent.com";
    private User user;

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
        this.loginActivity = new LoginActivity();
        this.homeActivity = new HomeActivity();
        this.ahorcadoActivity = new AhorcadoActivity();
        this.paraulogicActivity = new ParaulogicActivity();
        this.perfilActivity = new PerfilActivity();
        this.estadisticasActivity = new EstadisticasActivity();
        this.extraActivity = new ExtraActivity();

        this.user = new User();
    }

    public void mainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.createAllItemsAsGlobalWithGetters();
        switchActivity(this.mainActivity, this.loginActivity);
    }

    public void loginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.loginActivity.createAllItemsAsGlobalWithGetters();
        createActivityButtons(loginActivity);
    }

    public void homeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
        this.homeActivity.createAllItemsAsGlobalWithGetters();
        createActivityButtons(homeActivity);
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

    //SharedPreferences
    private void saveSession(Providers provider) {
        SharedPreferences.Editor prefs = this.loginActivity.getSharedPreferences(
                "PREFERENCES_FILE_KEY", Context.MODE_PRIVATE).edit();
        prefs.putString("email", this.loginActivity.getMail().getText().toString());
        prefs.putString("provider", provider.toString());
        prefs.apply();
        switchActivity(this.loginActivity, this.homeActivity);
    }

    private void clearSession() {
        SharedPreferences.Editor prefs = this.loginActivity.getSharedPreferences(
                "PREFERENCES_FILE_KEY", Context.MODE_PRIVATE).edit();
        prefs.clear();
        prefs.apply();
        switchActivity(this.homeActivity, this.mainActivity);
    }

    private boolean checkSession () {
        SharedPreferences prefs = this.loginActivity.getSharedPreferences("PREFERENCES_FILE_KEY", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String provider = prefs.getString("provider", null);
        if (email != null) {
            return true;
        }
        return false;
    }

    @Override
    public void createActivityButtons(Activity activity) {
        if (activity == this.loginActivity) {
            SharedPreferences prefs = this.loginActivity.getSharedPreferences(
                    "PREFERENCES_FILE_KEY", Context.MODE_PRIVATE);

            if (checkSession()) {
                switchActivity(this.loginActivity, this.homeActivity);
            }

            this.loginActivity.getRegisterButton().setOnClickListener(new View.OnClickListener() {
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
                                            saveSession(Providers.LOGIN);
                                            user.setEmail(mail);
                                        } else {
                                            showAlert(loginActivity, "El correo ya está registrado.");
                                        }
                                    }
                                });
                    } else {
                        showAlert(loginActivity, "Error en el registro.");
                    }
                }
            });

            this.loginActivity.getLoginButton().setOnClickListener(new View.OnClickListener() {
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
                                            saveSession(Providers.LOGIN);
                                            user.setEmail(mail);
                                        } else {
                                            showAlert(loginActivity, "Error en el login.");
                                        }

                                    }
                                });
                    } else {
                        showAlert(loginActivity, "Error en el login.");
                    }
                }
            });

            this.loginActivity.getGoogleButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (activity == this.homeActivity) {
            this.homeActivity.getBotonLogout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    clearSession();
                    switchActivity(homeActivity, loginActivity);
                }
            });

            this.homeActivity.getBotonAhorcado().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchActivity(homeActivity, ahorcadoActivity);
                }
            });

            this.homeActivity.getBotonLetras().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchActivity(homeActivity, extraActivity);
                }
            });

            this.homeActivity.getBotonPalabra().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchActivity(homeActivity, paraulogicActivity);
                }
            });

            this.homeActivity.getBotonPeril().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchActivity(homeActivity, perfilActivity);
                }
            });

            this.homeActivity.getBotonStats().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchActivity(homeActivity, estadisticasActivity);
                }
            });
        } else if (activity == this.perfilActivity) {

        }
    }

    public void returnCollectedData(User user) {

    }

}
