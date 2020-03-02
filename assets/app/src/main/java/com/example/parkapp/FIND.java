package com.example.parkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.*;
import java.lang.*;
import java.io.*;

public class FIND extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    LinearLayout addSpotDetail;

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

        addSpotDetail=(LinearLayout)findViewById(R.id.addSpotDetails) ;

        DocumentReference docRef = db.collection("carSpotsStatus").document("currentStatus");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        HashMap<String, Object> data= new HashMap<>((HashMap<String, Object>)document.getData());
                        addToTheSpotDetails(data);

                        Log.w("Message", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.w("message", "No such document");
                    }
                } else {
                    Log.w("message", "get failed with ", task.getException());
                }
            }
        });






    }

    private void addToTheSpotDetails(final HashMap<String,Object> detailsData){



        for(final String key : detailsData.keySet()){

            Boolean clickAble=false;
            if(((String)detailsData.get(key)).equals("False")) clickAble=true;

        final RelativeLayout newLayout= new RelativeLayout(this);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT

        );
        params.setMargins(0,35,0,2);
        newLayout.setLayoutParams(params);


        RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        p1.setMargins(0,10,0,0);

        RelativeLayout.LayoutParams p2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams p3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        TextView SpotName = new TextView(this);
            SpotName.setTypeface(Typeface.DEFAULT_BOLD);
            SpotName.setTextColor(Color.BLACK);
            SpotName.setText("PARKING SPOT NUMBER "+key);
            SpotName.setId(1);

        TextView newPrice = new TextView(this);
        newPrice.setId(2);
        newPrice.setText("change");
        p2.addRule(RelativeLayout.BELOW, SpotName.getId());


        TextView newdes = new TextView(this);
        newdes.setId(3);
        newdes.setText("change");
        newdes.setTextColor(Color.GRAY);
        p3.addRule(RelativeLayout.BELOW, newPrice.getId());



        final Button newbtn = new Button(this);
        RelativeLayout.LayoutParams pbtn = new RelativeLayout.LayoutParams(new LinearLayout.LayoutParams(300,100));
//            pbtn.width = 10;
//            pbtn.width= new RelativeLayout.LayoutParams(10);
        pbtn.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        newbtn.setLayoutParams(pbtn);
        if(clickAble)
        newbtn.setText("Occupied");
        else newbtn.setText("Free");


            final Boolean finalClickAble = clickAble;
            newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!finalClickAble){
                    Intent i= new Intent(FIND.this,BOOK.class);
                    i.putExtra("spotId",(String)detailsData.get(key));
                    startActivity(i);
                }
            }
        });






        newLayout.addView(SpotName, p1);
//        newLayout.addView(newPrice, p2);
//        newLayout.addView(newdes, p3);
        newLayout.addView(newbtn);


        addSpotDetail.addView(newLayout);}


    }


public void toBook(View view){
    Intent i= new Intent(FIND.this,BOOK.class);

    startActivity(i);
}


}
