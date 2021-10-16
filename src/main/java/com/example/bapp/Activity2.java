package com.example.bapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Activity2 extends AppCompatActivity {
     TextView name,number;
     Button alert,btn,power;
    SharedPreferences sharedPreferences;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getSupportActionBar().hide();
        name=findViewById(R.id.textview1);
        number=findViewById(R.id.textview2);
        alert=findViewById(R.id.alert);
        btn=findViewById(R.id.btn);
        power=findViewById(R.id.power);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        sharedPreferences=getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        String username = getIntent().getStringExtra("keyname");
        String phonenumber = getIntent().getStringExtra("keyphonenumber");
        name.setText(username);
        number.setText(phonenumber);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location!=null){
                                    Double lat=location.getLatitude();
                                    Double longt=location.getLongitude();
                                    name.setText("BRIGHT ALERT ! - Im in trouble,i have sent my Location coordinates.Please find me out by tracking my location. Location :" +lat+" , "+longt);
                                    Toast.makeText(Activity2.this,"Success",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                }
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(Activity2.this, MainActivity.class);
                startActivity(intentL);
                finish();
                Toast.makeText(Activity2.this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Activity2.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendMessage();
                } else {
                    ActivityCompat.requestPermissions(Activity2.this,new String[]{Manifest.permission.SEND_SMS},100);
                }

            }
        });



    }


    private void sendMessage() {
        String sMessage = name.getText().toString().trim();
        String sPhone = number.getText().toString().trim();

        if (!sMessage.equals("")&&!sPhone.equals("")) {
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(sPhone, null, sMessage, null, null);

            Toast.makeText(getApplicationContext(), "SMS sent successfully!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"Enter value first",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }else {
            Toast.makeText(getApplicationContext(),"Permission accessed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
    }
}
