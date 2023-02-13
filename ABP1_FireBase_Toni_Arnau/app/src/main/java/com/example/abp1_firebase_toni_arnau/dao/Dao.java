package com.example.abp1_firebase_toni_arnau.dao;

import static android.content.ContentValues.TAG;

import android.app.Activity;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.model.Ahorcado;
import com.example.abp1_firebase_toni_arnau.model.Anagrama;
import com.example.abp1_firebase_toni_arnau.model.Paraula;
import com.example.abp1_firebase_toni_arnau.model.Stats;
import com.example.abp1_firebase_toni_arnau.model.User;
import com.example.abp1_firebase_toni_arnau.utils.Providers;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.inject.Provider;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Dao {
    private static Dao dao;
    private FirebaseFirestore db;

    public Dao() {
        this.db = FirebaseFirestore.getInstance();
    }

    public static Dao getInstance() {
        if (dao == null) dao = new Dao();
        return dao;
    }

    // METHODS SAVE USER WITH EMAIL & PASSWORD
    public void saveLogin(User user) {
        HashMap<String, String> collection = new HashMap<String, String>();

        if (user.getName() != null) {
            collection.put("name", user.getName());
        } else {
            collection.put("name", null);
        }

        if (user.getUsername() != null) {
            collection.put("username", user.getUsername());
        } else {
            collection.put("username", null);
        }

        collection.put("provider", user.getProvider().toString());

        db.collection("users").document(user.getEmail())
                .set(collection, SetOptions.merge());
    }

    // METHOD SAVE WITH GOOGLE SIGN IN
    public void saveLoginGoogle(GoogleSignInAccount signInAccount) {
        HashMap<String, String> collection = new HashMap<String, String>();

        if (signInAccount.getDisplayName() != null) {
            collection.put("name", signInAccount.getDisplayName());
        } else {
            collection.put("name", null);
        }

        if (signInAccount.getGivenName() != null) {
            collection.put("username", signInAccount.getGivenName());
        } else {
            collection.put("username", null);
        }

        collection.put("provider", Providers.GOOGLE.toString());

        db.collection("users").document(signInAccount.getEmail())
                .set(collection, SetOptions.merge());
    }

    // METHOD SAVE STATS
    public void saveStats_init(String email) {
        HashMap<String, Object> collection = new HashMap<>();
        collection.put("numeroInicios", FieldValue.increment(1));
        collection.put("fecha", FieldValue.serverTimestamp());

        db.collection("stats")
                .document(email)
                .set(collection, SetOptions.merge());
    }

    // METHOD SAVE AHORCADO
    public void saveAhorcado(String email) {
        HashMap<String, Object> collection = new HashMap<>();

        collection.put("palabra", new Ahorcado().palabraRandom());
        collection.put("respuestas", "");
        collection.put("intentos", 5);

        db.collection("ahorcado")
                .document(email)
                .set(collection, SetOptions.merge());
    }

    public void saveAhorcado(String email, String respuestas, int intentos) {
        HashMap<String, Object> collection = new HashMap<>();

        collection.put("respuestas", respuestas);
        collection.put("intentos", intentos);

        db.collection("ahorcado")
                .document(email)
                .set(collection, SetOptions.merge());
    }

    // METHOD TO GET USER
    public void getUser(String email) {
        db.collection("users").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            User user = new User(email, Providers.valueOf(documentSnapshot.get("provider").toString()));

                            if (documentSnapshot.get("name") != null) {
                                if (!documentSnapshot.get("name").toString().equals("")) {
                                    user.setName(documentSnapshot.get("name").toString());
                                }
                            }

                            if (documentSnapshot.get("username") != null) {
                                if (!documentSnapshot.get("username").toString().equals("")) {
                                    user.setUsername(documentSnapshot.get("username").toString());
                                }
                            }

                            Controller.getInstance().returnCollectedData(user);
                        }
                    }
                });
    }

    // METHOD TO GET STAT
    public void getStat(String email) {
        db.collection("stats").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            Stats stats = new Stats(email, Integer.parseInt(documentSnapshot.get("numeroInicios").toString()), documentSnapshot.get("fecha").toString());

                            if (documentSnapshot.get("ganadasAhorcado") == null) {
                                stats.setGanadasAhorcado(0);
                            } else {
                                stats.setGanadasAhorcado(Integer.parseInt(documentSnapshot.get("ganadasAhorcado").toString()));
                            }

                            if (documentSnapshot.get("ganadasParaulogic") == null) {
                                stats.setGanadasAhorcado(0);
                            } else {
                                stats.setGanadasAhorcado(Integer.parseInt(documentSnapshot.get("ganadasParaulogic").toString()));
                            }

                            Controller.getInstance().returnCollectedData(stats);
                        }
                    }
                });
    }

    // METHOD TO GET AHORCADO
    public void getAhorcado(String email) {
        db.collection("ahorcado").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            Ahorcado ahorcado = new Ahorcado();

                            ahorcado.setEmail(email);

                            ahorcado.setPalabra(documentSnapshot.get("palabra").toString());

                            String[] respuestas = new String[documentSnapshot.get("respuestas").toString().length()];
                            for (int i = 0; i < respuestas.length; i++) {
                                respuestas[i] = documentSnapshot.get("respuestas").toString();
                            }
                            ahorcado.setRespuestas(respuestas);

                            ahorcado.setIntentos(Integer.parseInt(documentSnapshot.get("intentos").toString()));

                            Controller.getInstance().returnCollectedData(ahorcado);
                        }
                    }
                });
    }
    // --------------------------------------------------------------------------------------------
    // ANAGRAMA

    public void agregarAnagrama(Anagrama anagrama) {
        boolean hecho = false;
        HashMap<String, Object> collectionAnagrama = new HashMap<String, Object>();
        collectionAnagrama.put("palabrabraUno", anagrama.getPalabraUno().toString());
        collectionAnagrama.put("palabraDos", anagrama.getPalabraDos().toString());
        collectionAnagrama.put("ganadas", anagrama.getGanadasAna());

        db.collection("anagrama").document(anagrama.getUser().getEmail()).set(collectionAnagrama, SetOptions.merge());
    }

    public Task<DocumentSnapshot> getAnagrama(String email) {
        return db.collection("collectionAnagrama").document(email).get();
    }

// ____--------------------------------------------------------------------



    public void existsAhorcado(String email) {
        db.collection("ahorcado").document(email)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            getAhorcado(email);
                        } else {
                            saveAhorcado(email);
                            getAhorcado(email);
                        }
                    }
                });
    }

    public void delete(User user) {
        db.collection("users").document(user.getEmail())
                .delete();
    }
}
