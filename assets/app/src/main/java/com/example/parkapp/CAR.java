package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import static com.example.parkapp.userDetails.userdata.*;

public class CAR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);


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



    }



    public void spotYourCar(View view){
        Intent i= new Intent(this,Map.class);
        startActivity(i);
    }
}
