package com.example.etrain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {


    ImageView profile,ticket,parcel,train_schedule,feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ticket = findViewById(R.id.tickectbooking);
        parcel = findViewById(R.id.parcelbooking);
        train_schedule = findViewById(R.id.trainschedule);
        feedback = findViewById(R.id.feedback);
        profile = findViewById(R.id.profile);
        Bundle bundle = getIntent().getExtras();

        final String verify = bundle.getString("Verify");

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, TicketsActivity.class);
                String Vemaill  = verify;
                intent.putExtra("Verify", Vemaill );
                startActivity(intent);
            }
        });
        parcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, ParcelActivity.class);
                String Vemaill  = verify;
                intent.putExtra("Verify", Vemaill );
                startActivity(intent);
            }
        });
        train_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, TrainShedule.class);
                String Vemaill  = verify;
                intent.putExtra("Verify", Vemaill );
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, FeedbackActivity.class);
                String Vemaill  = verify;
                intent.putExtra("Verify", Vemaill );
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, Profile.class);
                String Vemaill  = verify;
                intent.putExtra("Verify", Vemaill );
                startActivity(intent);
            }
        });
    }

}