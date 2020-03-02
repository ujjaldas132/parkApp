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

    public static void create(){

        HashMap<String,String> userDataMap= new HashMap<>();
        userDataMap.put("name","None");
        userDataMap.put("carId","None");
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

}
