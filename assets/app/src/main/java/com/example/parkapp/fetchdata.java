package com.example.parkapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class fetchdata extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
//        try {
//            URL url = new URL("https://api.myjson.com/bins/k3p10");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while(line != null){
//                line = bufferedReader.readLine();
//                data = data + line;
//            }
//
//
//
//
//
//
//
//
//            JSONArray JA = new JSONArray(data);
//            for(int i =0 ;i <JA.length(); i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed =  "Name:" + JO.get("Name") + "\n"+
//                        "Age:" + JO.get("Age") + "\n"+
//                        "Place:" + JO.get("Place") +  "\n";
//
//                dataParsed = dataParsed + singleParsed +"\n" ;
//
//
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //MainActivity.data.setText(this.dataParsed+"kya hhai");
//        if(MainActivity.ref==1)
//            MainActivity.data.setText("Ho gaya");
//
//        MainActivity.name=MainActivity.name+100;
////        MainActivity.carModel=MainActivity.carModel+100000;
////        MainActivity.CarNo=MainActivity.CarNo+99000000;
//        String temp=String.valueOf(MainActivity.name);
//        Log.d("mess>>>>>>>>>>>>>>>----------------<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",temp);


    }

    public void givesomedata(){
        MainActivity.name="Ujjal Das";
        MainActivity.carModel="Lamborgini";
        MainActivity.CarNo="AS 8264";

    }


}
