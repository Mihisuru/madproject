package com.example.etrain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button loginbtn,btn_signup;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        btn_signup = findViewById(R.id.btn_signup);

        db = FirebaseDatabase.getInstance().getReference().child("Users");
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot loginSnapshot: dataSnapshot.getChildren()) {

                            String email1 = loginSnapshot.child("email").getValue().toString();
                            String pass1 = loginSnapshot.child("password").getValue().toString();
                            if(isEmailValid(email.getText().toString()) == false){
                                Toast.makeText(getApplicationContext(),"Please Enter Valid Email",Toast.LENGTH_SHORT).show();
                            }
                            if ((email.getText().toString().equals(email1))&& (password.getText().toString().equals(pass1))) {
                                Toast.makeText(getApplicationContext(), "Login Sucessful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, home.class);
                                String Vemaill  = email.getText().toString();
                                intent.putExtra("Verify", Vemaill );
                                startActivity(intent);
                            }
                            else if(TextUtils.isEmpty(email.getText().toString())){
                                Toast.makeText(getApplicationContext(),"Please Firstly Enter Email",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
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