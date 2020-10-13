package com.example.etrain;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowFeedback extends AppCompatActivity {

    Button delete,update,show;
    EditText feedbackT,email;
    TextView id;
    DatabaseReference feedbackDbRef;
    Feedback fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feedback);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        show = findViewById(R.id.show);
        feedbackT = findViewById(R.id.feedbackT);
        email = findViewById(R.id.email);
        id = findViewById(R.id.idd);

        fb = new Feedback();

        feedbackDbRef= FirebaseDatabase.getInstance().getReference().child("Feedback");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                database.getReference("Feedback").orderByChild("email").equalTo(email.getText().toString()).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    Feedback d = data.getValue(Feedback.class);
                                    String ID;
                                    ID = data.getKey().toString();
                                    String feedback = d.getFeedback().toString();
                                    id.setText(ID);
                                    feedbackT.setText(feedback);
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(TextUtils.isEmpty(feedbackT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Feedback is Empty",Toast.LENGTH_SHORT).show();
                    }
                    if(isEmailValid(email.getText().toString()) == false){
                        Toast.makeText(getApplicationContext(),"Please Enter Valid Email",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Email is Empty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        feedbackDbRef = FirebaseDatabase.getInstance().getReference();
                        feedbackDbRef.child("Feedback").child(id.getText().toString()).child("feedback").setValue(feedbackT.getText().toString().trim());
                        feedbackDbRef.child("Feedback").child(id.getText().toString()).child("email").setValue(email.getText().toString().trim());
                        Toast.makeText(getApplicationContext()," Success",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Error : "+e,Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    if(TextUtils.isEmpty(feedbackT.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Firstly Enter Email Click Show",Toast.LENGTH_SHORT).show();
                    }
                    if(isEmailValid(email.getText().toString()) == false){
                        Toast.makeText(getApplicationContext(),"Please Enter Valid Email",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Firstly Enter Email Click Show",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        feedbackDbRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(id.getText().toString());
                        feedbackDbRef.removeValue();
                        feedbackT.setText("");
                        email.setText("");
                        id.setText("");
                        Toast.makeText(getApplicationContext(),"Successfully Delete",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Error : "+e,Toast.LENGTH_SHORT).show();
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