package com.example.abp1_firebase_toni_arnau.dao;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.model.Stats;
import com.example.abp1_firebase_toni_arnau.model.User;
import com.example.abp1_firebase_toni_arnau.utils.Providers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.inject.Provider;

import java.util.HashMap;

public class Dao {
    private static Dao dao;

    public static Dao getInstance() {
        if (dao == null) dao = new Dao();
        return dao;
    }

    public void save(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    public void saveStats (Stats stats) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        HashMap<String, Object> estadisticas = new HashMap<String, Object>();

        if (stats.getGanadasAhorcado() != 0) {
            estadisticas.put("ganadas ahorcado", stats.getGanadasAhorcado());
        } else {
            estadisticas.put("ganadas ahoracdo", 0);
        }

        if (stats.getGanadasParaulogic() != 0) {
            estadisticas.put("ganadas paraulogic", stats.getGanadasParaulogic());
        } else {
            estadisticas.put("ganadas paraulogic", 0);
        }

        if (stats.getFecha() != FieldValue.serverTimestamp()) {
            estadisticas.put("datatime", stats.getFecha());
        } else {
            estadisticas.put("dataTime", FieldValue.serverTimestamp());
        }

        db.collection("estadisticas").document(stats.getMail())
               .set(stats, SetOptions.merge());

    }


    public void get(String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(email)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            User user = new User(email, Providers.valueOf(documentSnapshot.get("provider").toString()));

                            if (documentSnapshot.get("name") != null || documentSnapshot.get("name") != "") {
                                user.setName(documentSnapshot.get("name").toString());
                            }

                            if (documentSnapshot.get("username") != null || documentSnapshot.get("username") != "") {
                                user.setName(documentSnapshot.get("username").toString());
                            }

                            Controller.getInstance().returnCollectedData(user);
                        }
                    }
                });

    }

    public void exists(String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(email)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                        } else {

                        }
                    }
                });
    }

    public void delete (User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(user.getEmail())
                .delete();
    }

}
