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

public class ShowParcel extends AppCompatActivity {
    Button delete,update,show;
    EditText serialNumber,from,to,weight,date,description;
    TextView id;
    DatabaseReference parcelDbRefDbRef;
    Parcel pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_parcel);

        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        show = findViewById(R.id.show);

        serialNumber = findViewById(R.id.serialNumber);
        from = findViewById(R.id.from);
        to= findViewById(R.id.to);
        weight = findViewById(R.id.weight);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);
        id = findViewById(R.id.idd);

        pr = new Parcel();

        parcelDbRefDbRef= FirebaseDatabase.getInstance().getReference().child("Parcel");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                database.getReference("Parcel").orderByChild("serialNumber").equalTo(serialNumber.getText().toString()).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    Parcel d = data.getValue(Parcel.class);
                                    String ID;
                                    ID = data.getKey().toString();
                                    id.setText(ID);

                                    serialNumber.setText(d.getSerialNumber().toString());
                                    from.setText(d.getFrom().toString());
                                    to.setText(d.getTo().toString());
                                    weight.setText(d.getWeight().toString());
                                    date.setText(d.getDate().toString());
                                    description.setText(d.getDescription().toString());
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
                parcelDbRefDbRef = FirebaseDatabase.getInstance().getReference();
                parcelDbRefDbRef.child("Parcel").child(id.getText().toString()).child("serialNumber").setValue(serialNumber.getText().toString().trim());
                parcelDbRefDbRef.child("Parcel").child(id.getText().toString()).child("from").setValue(date.getText().toString().trim());
                parcelDbRefDbRef.child("Parcel").child(id.getText().toString()).child("to").setValue(to.getText().toString().trim());
                parcelDbRefDbRef.child("Parcel").child(id.getText().toString()).child("weight").setValue(weight.getText().toString().trim());
                parcelDbRefDbRef.child("Parcel").child(id.getText().toString()).child("date").setValue(date.getText().toString().trim());
                parcelDbRefDbRef.child("Parcel").child(id.getText().toString()).child("description").setValue(description.getText().toString().trim());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcelDbRefDbRef = FirebaseDatabase.getInstance().getReference().child("Parcel").child(id.getText().toString());
                parcelDbRefDbRef.removeValue();
                serialNumber.setText("");
                date.setText("");
                from.setText("");
                to.setText("");
                description.setText("");
                weight.setText("");
                id.setText("");
                Toast.makeText(getApplicationContext(),"Successfully Delete",Toast.LENGTH_SHORT).show();
            }
        });
    }
}