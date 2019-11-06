package com.example.parkapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;
        LatLng carLocation =new LatLng(20.1498208,85.6694196);
        map.addMarker(new MarkerOptions().position(carLocation).title("your car"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(carLocation,15));



    }
}
