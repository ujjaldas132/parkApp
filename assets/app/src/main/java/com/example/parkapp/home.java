package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkapp.userDetails.userdata;

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

        Toast.makeText(getApplicationContext(),"LOGIN Successfully"+ carId, Toast.LENGTH_SHORT).show();

//        com.example.parkapp.userDetails.userdata.userMobileNo=userMobNo;

        com.example.parkapp.userDetails.userdata.getUserData(userMobNo);



        // update data from the server
//        fetchdata process = new fetchdata();
//        process.execute();

        Button carStatus=(Button) findViewById(R.id.carStatus);
        Button bookSpot=(Button) findViewById(R.id.bookSpot);
        Button profile=(Button) findViewById(R.id.profile);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        final Animation myAnim1 = AnimationUtils.loadAnimation(this, R.anim.bounce1);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        myAnim1.setInterpolator(interpolator);
        carStatus.startAnimation(myAnim1);
        bookSpot.startAnimation(myAnim);
        profile.startAnimation(myAnim);

    }

    public void findSpot(View view){
        Intent i= new Intent(this,FIND.class);
        startActivity(i);
    }



    public void bookSpot(View view){

        if( userdata.carId.length()!=0){
        Intent i= new Intent(this,BOOK.class);
            startActivity(i);}
        else{
            Toast.makeText(this, "Loading.....!", Toast.LENGTH_SHORT).show();
        }


    }








    public void getCarStatus(View view){



//        process.execute();


        Intent i= new Intent(this,CAR.class);




if(userdata.name!=null) {

    i.putExtra("Name", name);

    i.putExtra("model", carModel);

    i.putExtra("carNo", CarNo);
    i.putExtra("charging", charging);
    i.putExtra("batteryLevel", batteryLevel);

    startActivity(i);
}else{
    Toast.makeText(this,"Data is Loading please wait>>"+name, LENGTH_LONG).show();
}
    }








    public void profile(View view){
        Intent i;
        i = new Intent(this,Profile.class);

        i.putExtra("Name",userdata.name);

        i.putExtra("Place","Loading.....");

        i.putExtra("carNo", userdata.carId);


        startActivity(i);
    }
}


class MyBounceInterpolator implements android.view.animation.Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}