package com.example.parkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BOOK extends AppCompatActivity {
    public String[] arrayVehicle = new String[] {
            "MAHINDRA","Tesla","Audi e-tron"
    };
    public String[] arraySpace= new String[] {
            "1","3","4","7"
    };
    public String[] arrayTimeHour= new String[] {
            "0","1","2","3","4","5","6","7","8","9","10","11","12"
    };

    public ArrayList<String> arrrayTimeMin=new ArrayList<>();

    public HashMap<String,String> spaceMap= new HashMap<String, String>();
    public Spinner sp,timesp,timespMin;
    public TextView spacedisp;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_book);

        spaceMap.put("MAHINDRA","4");spaceMap.put("Tesla","3");spaceMap.put("Audi e-tron","3");




        Spinner s = (Spinner) findViewById(R.id.selectVehicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayVehicle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);







        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                String selectedVehicle=String.valueOf(parent.getSelectedItem());
                spacedisp=(TextView) findViewById(R.id.spaceRequiredValue);
                spacedisp.setText(spaceMap.get(selectedVehicle));
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });









        String selectedVehicle=String.valueOf(s.getSelectedItem());
        TextView spacedisp=(TextView) findViewById(R.id.spaceRequiredValue);
        spacedisp.setText(spaceMap.get(selectedVehicle));


       sp = (Spinner) findViewById(R.id.selectSpace);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpace);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter1);




        timesp = (Spinner) findViewById(R.id.timeValueHour);
        ArrayAdapter<String> adaptert = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayTimeHour);
        adaptert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesp.setAdapter(adaptert);




        timespMin = (Spinner) findViewById(R.id.timeValueMin);

        for(int i1=0;i1<12;i1++) {
            arrrayTimeMin.add(String.valueOf(i1*5));
        }
        Log.w("TAG",arrrayTimeMin.toString());

        ArrayAdapter<String> adaptertMin = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrrayTimeMin);
        adaptertMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timespMin.setAdapter(adaptertMin);

    }





    public void bookTheSpot(View view){
        String spotId=sp.getSelectedItem().toString();
        String parkingTime=String.valueOf(Integer.valueOf(timespMin.getSelectedItem().toString())+(60*Integer.valueOf(timesp.getSelectedItem().toString())));
        String carId="XXXXXXX";
        String powerLevel="240";
        String fullPowerLevel="1200";

//        data={'space': '5','id': 'AS6984','time': '12','powerLevel': '50','parkingSpaceId':"None","fullPowerlevel":"1200"}
        HashMap<String,String> dataMap= new HashMap<>();
        dataMap.put("space",spacedisp.getText().toString());
        dataMap.put("id",carId);
        dataMap.put("time",parkingTime);
        dataMap.put("powerLevel",powerLevel);
        dataMap.put("parkingSpaceId",spotId);
        dataMap.put("fullPowerlevel",fullPowerLevel);


        writetheCarDetail(dataMap,spotId);

    }

    private void writetheCarDetail(HashMap<String, String> data, final String spotid){


        db.collection("detailOfParkedCar").document(spotid)
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                        changeTheSpotStatus(spotid);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

    }

    private void changeTheSpotStatus(String spotId){
        HashMap<String,String> statusMap=new HashMap<>();
        statusMap.put(spotId,"True");

        db.collection("carSpotsStatus").document(spotId)
                .set(statusMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"the spot is booked Successfully",Toast.LENGTH_LONG).show();
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                });

        //TODO: also check the status for the charging neutral, discharging
    }


}




