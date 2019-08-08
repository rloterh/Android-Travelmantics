package com.loterh.robert.travelmantics;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {
    ArrayList<TravelDeal> deals;
    private FirebaseDatabase rFirebaseDatabase;
    private DatabaseReference rDatabaseReference;
    private ChildEventListener rChildEventListener;

    public DealAdapter(){
        FirebaseUtil.openFbReference("traveldeals");
        rFirebaseDatabase = FirebaseUtil.rFirebaseDatabase;
        rDatabaseReference = FirebaseUtil.rDatabaseReference;
        deals = FirebaseUtil.rDdeals;

        rChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TravelDeal td = dataSnapshot.getValue(TravelDeal.class);
                Log.d("Deal:",td.getTitle());
                td.setId(dataSnapshot.getKey());
                deals.add(td);
                notifyItemInserted(deals.size() -1);

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
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =  parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        TravelDeal deal = deals.get(position);
        holder.bind(deal);

    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class DealViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPrice;
        public DealViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_title);
            textViewDescription = (TextView) itemView.findViewById(R.id.tv_description);
            textViewPrice = (TextView) itemView.findViewById(R.id.txt_price);


        }
        public void bind (TravelDeal deal){
            textViewTitle.setText(deal.getTitle());
            textViewDescription.setText(deal.getDescription());
            textViewPrice.setText(deal.getPrice());
        }
    }
}
