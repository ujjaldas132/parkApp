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
    public static String place="";
    public boolean charging= true;
    public int batteryLevel=90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // update data from the server
        fetchdata process = new fetchdata();
        process.execute();


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



//        process.execute();


        Intent i= new Intent(this,CAR.class);






        i.putExtra("Name",name);

        i.putExtra("model",carModel);

        i.putExtra("carNo",CarNo);
        i.putExtra("charging",charging);
        i.putExtra("batteryLevel",batteryLevel);

        startActivity(i);
    }








    public void profile(View view){
        Intent i;
        i = new Intent(this,Profile.class);

        i.putExtra("Name",name);

        i.putExtra("Place",this.place);

        i.putExtra("carNo",this.CarNo);


        startActivity(i);
    }



}
