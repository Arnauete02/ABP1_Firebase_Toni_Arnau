package com.example.abp1_firebase_toni_arnau.dao;

import android.app.Activity;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.model.Stats;
import com.example.abp1_firebase_toni_arnau.model.User;
import com.example.abp1_firebase_toni_arnau.utils.Providers;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.inject.Provider;

import java.sql.Date;
import java.util.HashMap;

public class Dao {
    private static Dao dao;
    private FirebaseFirestore db;

    public static Dao getInstance() {
        if (dao == null) dao = new Dao();
        return dao;
    }

    // METHODS DATABASE
    public void save(User user) {
        db = FirebaseFirestore.getInstance();

        HashMap<String, String> collection = new HashMap<String, String>();

        if(user.getName() != null) {
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

    //METHOD SAVE WITH GOOGLE SIGN IN
    public void save(GoogleSignInAccount signInAccount) {
        db = FirebaseFirestore.getInstance();

        HashMap<String, String> collection = new HashMap<String, String>();

        if(signInAccount.getDisplayName() != null) {
            collection.put("name", signInAccount.getDisplayName());
        } else {
            collection.put("name", null);
        }

        if(signInAccount.getGivenName() != null){
            collection.put("username", signInAccount.getGivenName());
        } else {
            collection.put("username", null);
        }

        collection.put("provider", Providers.GOOGLE.toString());

        db.collection("users").document(signInAccount.getEmail())
                .set(collection, SetOptions.merge());
    }

    public void save(Stats stats) {
        db = FirebaseFirestore.getInstance();

        HashMap<String, String> collection = new HashMap<String, String>();

        if(stats.getGanadasAhorcado() != 0) {
            collection.put("ganadasAhorcado", String.valueOf(stats.getGanadasAhorcado()));
        } else {
            collection.put("ganadasAhorcado", "0");
        }

        if(stats.getGanadasParaulogic() != 0) {
            collection.put("ganadasParaulogic", String.valueOf(stats.getGanadasParaulogic()));
        } else {
            collection.put("ganadasParaulogic", "0");
        }

        //TODO: numeroInicios

        if(stats.getFecha() != null) {
            collection.put("ganadasParaulogic", String.valueOf(stats.getFecha()));
        } else {
            collection.put("ganadasParaulogic", Timestamp.now().toString());
        }

        db.collection("stats").document(stats.getEmail())
                .set(collection, SetOptions.merge());
    }

    public void get(String email, String type) {
        db = FirebaseFirestore.getInstance();

        switch (type){
            case "users":
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

            case "stats":
                db.collection("stats").document(email)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot documentSnapshot = task.getResult();

                                    Stats stats = new Stats(email, Integer.parseInt(documentSnapshot.get("ganadasAhorcado").toString()),
                                            Integer.parseInt(documentSnapshot.get("ganadasParaulogic").toString()), Integer.parseInt(documentSnapshot.get("numeroInicios").toString()), Date.valueOf(documentSnapshot.get("ganadasParaulogic").toString()));

                                    Controller.getInstance().returnCollectedData(stats);
                                }
                            }
                        });
        }
    }

    public void exists(String email) {
        db = FirebaseFirestore.getInstance();

        db.collection("stats").document(email)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            return;
                        } else {

                        }
                    }
                });
    }

    public void delete (User user) {
        db = FirebaseFirestore.getInstance();

        db.collection("users").document(user.getEmail())
                .delete();
    }

}
