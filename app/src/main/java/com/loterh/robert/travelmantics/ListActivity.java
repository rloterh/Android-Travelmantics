package com.loterh.robert.travelmantics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<TravelDeal> deals;
    private FirebaseDatabase rFirebaseDatabase;
    private DatabaseReference rDatabaseReference;
    private ChildEventListener rChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       /*
       FirebaseUtil.openFbReference("traveldeals");
        rFirebaseDatabase = FirebaseUtil.rFirebaseDatabase;
        rDatabaseReference = FirebaseUtil.rDatabaseReference;

        rChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TextView textViewDeals= (TextView)findViewById(R.id.textview_deals);
                TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
                textViewDeals.setText(textViewDeals.getText() + "\n" + td.getTitle());

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
        rDatabaseReference.addChildEventListener(rChildEventListener);
        */
        RecyclerView recyclerViewDeals = (RecyclerView) findViewById(R.id.recyclerview_deals);
        final DealAdapter adapter = new DealAdapter();
        recyclerViewDeals.setAdapter(adapter);
        LinearLayoutManager dealsLayoutManager = new LinearLayoutManager(this,recyclerViewDeals.VERTICAL, false);
        recyclerViewDeals.setLayoutManager(dealsLayoutManager);

    }
}
