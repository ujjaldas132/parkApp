package com.example.parkapp.userDetails;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class userdata {
    public static String name,carId,spotId,carModel,status,lastParkedDate,lastParkedTime,expectedRecievingDate,expectedRecievingTime,userMobileNo;

    public static HashMap<String,Object> userPrevDetailsMap=new HashMap<>();

    static FirebaseFirestore db = FirebaseFirestore.getInstance();



    public static void getUserData(String userMobileNo){
        userMobileNo=(String)userMobileNo;

        DocumentReference docRef = db.collection("users").document(userMobileNo);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Tag", "DocumentSnapshot data: " + document.getData());

                        userPrevDetailsMap=(HashMap<String, Object>) document.getData();

                        updateTheUserData();

                    } else {
                        Log.d("TAG", "No such document");
                        com.example.parkapp.userDetails.createNewUser.create();
//                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
//                    Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public static void updateTheUserData(){
        name=(String)userPrevDetailsMap.get("name");
        carId=(String)userPrevDetailsMap.get("carId");
        spotId=(String)userPrevDetailsMap.get("spotId");
        carModel=(String)userPrevDetailsMap.get("carModel");
        status=(String)userPrevDetailsMap.get("status");
        lastParkedDate=(String)userPrevDetailsMap.get("lastParkedDate");
        lastParkedTime=(String)userPrevDetailsMap.get("lastParkedTime");
        expectedRecievingDate=(String)userPrevDetailsMap.get("expectedRecievingDate");
        expectedRecievingTime=(String)userPrevDetailsMap.get("expectedRecievingTime");

    }



}
