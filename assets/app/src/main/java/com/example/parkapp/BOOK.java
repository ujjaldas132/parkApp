package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class BOOK extends AppCompatActivity {
    public String[] arrayVehicle = new String[] {
            "MAHINDRA","Tesla"
    };
    public String[] arraySpace= new String[] {
            "SPACE 1","SPACE 3","SPACE 4","SPACE 7"
    };
    public String[] arrayTime= new String[] {
            "0.5","1","1.5","2","2.5","3"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);






        Spinner s = (Spinner) findViewById(R.id.selectVehicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayVehicle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);





        Spinner sp = (Spinner) findViewById(R.id.selectSpace);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpace);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter1);




        Spinner timesp = (Spinner) findViewById(R.id.timeValue);
        ArrayAdapter<String> adaptert = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayTime);
        adaptert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesp.setAdapter(adaptert);

    }





}
