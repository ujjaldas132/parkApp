package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Bundle dataFromIntent=getIntent().getExtras();


        String nameVal=dataFromIntent.getString("Name");
        final TextView nameDisp= (TextView) findViewById(R.id.name);
        nameDisp.setText(nameVal);


//        String nameVal1=dataFromIntent.getString("Place");
//        final TextView nameDisp1= (TextView) findViewById(R.id.location);
//        nameDisp.setText(nameVal1);


//        String nameVal2=dataFromIntent.getString("Name");
//        final TextView nameDisp2= (TextView) findViewById(R.id.name);
//        nameDisp.setText(nameVal2);




    }
}
