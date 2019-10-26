package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String name="Ujjal Das";
    public String carModel="TESLA ";
    public String CarNo="AS 6858";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findSpot(View view){
        Intent i= new Intent(this,FIND.class);
        startActivity(i);
    }



    public void bookSpot(View view){
        Intent i= new Intent(this,BOOK.class);
        startActivity(i);
    }






    public void getCarStatus(View view){
        Intent i= new Intent(this,CAR.class);



        i.putExtra("Name",name);

        i.putExtra("model",carModel);

        i.putExtra("carNo",CarNo);

        startActivity(i);
    }






    public void profile(View view){
        Intent i;
        i = new Intent(this,Profile.class);
        startActivity(i);
    }



}
