package com.example.parkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class userLogin extends AppCompatActivity {
    public EditText userName;
    public Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userName=(EditText)findViewById(R.id.mobNoValue);

        login=(Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMobileNo=userName.getText().toString();
                Intent i=new Intent(userLogin.this,home.class);
                i.putExtra("userMobNo",userMobileNo);
                startActivity(i);
            }
        });

    }
    public void toRegister(View view){

    }
}
