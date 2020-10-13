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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText email,nic,password;
    Button subbtn;
    DatabaseReference db;
    RegJava mem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_registration);

        email = findViewById(R.id.email);
        nic = findViewById(R.id.nic);
        password = findViewById(R.id.password);
        subbtn = findViewById(R.id.update);

        mem = new RegJava();


        db = FirebaseDatabase.getInstance().getReference().child("Users");
        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString();
                String NIC1 = nic.getText().toString();
                String Pass = password.getText().toString();
                try {
                    if(isEmailValid(email.getText().toString()) == false){
                        Toast.makeText(getApplicationContext(),"Please Enter Valid Email",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(Email)) {
                        Toast.makeText(RegistrationActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                    }
                    else if (password.getText().toString().length() < 8){
                        Toast.makeText(RegistrationActivity.this, "Password are to short", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(NIC1)) {
                        Toast.makeText(RegistrationActivity.this, "NIC is Empty", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(Pass)) {
                        Toast.makeText(RegistrationActivity.this, "Password is Empty", Toast.LENGTH_SHORT).show();
                    }else {

                        mem.setEmail(Email);
                        mem.setNIC(NIC1);
                        mem.setPassword(Pass);

                        db.push().setValue(mem);

                        Toast.makeText(RegistrationActivity.this, "Successfull Added", Toast.LENGTH_SHORT).show();

                        email.setText("");
                        nic.setText("");
                        password.setText("");

                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                }catch(Exception e){
                    Toast.makeText(RegistrationActivity.this, "Not Successfull Added", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}