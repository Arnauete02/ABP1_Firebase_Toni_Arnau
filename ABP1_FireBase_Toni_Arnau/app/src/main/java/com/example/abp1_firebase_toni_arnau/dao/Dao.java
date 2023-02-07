package com.example.abp1_firebase_toni_arnau.dao;

import com.example.abp1_firebase_toni_arnau.model.User;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class Dao {
    private static Dao dao;

    public static Dao getInstance() {
        if (dao == null) dao = new Dao();
        return dao;
    }

    public void save (User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        HashMap<String, String> collection = new HashMap<String, String>();
        collection.put("provider",user.getProvider().toString());
        collection.put("name",user.getName());

        db.collection("users").document(user.getEmail())
                .set(collection, SetOptions.merge());
    }
}
