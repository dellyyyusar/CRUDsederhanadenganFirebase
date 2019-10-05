package com.pamekasan.cobafirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    TextView ubahdata;
    EditText dataperubahan;
    Button kirim;

    DatabaseReference reference;
    StorageReference storage;

    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ubahdata = findViewById(R.id.ubahdata);
        dataperubahan = findViewById(R.id.dataperubahan);
        kirim = findViewById(R.id.kirim);

        member = new Member();

        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child("ArekLancor");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ubahdata.setText(dataSnapshot.child("harga_ticket").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_SHORT);
            }
        });

        //kirim.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        int dataperubahan1 = Integer.parseInt(dataperubahan.getText().toString().trim());

        //        member.setDataperubahan0(dataperubahan1);

        //       reference.push().setValue(member);
        //    }
        //});

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int dataperubahan1 = Integer.parseInt(dataperubahan.getText().toString().trim());

                        //member.setDataperubahan0(dataperubahan1);
                        //dataSnapshot.getRef().push().child("harga_ticket").setValue(dataperubahan1);
                        dataSnapshot.getRef().child("harga_ticket").setValue(dataperubahan1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });





    }
}
