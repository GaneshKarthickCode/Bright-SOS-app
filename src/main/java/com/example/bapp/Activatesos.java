package com.example.bapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Activatesos extends AppCompatActivity {
     TextView text;
     EditText edittext1,username2,edittext2;
     Button button,save;
    DBHelper DB;
    private Context context;
     FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activatesos);
        getSupportActionBar().hide();

        text=findViewById(R.id.text);
        edittext1=findViewById(R.id.edittext1);
        username2=findViewById(R.id.username2);
        edittext2=findViewById(R.id.edittext2);
        button=findViewById(R.id.button);
        save=findViewById(R.id.save);
        context = this;
        DB = new DBHelper(this);

        /*fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

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
                                edittext1.setText(lat+" , "+longt);
                                Toast.makeText(Activatesos.this,"Success",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    }else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                }
            }
        });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edittext1.getText().toString();
                String phonenumber = edittext2.getText().toString();
                Intent intent = new Intent(Activatesos.this,Activity2.class);
                intent.putExtra("keyname",username);
                intent.putExtra("keyphonenumber",phonenumber);
                startActivity(intent);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username2TXT = username2.getText().toString();
                String phonenumberTXT = edittext2.getText().toString();



                Boolean checkinsertdata = DB.insertuserdata(username2TXT, phonenumberTXT);
                if(checkinsertdata==true)
                    Toast.makeText(Activatesos.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Activatesos.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
    }


}