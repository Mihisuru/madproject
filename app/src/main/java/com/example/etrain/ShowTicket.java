package com.example.etrain;

import android.os.Bundle;
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

public class ShowTicket extends AppCompatActivity {
    Button delete,update,show;
    EditText ticketCode,getIn,getOff,clas,date,NOP;
    TextView id;
    DatabaseReference ticketDbRefDbRef;
    Ticket tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_ticket);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        show = findViewById(R.id.show);

        ticketCode = findViewById(R.id.ticketCode);
        getIn = findViewById(R.id.getIn);
        getOff= findViewById(R.id.getOff);
        clas = findViewById(R.id.clas);
        date = findViewById(R.id.date);
        NOP = findViewById(R.id.NOP);
        id = findViewById(R.id.idd);

        tk = new Ticket();

        ticketDbRefDbRef= FirebaseDatabase.getInstance().getReference().child("Ticket");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                database.getReference("Ticket").orderByChild("ticketCode").equalTo(ticketCode.getText().toString()).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    Ticket d = data.getValue(Ticket.class);
                                    String ID;
                                    ID = data.getKey().toString();
                                    id.setText(ID);

                                    ticketCode.setText(d.getTicketCode().toString());
                                    getIn.setText(d.getGetIn().toString());
                                    getOff.setText(d.getGetOff().toString());
                                    clas.setText(d.getClas().toString());
                                    date.setText(d.getDate().toString());
                                    NOP.setText(d.getNOP().toString());
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
                ticketDbRefDbRef = FirebaseDatabase.getInstance().getReference();
                ticketDbRefDbRef.child("Ticket").child(id.getText().toString()).child("clas").setValue(clas.getText().toString().trim());
                ticketDbRefDbRef.child("Ticket").child(id.getText().toString()).child("date").setValue(date.getText().toString().trim());
                ticketDbRefDbRef.child("Ticket").child(id.getText().toString()).child("getIn").setValue(getIn.getText().toString().trim());
                ticketDbRefDbRef.child("Ticket").child(id.getText().toString()).child("getOut").setValue(getOff.getText().toString().trim());
                ticketDbRefDbRef.child("Ticket").child(id.getText().toString()).child("nop").setValue(NOP.getText().toString().trim());
                ticketDbRefDbRef.child("Ticket").child(id.getText().toString()).child("ticketCode").setValue(ticketCode.getText().toString().trim());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketDbRefDbRef = FirebaseDatabase.getInstance().getReference().child("Ticket").child(id.getText().toString());
                ticketDbRefDbRef.removeValue();
                clas.setText("");
                date.setText("");
                getOff.setText("");
                getIn.setText("");
                NOP.setText("");
                ticketCode.setText("");
                id.setText("");
                Toast.makeText(getApplicationContext(),"Successfully Delete",Toast.LENGTH_SHORT).show();
            }
        });
    }
}