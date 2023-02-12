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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Controller implements ControllerInterface {
    public static final String default_web_client_id = "28931008152-jgtpdrmfpcdeoffse8luipdme6g3unn3.apps.googleusercontent.com";
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
            dao.saveStats_init(checkEmail());
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
                                        saveSession();
                                        user.setEmail(mail);
                                        user.setProvider(Providers.LOGIN);

                                        dao.save(user);
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

                dao.save(user);

                Toast.makeText(perfilActivity, "Se ha guardado correctamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // STATS
    private void createEstadisticasActivityEvents() {
        if (checkSession()) {
            dao.getStat(checkEmail());
        } else {
            dao.getStat(stats.getEmail());
        }
    }

    // AHORCADO
    private void createAhorcadoActivityEvents() {
        String palabraSecreta = ahorcado.palabraFIn();
        char[] palabraGuiones = ahorcado.cambioGuiones(palabraSecreta);

        this.ahorcadoActivity.getTextViewGuiones().setText(palabraGuiones, 0, palabraGuiones.length);

        this.ahorcadoActivity.getButtonBomb().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInput = ahorcadoActivity.getTextViewGuiones().getText().toString();
                char[] tempGuionMostrar = ahorcado.cambioLetraGuion(palabraSecreta, palabraGuiones);
                int x = 0;

// si entra 1 letra / compara y autocambia -> error, avanza imagen, resta intento || acierto, cambia letra -> espera clickbutton
// si entra +de1 letras -> quiere resolver / finjuego -> intentos = 0, imagen fin || gana++ -> reload

                do {
                    if (textInput.length() == 1) {
                        if (tempGuionMostrar == null) {
                            x++;
                            ahorcadoActivity.getTextViewGuiones().setText(textInput);
                            ahorcadoActivity.getImageViewBomb().setImageLevel(x);
                            Toast.makeText(ahorcadoActivity, "OHHH... No has acertado", Toast.LENGTH_SHORT).show();
                            ahorcado.setIntentos(ahorcado.getIntentos() - 1);
                        } else {
                            ahorcadoActivity.getTextViewGuiones().setText(tempGuionMostrar, 0, palabraGuiones.length);
                            Toast.makeText(ahorcadoActivity, " ¡¡ MUY BIEN !! Has acertado", Toast.LENGTH_SHORT).show();
                        }

                    } else if (textInput.length() > 1) {
                        if (comporbar()) {
                            ahorcado.setGanadas(ahorcado.getGanadas() + 1);
                            Toast.makeText(ahorcadoActivity, " ¡¡¡¡¡ HAS GANADO !!!!!", Toast.LENGTH_SHORT).show();
                            ahorcadoActivity.finish();
                            ahorcadoActivity.recreate();
                        } else {
                            Toast.makeText(ahorcadoActivity, " ¡¡¡¡¡ HAS PERDIDO !!!!!", Toast.LENGTH_SHORT).show();
                            ahorcadoActivity.finish();
                            ahorcadoActivity.recreate();
                        }
                    }
                } while (ahorcado.aunGuiones(tempGuionMostrar) || ahorcado.getIntentos() != 0);
            }
        });

    }

    /*
                if (tempGuion == null) {
                    x++;
                    ahorcadoActivity.getTextViewGuiones().setText(tempText);
                    ahorcadoActivity.getImageViewBomb().setImageLevel(x);
                    ahorcadoActivity.setImageViewBomb();
                    Toast.makeText(ahorcadoActivity, "No has acertado", Toast.LENGTH_SHORT).show();

                } else {
                    ahorcadoActivity.getTextViewGuiones().setText(tempGuion,0,palabraGuiones.length);
                }
                */

    // PARAULOGIC
    private void createParaulogicActivityEvents() {
        String inputPalabra = this.paraulogicActivity.getEditTextPala().getText().toString();

        /*this.paraulogicActivity.getButtonPala().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                do {
                    if (paraula.palabraExiste(inputPalabra) == true) {
                        paraulogicActivity.getTextViewAcier().setText(paraula.getCount());
                        Toast.makeText(paraulogicActivity, " ¡¡ MUY BIEN !! Has acertado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(paraulogicActivity, " ¡¡ No esta, SORRY", Toast.LENGTH_SHORT).show();
                    }

                } while (!paraula.juegofin());
                paraula.setGanadas(paraula.getGanadas() + 1);
                paraulogicActivity.finish();
                paraulogicActivity.recreate();
            }
        });*/
    }

    // EXTRA
    private void createExtraActivityEvents() {

        String inputPalabra = this.extraActivity.getTextPalabraAna().getText().toString();
        this.extraActivity.getTextAnaPalabra().setText(anagrama.palabraUno());

        CountDownTimer timer = new CountDownTimer(10, 1000) {

            @Override
            public void onTick(long l) {
                extraActivity.getButtoAna().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (anagrama.palabrafinal(inputPalabra) == false) {
                            Toast.makeText(paraulogicActivity, " ¡¡ NO, Vuelve a intentarlo", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(paraulogicActivity, " ¡¡ MUY BIEN !! Has acertado", Toast.LENGTH_SHORT).show();
                            anagrama.setGanadasAna(anagrama.getGanadasAna() + 1); // STATS
                            extraActivity.recreate();
                        }
                    }
                });
            }

            @Override
            public void onFinish() {
                Toast.makeText(paraulogicActivity, " ¡¡ SE ACABÓ EL TIEMPO !!", Toast.LENGTH_SHORT).show();
                extraActivity.getTextAnaPalabra().setText(anagrama.palabraDos());
                extraActivity.recreate();
            }
        };
        this.extraActivity.getTextAnaCrono().setText(timer.toString());
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

    public void getSignedAccount() {
        dao.save(GoogleSignIn.getLastSignedInAccount(this.loginActivity));
        dao.saveStats_init(GoogleSignIn.getLastSignedInAccount(this.loginActivity).getEmail());

        saveSession(GoogleSignIn.getLastSignedInAccount(this.loginActivity));
    }

    // MODEL METHODS
    public char letraAhorcado() {
        String letraTemp = this.ahorcadoActivity.getEditTextLetra().getText().toString();
        do {
            if (letraTemp.isEmpty() || letraTemp.length() == 0) {
                Toast.makeText(ahorcadoActivity, "Introduce SOLO una Letra", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ahorcadoActivity, "Comprobemos...", Toast.LENGTH_SHORT).show();
            }
        } while (letraTemp.length() == 1);
        return letraTemp.charAt(0);
    }

    public boolean comporbar() {
        String letraTemp = this.ahorcadoActivity.getEditTextLetra().getText().toString(); // coge el input (A,B)
        char[] palabraConGuiones = this.ahorcadoActivity.getTextViewGuiones().getText().toString().toCharArray(); // Coge el array de los guines algunos cambiados

        for (int i = 0; i < palabraConGuiones.length; i++) {
            if (ahorcado.cambioLetraGuion(String.valueOf(letraTemp.charAt(i)), palabraConGuiones) != null)
                ;
            return true;
        }
        return false;
    }

}
