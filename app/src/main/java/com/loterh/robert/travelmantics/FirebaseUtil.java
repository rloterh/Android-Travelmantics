package com.loterh.robert.travelmantics;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase rFirebaseDatabase;
    public static DatabaseReference rDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<TravelDeal> rDdeals;

    private  FirebaseUtil(){}

    public static void openFbReference(String ref){
        if (firebaseUtil==null){
            firebaseUtil = new FirebaseUtil();
            rFirebaseDatabase = FirebaseDatabase.getInstance();
            rDdeals = new ArrayList<TravelDeal>();
        }
        rDatabaseReference = rFirebaseDatabase.getReference().child(ref);

    }

}
