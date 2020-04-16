package com.example.parkapp.userDetails;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class createNewUser {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();


    public static StringBuilder carID = new StringBuilder();



    public static void create(){

        for(int i = 0; i < 2; i++) {
            char ch = (char) (Math.random() * 26 + 'A');
            carID.append(ch);
        }

        carID.append(" ");

        for(int i = 0; i < 2; i++) {
            char digit1 = (char) (Math.random() * 10 + '0');
            carID.append(digit1);
        }

        carID.append(" ");

        for(int i = 0; i < 4; i++) {
            char digit1 = (char) (Math.random() * 10 + '0');
            carID.append(digit1);
        }



        HashMap<String,String> userDataMap= new HashMap<>();
        userDataMap.put("name","User User");
        userDataMap.put("carId",carID.toString());
        userDataMap.put("spotId","None");
        userDataMap.put("carModel","None");
        userDataMap.put("status","None");
        userDataMap.put("lastParkedDate","None");
        userDataMap.put("lastParkedTime","None");
        userDataMap.put("expectedRecievingDate","None");
        userDataMap.put("expectedRecievingTime","None");


        db.collection("users").document(com.example.parkapp.home.userMobNo)
                .set(userDataMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getApplicationContext(),"the spot is booked Successfully",Toast.LENGTH_LONG).show();
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                        createCar();
                        userdata.updateTheUserData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
//                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                });
    }






    private static void createCar(){

        HashMap<String,String> data =new HashMap<>();
        data.put("carIdNumber",carID.toString());
        data.put("curPowerLevel","800");
        data.put("fullPowerLevel","5000");
        data.put("ownerMob",com.example.parkapp.home.userMobNo);
        data.put("status","inQueue");


        db.collection("cars").document(carID.toString())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(getApplicationContext(),"the spot is booked Successfully",Toast.LENGTH_LONG).show();
                        Log.d("TAG", "DocumentSnapshot successfully written!");

//                        userdata.updateTheUserData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
//                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                });

    }



}
