package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkapp.userDetails.userdata;

public class userLogin extends AppCompatActivity {
    public EditText userName;
    public Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_login);

        Toast.makeText(this, userdata.carId, Toast.LENGTH_SHORT).show();

        userName=(EditText)findViewById(R.id.mobNoValue);

        login=(Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userdata.carId="";

                String userMobileNo=userName.getText().toString();
                Intent i=new Intent(userLogin.this,home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("userMobNo",userMobileNo);
                startActivity(i);

            }
        });

    }
    public void toRegister(View view){

    }
}
