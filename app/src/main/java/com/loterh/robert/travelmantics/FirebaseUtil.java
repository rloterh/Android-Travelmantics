package com.loterh.robert.travelmantics;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class FirebaseUtil {
    public static FirebaseDatabase rFirebaseDatabase;
    public static DatabaseReference rDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static FirebaseAuth rFirebaseAuth;
    public static FirebaseStorage rStorage;
    public static StorageReference rStorageRef;
    public static FirebaseAuth.AuthStateListener rAuthListener;
    public static ArrayList<TravelDeal> mDdeals;
    private static final int RC_SIGN_IN = 123;
    private static ListActivity caller;
public static boolean isAdmin;
    private  FirebaseUtil(){}

    public static void openFbReference(String ref, final ListActivity callerActivity){
        if (firebaseUtil==null){
            firebaseUtil = new FirebaseUtil();
            rFirebaseDatabase = FirebaseDatabase.getInstance();
            rFirebaseAuth = FirebaseAuth.getInstance();
            caller = callerActivity;

            rAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() ==null){
                    FirebaseUtil.signIn();
                    }
                    else{
                        String userId = firebaseAuth.getUid();
                        checkAdmin(userId);

                    }
                    Toast.makeText(callerActivity.getBaseContext(), "Welcome Back!", Toast.LENGTH_LONG).show();
                }
            };
            connectStorage();

        }
        mDdeals = new ArrayList<TravelDeal>();
        rDatabaseReference = rFirebaseDatabase.getReference().child(ref);

    }

    private static void signIn(){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


// Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    private static void checkAdmin(String uid){
        FirebaseUtil.isAdmin=false;
        DatabaseReference ref = rFirebaseDatabase.getReference().child("administrators").child(uid);
        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseUtil.isAdmin=true;
                caller.showMenu();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addChildEventListener(listener);
    }

    public static void attachListener(){
        rFirebaseAuth.addAuthStateListener(rAuthListener);
    }

    public static void detachListener(){
        rFirebaseAuth.removeAuthStateListener(rAuthListener);
    }
    public static void connectStorage(){
        rStorage = FirebaseStorage.getInstance();
        rStorageRef = rStorage.getReference().child("deals_pictures");
    }

}
