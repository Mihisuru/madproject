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

public class TicketsActivity extends AppCompatActivity {
    EditText ticketCode,getIn,getOff,clas,date,NOP;
    Button save,show;
    DatabaseReference ticketDbRef;
    Ticket tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        ticketCode = findViewById(R.id.ticketCode);
        getIn = findViewById(R.id.getIn);
        getOff = findViewById(R.id.getOff);
        clas = findViewById(R.id.clas);
        date = findViewById(R.id.date);
        NOP = findViewById(R.id.numPassenger);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);

        tk = new Ticket();

        ticketDbRef= FirebaseDatabase.getInstance().getReference().child("Ticket");

        final String verify = getIntent().getExtras().getString("Verify");
        String check = "chathuranga4lk12@gmail.com";

        if(verify.equals(check)){

        }else {
            show.setVisibility(View.INVISIBLE);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                ticketDbRef = FirebaseDatabase.getInstance().getReference().child("Ticket");
                try{
                    if(TextUtils.isEmpty(ticketCode.getText().toString())){
                        Toast.makeText(getApplicationContext()," is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(getIn.getText().toString())){
                        Toast.makeText(getApplicationContext()," is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(getOff.getText().toString())){
                        Toast.makeText(getApplicationContext()," is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(clas.getText().toString())){
                        Toast.makeText(getApplicationContext()," is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(date.getText().toString())){
                        Toast.makeText(getApplicationContext()," is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(NOP.getText().toString())){
                        Toast.makeText(getApplicationContext()," is Emplty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        tk.setTicketCode(ticketCode.getText().toString());
                        tk.setGetIn(getIn.getText().toString());
                        tk.setGetOff(getOff.getText().toString());
                        tk.setClas(clas.getText().toString());
                        tk.setDate(date.getText().toString());
                        tk.setNOP(NOP.getText().toString());

                        ticketDbRef.push().setValue(tk);
                        Toast.makeText(getApplicationContext()," Success",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Please Check Your Entered Details",Toast.LENGTH_SHORT).show();
                    ticketCode.setText(e.toString());
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketsActivity.this, ShowTicket.class);
                startActivity(intent);
            }
        });
    }
}