package com.example.parkapp.userDetails;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class carChargingStatus {
    static String chargingStatus="UNKNOWN";
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static void getChargingStatus(final String Spotid){
        if(!Spotid.equals("None")){

            DocumentReference docRef = db.collection("SpecificSpotDeetails").document("spotStatus");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("Tag", "DocumentSnapshot>>>>>>>>>>>>>>>>>>>>>>>>>>>> data: " + document.getData());
                            String tempStatus=(String)document.getData().get(Spotid);
                            if(tempStatus.equals("c")){
                                chargingStatus="Charging";
                            }else if(tempStatus.equals("n")){
                                chargingStatus="Neutral";
                            }else if(tempStatus.equals("d")){
                                chargingStatus="Discharging";
                            }else{
                                chargingStatus="Fully Charged";
                            }

                            //change the status in the activity
                            com.example.parkapp.CAR.BatteryChargingStatus.setText(chargingStatus);


                        } else {
                            Log.d("TAG", "No such -------------------document");
//                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("TAG", "get <<<<<<<<<<<failed with ", task.getException());
//                    Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                }
            });



        }else{
            Log.d("TAG", "No such ---------NOOOONE---------document");
        }
    }
}
