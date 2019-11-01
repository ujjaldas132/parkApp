package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String name="";
    public static String carModel="";
    public static String CarNo="";

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


        fetchdata process = new fetchdata();
        process.execute();


        Intent i= new Intent(this,CAR.class);


            process.givesomedata();

//        String temp=String.valueOf(name);
//        Log.d("mess>>>>>>>>>>>>>>>",temp);



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
