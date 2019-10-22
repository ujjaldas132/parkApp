package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CAR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);


        Bundle dataFromIntent=getIntent().getExtras();


        String nameVal=dataFromIntent.getString("Name");
        final TextView nameDisp= (TextView) findViewById(R.id.nameValue);
        nameDisp.setText(nameVal);

        String modelVal=dataFromIntent.getString("model");
        final TextView modelDisp= (TextView) findViewById(R.id.modelValue);
        modelDisp.setText(modelVal);


        String carNoVal=dataFromIntent.getString("carNo");
        final TextView carNoDisp= (TextView) findViewById(R.id.carNoValue);
        carNoDisp.setText(carNoVal);

    }
}
