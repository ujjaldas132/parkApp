package com.example.parkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkapp.userDetails.userdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BOOK extends AppCompatActivity {
    public String[] arrayVehicle = new String[] {
            "MAHINDRA","Tesla","Audi e-tron"
    };



    public ArrayList<String> arraySpace=new ArrayList<>();

    public String[] arrayTimeHour= new String[] {
            "0","1","2","3","4","5","6","7","8","9","10","11","12"
    };

    public ArrayList<String> arrrayTimeMin=new ArrayList<>();

    public HashMap<String,String> spaceMap= new HashMap<String, String>();
    public Spinner sp,timesp,timespMin;
    public TextView spacedisp;
    public HashMap<String,Object> preSpotStatus= new HashMap<>();
    int totalSpots=8;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_book);


//        Toast.makeText(this, userdata.carId, Toast.LENGTH_LONG).show();






        Button bookSpot=(Button) findViewById(R.id.book);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.3, 30);
        myAnim.setInterpolator(interpolator);

        bookSpot.startAnimation(myAnim);
















        spaceMap.put("MAHINDRA","4");spaceMap.put("Tesla","3");spaceMap.put("Audi e-tron","3");
        getPreviousVehicleStatus();



        Spinner s = (Spinner) findViewById(R.id.selectVehicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arrayVehicle);
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


//       space array is assigned after getting the previous status




        timesp = (Spinner) findViewById(R.id.timeValueHour);
        ArrayAdapter<String> adaptert = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arrayTimeHour);
        adaptert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesp.setAdapter(adaptert);




        timespMin = (Spinner) findViewById(R.id.timeValueMin);

        for(int i1=0;i1<12;i1++) {
            arrrayTimeMin.add(String.valueOf(i1*5));
        }
        Log.w("TAG",arrrayTimeMin.toString());

        ArrayAdapter<String> adaptertMin = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arrrayTimeMin);
        adaptertMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timespMin.setAdapter(adaptertMin);

    }





    public void bookTheSpot(View view){


        String spotId=sp.getSelectedItem().toString();
        String parkingTime=String.valueOf(Integer.valueOf(timespMin.getSelectedItem().toString())+(60*Integer.valueOf(timesp.getSelectedItem().toString())));
        String carId=userdata.carId;
        String powerLevel="800";
        String fullPowerLevel="5000";

//        data={'space': '5','id': 'AS6984','time': '12','powerLevel': '50','parkingSpaceId':"None","fullPowerlevel":"1200"}
        HashMap<String,String> dataMap= new HashMap<>();
        dataMap.put("space",spacedisp.getText().toString());
        dataMap.put("id",userdata.carId);
        dataMap.put("time",parkingTime);
        dataMap.put("powerLevel",powerLevel);
        dataMap.put("parkingSpaceId",spotId);
        dataMap.put("fullPowerlevel",fullPowerLevel);


//        writetheCarDetail(dataMap,spotId);

        getPrevQueue(carId,parkingTime,powerLevel,fullPowerLevel, "needtoAdd");
    }






    private void getPrevQueue(final String carId, final String reqTime, final String curP, final String fullP, final String OwnMob){

        DocumentReference docRef = db.collection("carSpotsStatus").document("queue");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Tag", "DocumentSnapshot data: " + document.getData());
                        preSpotStatus=(HashMap<String, Object>) document.getData();
                        //getAndAssignTheFreeSpots();
                        preSpotStatus.put(carId,reqTime);
                        fixSpotInQueue(carId,curP,fullP,OwnMob);


                    } else {
                        Log.d("TAG", "No such document");
                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                    Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });



    }






    private void fixSpotInQueue(final String carId, final String curP, final String fullP, final String OwnMob){
        db.collection("carSpotsStatus").document("queue")
                .set(preSpotStatus)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
//                        changeTheSpotStatus(spotid);
                        updateCarInfo(carId,curP,fullP,OwnMob);
                        Toast.makeText(getApplicationContext(),"book successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });
    }









    public void updateCarInfo(String carId,String curP,String fullP,String OwnMob){


        HashMap<String,String> data =new HashMap<>();
        data.put("carIdNumber",userdata.carId);
        data.put("curPowerLevel",curP);
        data.put("fullPowerLevel",fullP);
        data.put("ownerMob",com.example.parkapp.userDetails.userdata.userMobileNo);
        data.put("status","inQueue");







        String timeNow=String.valueOf(java.time.LocalTime.now());
        String[] tArray=timeNow.split(":");
        int h=Integer.valueOf(tArray[0]);
        int m=Integer.valueOf(tArray[1]);

        int newH=h+Integer.valueOf(timesp.getSelectedItem().toString());
        int newM=m+Integer.valueOf(timespMin.getSelectedItem().toString());

        if(newM>=60){
            newM=newM-60;
            newH=newH+1;
        }


        String dateToday=String.valueOf(java.time.LocalDate.now());
        String[] dArray=dateToday.split("-");
        int day=Integer.valueOf(dArray[2]);
        int month=Integer.valueOf(dArray[1]);
        int year=Integer.valueOf(dArray[0]);
        int newDay=day,newMonth=month,newYear=year;
        if(newH>=24){
            newDay=day+1;
            if(newDay>30){
                newDay=1;
                newMonth=newMonth+1;
                if(newMonth>12){
                    newMonth=1;
                    newYear=newYear+1;

                }
            }
        }


        String lastParkedDate= String.format("%s-%s-%s", String.valueOf(year), String.valueOf(month), String.valueOf(day));//String.valueOf(java.time.LocalDate.now());
        String expectedRecievingDate=String.format("%s-%s-%s", String.valueOf(newYear), String.valueOf(newMonth), String.valueOf(newDay));//String.valueOf(java.time.LocalDate.now());
        String lastParkedTime=String.format("%s::%s", String.valueOf(h), String.valueOf(m));//String.valueOf(java.time.LocalTime.now());
        String expectedRecievingTime=String.format("%s::%s", String.valueOf(newH), String.valueOf(newM));//String.valueOf(java.time.LocalTime.now());


        data.put("expectedRecievingDate",expectedRecievingDate);
        data.put("expectedRecievingTime",expectedRecievingTime);





        db.collection("cars").document(carId)
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully written!");
//                        changeTheSpotStatus(spotid);
//                        updateCarInfo(carId,curP,fullP,OwnMob);
                        updateUserInfo();
                        Toast.makeText(getApplicationContext(),"book successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                    }
                });

    }





    private void updateUserInfo(){



        String timeNow=String.valueOf(java.time.LocalTime.now());
        String[] tArray=timeNow.split(":");
        int h=Integer.valueOf(tArray[0]);
        int m=Integer.valueOf(tArray[1]);

        int newH=h+Integer.valueOf(timesp.getSelectedItem().toString());
        int newM=m+Integer.valueOf(timespMin.getSelectedItem().toString());

        if(newM>=60){
            newM=newM-60;
            newH=newH+1;
        }


        String dateToday=String.valueOf(java.time.LocalDate.now());
        String[] dArray=dateToday.split("-");
        int day=Integer.valueOf(dArray[2]);
        int month=Integer.valueOf(dArray[1]);
        int year=Integer.valueOf(dArray[0]);
        int newDay=day,newMonth=month,newYear=year;
        if(newH>=24){
            newDay=day+1;
            if(newDay>30){
                newDay=1;
                newMonth=newMonth+1;
                if(newMonth>12){
                    newMonth=1;
                    newYear=newYear+1;

                }
            }
        }


        String lastParkedDate= String.format("%s-%s-%s", String.valueOf(year), String.valueOf(month), String.valueOf(day));//String.valueOf(java.time.LocalDate.now());
        String expectedRecievingDate=String.format("%s-%s-%s", String.valueOf(newYear), String.valueOf(newMonth), String.valueOf(newDay));//String.valueOf(java.time.LocalDate.now());
        String lastParkedTime=String.format("%s::%s", String.valueOf(h), String.valueOf(m));//String.valueOf(java.time.LocalTime.now());
        String expectedRecievingTime=String.format("%s::%s", String.valueOf(newH), String.valueOf(newM));//String.valueOf(java.time.LocalTime.now());






        userdata.userPrevDetailsMap.put("lastParkedDate",lastParkedDate);
        userdata.userPrevDetailsMap.put("lastParkedTime",lastParkedTime);
        userdata.userPrevDetailsMap.put("expectedRecievingDate",expectedRecievingDate);
        userdata.userPrevDetailsMap.put("expectedRecievingTime",expectedRecievingTime);


        userdata.userPrevDetailsMap.put("carId",userdata.carId);
        userdata.userPrevDetailsMap.put("carModel",userdata.carModel);
        userdata.userPrevDetailsMap.put("name",userdata.name);
        userdata.userPrevDetailsMap.put("status","in queue");
        userdata.userPrevDetailsMap.put("spotId","None");
        //todo : add other details as well

//        userdata.userPrevDetailsMap.put("spotId",sp.getSelectedItem().toString());





        db.collection("users").document(com.example.parkapp.home.userMobNo)
                .set(userdata.userPrevDetailsMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"the spot is booked Successfully",Toast.LENGTH_LONG).show();
                        Log.d("TAG", "DocumentSnapshot successfully written!");

                        String userMobileNo=userdata.userMobileNo;
                        Intent i=new Intent(BOOK.this,home.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        i.putExtra("userMobNo",userMobileNo);
                        startActivity(i);

                        userdata.updateTheUserData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error writing document", e);
                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                });


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
//        HashMap<String,String> statusMap=new HashMap<>();
//        statusMap.put(spotId,"True");
        preSpotStatus.put(spotId,"False");

        db.collection("carSpotsStatus").document("currentStatus")
                .set(preSpotStatus)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"the spot is booked Successfully",Toast.LENGTH_LONG).show();
                        Log.d("TAG", "DocumentSnapshot successfully written!");
                        updateUserInfo();
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



    public void getPreviousVehicleStatus(){


        DocumentReference docRef = db.collection("carSpotsStatus").document("currentStatus");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Tag", "DocumentSnapshot data: " + document.getData());
                        preSpotStatus=(HashMap<String, Object>) document.getData();
                        getAndAssignTheFreeSpots();
                    } else {
                        Log.d("TAG", "No such document");
                        Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                    Toast.makeText(getApplicationContext(),"SomeThing went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getAndAssignTheFreeSpots(){

        for(int i=1;i<=totalSpots;i++){
            if(((String)preSpotStatus.get(String.valueOf(i))).equals("True")){
                arraySpace.add(String.valueOf(i));
            }
        }

        sp = (Spinner) findViewById(R.id.selectSpace);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpace);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter1);

        if(arraySpace.size()==0){
            Button bookBtn=(Button)findViewById(R.id.book);
            bookBtn.setClickable(false);
            bookBtn.setText("NO Spot");
            bookBtn.setBackgroundColor(Color.RED);
        }



    }







}




