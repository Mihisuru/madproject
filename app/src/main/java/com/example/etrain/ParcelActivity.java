package com.example.etrain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParcelActivity extends AppCompatActivity {
    EditText serialNumberT,fromT,toT,weightT,dateT,descriptionT;
    Button saveB,showB;
    DatabaseReference parcelDbRef;
    Parcel pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);


        serialNumberT = findViewById(R.id.serialNumber);
        fromT = findViewById(R.id.from);
        toT = findViewById(R.id.to);
        weightT = findViewById(R.id.weight);
        dateT = findViewById(R.id.date);
        descriptionT = findViewById(R.id.description);
        saveB = findViewById(R.id.save);
        showB = findViewById(R.id.show);

        pa = new Parcel();

        parcelDbRef= FirebaseDatabase.getInstance().getReference().child("Parcel");


        final String verify = getIntent().getExtras().getString("Verify");
        String check = "chathuranga4lk12@gmail.com";

        if(verify.equals(check)){
        }else {
            showB.setVisibility(View.INVISIBLE);
        }

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                parcelDbRef = FirebaseDatabase.getInstance().getReference().child("Parcel");
                try{
                    if(TextUtils.isEmpty(serialNumberT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Serial Number is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(fromT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"From is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(toT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"To is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(weightT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Weight is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(dateT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Date is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(descriptionT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Description Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        pa.setSerialNumber(serialNumberT.getText().toString());
                        pa.setFrom(fromT.getText().toString());
                        pa.setTo(toT.getText().toString());
                        pa.setWeight(weightT.getText().toString());
                        pa.setDate(dateT.getText().toString());
                        pa.setDescription(descriptionT.getText().toString());

                        parcelDbRef.push().setValue(pa);
                        Toast.makeText(getApplicationContext()," Success",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Please Check Your Entered Details"+e,Toast.LENGTH_SHORT).show();
                }
            }
        });
        showB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParcelActivity.this, ShowParcel.class);
                startActivity(intent);
            }
        });
    }
}