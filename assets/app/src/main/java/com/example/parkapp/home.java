package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.parkapp.userDetails.userdata.*;

public class home extends AppCompatActivity {

    public static String name="";
    public static String carModel="";
    public static String CarNo="";
    public static String place="";
    public boolean charging= true;
    public int batteryLevel=90;

    public static String userMobNo="1";
//    public static String userMobNo="9583222425";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        userMobNo=intent.getStringExtra("userMobNo");

        Toast.makeText(getApplicationContext(),"LOGIN Successfully", Toast.LENGTH_SHORT).show();

        com.example.parkapp.userDetails.userdata.userMobileNo=userMobNo;

        com.example.parkapp.userDetails.userdata.getUserData(userMobNo);

        // update data from the server
//        fetchdata process = new fetchdata();
//        process.execute();


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
