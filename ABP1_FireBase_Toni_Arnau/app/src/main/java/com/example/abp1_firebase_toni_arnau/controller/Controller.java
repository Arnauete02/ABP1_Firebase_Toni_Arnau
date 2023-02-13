package com.example.abp1_firebase_toni_arnau.controller;

import static android.content.Intent.getIntent;
import static android.provider.Settings.System.getString;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.dao.Dao;
import com.example.abp1_firebase_toni_arnau.model.Stats;
import com.example.abp1_firebase_toni_arnau.model.Ahorcado;
import com.example.abp1_firebase_toni_arnau.model.Anagrama;
import com.example.abp1_firebase_toni_arnau.model.Paraula;
import com.example.abp1_firebase_toni_arnau.model.User;
import com.example.abp1_firebase_toni_arnau.utils.Constants;
import com.example.abp1_firebase_toni_arnau.utils.Contador;
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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;

public class Controller implements ControllerInterface {
    private String email;

    private User user;
    private Stats stats;
    private Dao dao;
    private Ahorcado ahorcado;
    private Paraula paraula;
    private Anagrama anagrama;

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
        return controller;
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
        this.stats = new Stats();
        this.dao = new Dao();
        this.ahorcado = new Ahorcado();
        this.paraula = new Paraula();
        this.anagrama = new Anagrama();
    }

    public void mainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mainActivity.createAllItemsAsGlobalWithGetters();
        switchActivity(this.mainActivity, this.loginActivity);
    }

    public void loginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.loginActivity.createAllItemsAsGlobalWithGetters();
        createLoginActivityEvents();
    }

    public void homeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
        this.homeActivity.createAllItemsAsGlobalWithGetters();
        createHomeActivityEvents();
    }

    public void ahorcadoActivity(AhorcadoActivity ahorcadoActivity) {
        this.ahorcadoActivity = ahorcadoActivity;
        this.ahorcadoActivity.createAllItemsAsGlobalWithGetters();
        createAhorcadoActivityEvents();
    }

    public void paraulogicActivity(ParaulogicActivity paraulogicActivity) {
        this.paraulogicActivity = paraulogicActivity;
        this.paraulogicActivity.createAllItemsAsGlobalWithGetters();
        createParaulogicActivityEvents();
    }

    public void perfilActivity(PerfilActivity perfilActivity) {
        this.perfilActivity = perfilActivity;
        this.perfilActivity.createAllItemsAsGlobalWithGetters();
        createProfileActivityEvents();
    }

    public void estadisticasActivity(EstadisticasActivity estadisticasActivity) {
        this.estadisticasActivity = estadisticasActivity;
        this.estadisticasActivity.createAllItemsAsGlobalWithGetters();
        createEstadisticasActivityEvents();
    }

    public void extraActivity(ExtraActivity extraActivity) {
        this.extraActivity = extraActivity;
        this.extraActivity.createAllItemsAsGlobalWithGetters();
        createExtraActivityEvents();
    }

    //METHODS OF ACTIVTIES TO CHECK EVENT'S (CLICK, ETC.)

    // LOGIN
    private void createLoginActivityEvents() {
        SharedPreferences prefs = this.loginActivity.getSharedPreferences(
                "PREFERENCES_FILE_KEY", Context.MODE_PRIVATE);

        //CHECK SESSION WITH SHEARED PREFERENCES, IF I DIDN'T DO LOG OUT
        if (checkSession()) {
            switchActivity(this.loginActivity, this.homeActivity);
            this.email = checkEmail();
            dao.saveStats_init(this.email);
        }

        // REGISTER WITH EMAIL & PASSWORD
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
                                        email = mail;

                                        saveSession();
                                        user.setEmail(mail);
                                        user.setProvider(Providers.LOGIN);

                                        dao.saveLogin(user);
                                        dao.saveStats_init(mail);
                                    } else {
                                        showAlert(loginActivity, "Error en el registro.");
                                    }
                                }
                            });
                } else {
                    showAlert(loginActivity, "Error en el registro.");
                }
            }
        });

        // LOGIN WITH EMAIL & PASSWORD
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
                                        email = mail;

                                        saveSession();
                                        user.setEmail(mail);
                                        user.setProvider(Providers.LOGIN);

                                        dao.saveStats_init(mail);
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

        // LOGIN WITH GOOGLE
        this.loginActivity.getGoogleButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(loginActivity.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(loginActivity, googleSignInOptions);

                googleSignInClient.signOut();

                loginActivity.startActivityForResult(googleSignInClient.getSignInIntent(), Constants.GOOGLE_SIGN_IN);
            }
        });
    }

    // HOME
    private void createHomeActivityEvents() {
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
    }

    // PROFILE
    private void createProfileActivityEvents() {
        if (checkSession()) {
            dao.getUser(checkEmail());
        } else {
            dao.getUser(user.getEmail());
        }

        // DO IT BECAUSE I CAN'T MODIFY MY GOOGLE EMAIL ACCOUNT
        if (Providers.valueOf(checkProvider()) == Providers.GOOGLE) {
            this.perfilActivity.getEditText_mail_perfil().setEnabled(false);
            this.perfilActivity.getEditText_mail_perfil().setFocusable(false);
        }

        this.perfilActivity.getButtonPerfil().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setEmail(String.valueOf(perfilActivity.getEditText_mail_perfil().getText()));
                user.setName(String.valueOf(perfilActivity.getEditText_nombre().getText()));
                user.setProvider(Providers.valueOf(perfilActivity.getTextViewProvider().getText().toString()));
                user.setUsername(String.valueOf(perfilActivity.getEditText_alias().getText()));

                dao.saveLogin(user);

                Toast.makeText(perfilActivity, "Se ha guardado correctamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // STATS
    private void createEstadisticasActivityEvents() {
        dao.getStat(email);
    }

    // AHORCADO
    private void createAhorcadoActivityEvents() {
        dao.existsAhorcado(email);


        this.ahorcadoActivity.getButtonBomb().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.getAhorcado(email, "class");

                String palabraGuiones = ahorcadoActivity.getPalabraGuionesBomb().getText().toString();
                String letra = ahorcadoActivity.getTextLetraBomb().getText().toString();
                char[] respuestas = new char[0];


                for (int i = 0; i < ahorcado.getPalabra().length(); i++) {
                    palabraGuiones += " _ ";

                    if (ahorcado.getPalabra().charAt(i) == letra.charAt(0)) {
                        if (ahorcado.getRespuestas().length != 0) {
                            String respuesta = String.valueOf(ahorcado.getRespuestas());
                            respuesta += letra;

                            respuestas = respuesta.toCharArray();
                        } else {
                            respuestas = new char[1];
                            respuestas[0] = letra.charAt(0);
                        }

                        if (ahorcado.getPalabra().charAt(i) == letra.charAt(0)) {
                            palabraGuiones += letra;
                        }

                        ahorcadoActivity.getPalabraGuionesBomb().setText(palabraGuiones);
                    }
                    /*if (ahorcado.getIntentos() == 4) {
                        dao.delete("ahorcado", email);
                    } else {
                        dao.saveAhorcado_intentos(email);
                    }*/
                }

                dao.saveAhorcado(email, String.valueOf(respuestas), ahorcado.getIntentos());
                dao.getAhorcado(email, "data");
            }
        });
    }

    // PARAULOGIC
    private void createParaulogicActivityEvents() {
        dao.existsParaula(email);


        this.paraulogicActivity.getImageViewPala().setImageResource(R.drawable.p2);

        this.paraulogicActivity.getButtonPala().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputPalabra = paraulogicActivity.getEditTextPala().getText().toString();
                if (paraula.palabraExiste(inputPalabra) == true) {
                        Toast.makeText(paraulogicActivity, " ¡¡ HAS GANADO !!", Toast.LENGTH_SHORT).show();
                        paraula.setGanadasPara(paraula.getGanadasPara() + 1);
                        paraulogicActivity.recreate();
                    } else {
                        paraulogicActivity.getTextViewAcier().setText(paraula.getCount() + 1);
                        Toast.makeText(paraulogicActivity, " ¡¡ MUY BIEN !! Has acertado", Toast.LENGTH_SHORT).show();
                    }

               }
        });
    }

    // EXTRA
    private void createExtraActivityEvents() {
        dao.existsAnagrama(email);
        CountDownTimer timer = new Contador(10000, 1000, this.extraActivity).start();

        this.extraActivity.getButtoAna().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputPalabra = extraActivity.getTextPalabraAnaInput().getText().toString();

                if (!anagrama.palabrafinal(inputPalabra)) {
                    Toast.makeText(extraActivity, " ¡¡ NO, Vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(extraActivity, " ¡¡ MUY BIEN !! Has acertado", Toast.LENGTH_SHORT).show();
                    dao.saveStats_anagrama(email);
                    extraActivity.recreate();
                }

            }
        });
    }


    //METHODS OF SHARED PREFERENCES
    private void saveSession() {
        SharedPreferences.Editor prefs = this.loginActivity.getSharedPreferences(
                "PREFERENCES_FILE_KEY", Context.MODE_PRIVATE).edit();
        prefs.putString("email", this.loginActivity.getMail().getText().toString());
        prefs.putString("provider", Providers.LOGIN.toString());
        prefs.apply();
        switchActivity(this.loginActivity, this.homeActivity);
    }

    private void saveSession(GoogleSignInAccount signInAccount) {
        SharedPreferences.Editor prefs = this.loginActivity.getSharedPreferences(
                "PREFERENCES_FILE_KEY", Context.MODE_PRIVATE).edit();
        prefs.putString("email", signInAccount.getEmail().toString());
        prefs.putString("provider", Providers.GOOGLE.toString());
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

    private boolean checkSession() {
        SharedPreferences prefs = this.loginActivity.getSharedPreferences("PREFERENCES_FILE_KEY", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String provider = prefs.getString("provider", null);
        if (email != null) {
            return true;
        }
        return false;
    }

    private String checkEmail() {
        SharedPreferences prefs = this.loginActivity.getSharedPreferences("PREFERENCES_FILE_KEY", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        return email;
    }

    private String checkProvider() {
        SharedPreferences prefs = this.loginActivity.getSharedPreferences("PREFERENCES_FILE_KEY", Context.MODE_PRIVATE);
        String provider = prefs.getString("provider", null);
        return provider;
    }

    //OTHER METHODS
    public void returnCollectedData(User user) {
        this.perfilActivity.getEditText_mail_perfil().setText(user.getEmail());
        this.perfilActivity.getEditText_nombre().setText(user.getName());
        this.perfilActivity.getEditText_alias().setText(user.getUsername());
        this.perfilActivity.getTextViewProvider().setText(user.getProvider().toString());
    }

    public void returnCollectedData(Stats stats) {
        this.estadisticasActivity.getGanadasAhorcado().setText(String.valueOf(stats.getGanadasAhorcado()));
        this.estadisticasActivity.getGanadasParaulogic().setText(String.valueOf(stats.getGanadasParaulogic()));
        this.estadisticasActivity.getInicioSesion().setText(String.valueOf(stats.getNumeroInicios()));
        this.estadisticasActivity.getUltimasesion().setText(String.valueOf(stats.getFecha()));
    }

    public void returnCollectedData(Ahorcado ahorcado) {
        String palabra = "";
        for (int i = 0; i < ahorcado.getPalabra().length(); i++) {
            palabra = palabra.concat(" _ ");
        }

        this.ahorcadoActivity.getPalabraGuionesBomb().setText(palabra);

        String respuesta = "";
        if (ahorcado.getRespuestas().length != 0) {
            for (int i = 0; i < ahorcado.getRespuestas().length; i++) {
                respuesta += ahorcado.getRespuestas()[i];
            }
        }

        this.ahorcadoActivity.getTextRespuestasBomb().setText(respuesta);

        String palabraGuiones = "";

        for (int i = 0; i < ahorcado.getPalabra().length(); i++) {
            if (respuesta.length() != 0) {
                for (int j = 0; j < respuesta.length(); j++) {
                    if (ahorcado.getPalabra().charAt(i) == respuesta.charAt(j)) {
                        palabraGuiones += respuesta.charAt(j);
                    } else {
                        palabraGuiones += " _ ";
                    }
                }
            } else {
                palabraGuiones += " _ ";
            }

        }

        this.ahorcadoActivity.getPalabraGuionesBomb().setText(palabraGuiones);
    }

    public void returnCollectedDataClass(Ahorcado ahorcado) {
        this.ahorcado = ahorcado;
    }

    public void returnCollectedData(Anagrama anagrama) {
        this.extraActivity.getTextAnaPalabraMostrar().setText(anagrama.getPalabraUno());
        this.extraActivity.getTextPalabraAnaDos().setText(anagrama.getPalabraDos());
    }

    public void returnCollectedDataTimer(String timer) {
        this.extraActivity.getTextAnaCrono().setText(timer);
    }

    public void getSignedAccount() {
        this.email = GoogleSignIn.getLastSignedInAccount(this.loginActivity).getEmail();

        dao.saveLoginGoogle(GoogleSignIn.getLastSignedInAccount(this.loginActivity));
        dao.saveStats_init(this.email);

        saveSession(GoogleSignIn.getLastSignedInAccount(this.loginActivity));
    }
}
