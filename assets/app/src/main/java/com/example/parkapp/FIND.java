package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.*;
import java.lang.*;
import java.io.*;

public class FIND extends AppCompatActivity {

    public String[] arrayVehicle = new String[] {
            "MAHINDRA","Tesla"
    };
    public String[] arraySpace= new String[] {
            "SPACE 1","SPACE 3","SPACE 4","SPACE 7"
    };

    public HashMap<String,String> spaceMap= new HashMap<String, String>();











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);


spaceMap.put("MAHINDRA","4");spaceMap.put("Tesla","3");


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
        TextView spacedisp=(TextView) findViewById(R.id.spaceRequiredValue);
        spacedisp.setText(spaceMap.get(selectedVehicle));
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });










        Spinner sp = (Spinner) findViewById(R.id.selectSpace);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpace);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter1);




    }





}
