package com.example.abp1_firebase_toni_arnau.dao;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.model.User;
import com.example.abp1_firebase_toni_arnau.utils.Providers;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
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
        collection.put("name", user.getName());
        collection.put("provider", user.getProvider().toString());
        collection.put("username", user.getUsername());

        db.collection("users").document(user.getEmail())
                .set(collection, SetOptions.merge());
    }

    public void get(String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(email)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = new User(documentSnapshot.get("name").toString(), email,
                                Providers.valueOf(documentSnapshot.get("provider").toString()), documentSnapshot.get("username").toString());
                        Controller.getInstance().returnCollectedData(user);
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
