package com.example.etrain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FeedbackActivity extends AppCompatActivity {

    EditText feedback,email;
    Button save,show;
    DatabaseReference feedbackDbRef,databaseReference;
    Feedback fb;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        feedback = findViewById(R.id.feedback);
        email = findViewById(R.id.email);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);
        listView = findViewById(R.id.listView);

        fb = new Feedback();

        final String verify = getIntent().getExtras().getString("Verify");
        String check = "chathuranga4lk12@gmail.com";

        if(verify.equals(check)){
        }else {
            show.setVisibility(View.INVISIBLE);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Feedback value = dataSnapshot.getValue(Feedback.class);
                arrayList.add(value.getFeedback());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        feedbackDbRef= FirebaseDatabase.getInstance().getReference().child("Feedback").child("feedback");

        save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                feedbackDbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
                try{
                    if(TextUtils.isEmpty(feedback.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Feedback is Empty",Toast.LENGTH_SHORT).show();
                    }
                    if(isEmailValid(email.getText().toString()) == false){
                        Toast.makeText(getApplicationContext(),"Please Enter Valid Email",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Email is Empty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        fb.setFeedback(feedback.getText().toString());
                        fb.setEmail(email.getText().toString());

                        feedbackDbRef.push().setValue(fb);
                        Toast.makeText(getApplicationContext()," Success",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Please Check Your Entered Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackActivity.this, ShowFeedback.class);
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