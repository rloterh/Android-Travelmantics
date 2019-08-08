package com.loterh.robert.travelmantics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    private FirebaseDatabase rFirebaseDatabase;
    private DatabaseReference rDatabaseReference;

    EditText txtTitle;
    EditText txtDescription;
    EditText txtPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        FirebaseUtil.openFbReference("traveldeals");
        rFirebaseDatabase = FirebaseUtil.rFirebaseDatabase;
        rDatabaseReference = FirebaseUtil.rDatabaseReference;
        txtTitle = (EditText)findViewById(R.id.txt_title);
        txtDescription = (EditText)findViewById(R.id.txt_description);
        txtPrice = (EditText) findViewById(R.id.txt_price);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeals();
                Toast.makeText(this, "Deal saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void saveDeals() {
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String price = txtPrice.getText().toString();
        TravelDeal deal = new TravelDeal(title, description, price, "");
        rDatabaseReference.push().setValue(deal);

    }
    private  void clean(){
        txtTitle.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        txtTitle.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}
