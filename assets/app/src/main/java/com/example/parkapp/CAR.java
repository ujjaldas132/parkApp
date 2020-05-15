package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import static com.example.parkapp.userDetails.userdata.*;

public class CAR extends AppCompatActivity {

    public static TextView BatteryChargingStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_car);






        Button carspot=(Button) findViewById(R.id.spot);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        carspot.startAnimation(myAnim);








        Bundle dataFromIntent=getIntent().getExtras();


//        String nameVal=dataFromIntent.getString("Name");
        final TextView nameDisp= (TextView) findViewById(R.id.nameValue);
        nameDisp.setText(name);

//        String modelVal=dataFromIntent.getString("model");
        final TextView modelDisp= (TextView) findViewById(R.id.modelValue);
        modelDisp.setText(carModel);


//        String carNoVal=dataFromIntent.getString("carNo");
        final TextView carNoDisp= (TextView) findViewById(R.id.carNoValue);
        carNoDisp.setText(carId);


        Boolean chargingStatus = status.equals("c");
        CheckBox charge= (CheckBox) findViewById(R.id.chargingStatus);
        charge.setChecked(chargingStatus);
        charge.setClickable(false);


        TextView spotIDValue=(TextView)findViewById(R.id.SPOTIDvalue);
//        spotIDValue.setText(spotId);
        spotIDValue.setText("no need");



        int battaryLevel= dataFromIntent.getInt("batteryLevel");
        ProgressBar battaryBar= (ProgressBar) findViewById(R.id.batteryLevel);
        battaryBar.setProgress(battaryLevel);


        TextView lastTimeParkedTime=(TextView)findViewById(R.id.parkedTimeValue);
        lastTimeParkedTime.setText(lastParkedTime);

        TextView lastTimeParkedDate=(TextView)findViewById(R.id.parkedDateValue);
        lastTimeParkedDate.setText(lastParkedDate);


        TextView expectedTimeParkedTime=(TextView)findViewById(R.id.DeliveryTimeValue);
        expectedTimeParkedTime.setText(expectedRecievingTime);

        TextView expectedTimeParkedDate=(TextView)findViewById(R.id.DeliveryDateValue);
        expectedTimeParkedDate.setText(expectedRecievingDate);



        //charging status
        // will be update from outside just try
        BatteryChargingStatus=(TextView)findViewById(R.id.statusValue);
        com.example.parkapp.userDetails.carChargingStatus.getChargingStatus(spotId);



    }



    public void spotYourCar(View view){
        Intent i= new Intent(this,Map.class);
        startActivity(i);
    }

    //TODO: if car is removed previously can update accordinly
}
