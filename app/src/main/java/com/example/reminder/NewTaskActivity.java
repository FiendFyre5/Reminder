package com.example.reminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {
    TextView titlepage, addtitle;
    EditText titleDoes,descDoes,dateDoes;
    Button btnsave,btncancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        titlepage=findViewById(R.id.titlepage);
        addtitle=findViewById(R.id.addtitle);
        titleDoes=findViewById(R.id.titleDoes);
        descDoes=findViewById(R.id.desc);
        dateDoes=findViewById(R.id.timeCount);
        titleDoes=findViewById(R.id.titleDoes);
        btnsave=findViewById(R.id.btnSaveTask);
        btncancel=findViewById(R.id.btnCancel);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // insert data to database
                reference= FirebaseDatabase.getInstance().getReference().child("DoesApp").child("Does"+doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("titledoes").setValue(titleDoes.getText().toString());
                        snapshot.getRef().child("descdoes").setValue(descDoes.getText().toString());
                        snapshot.getRef().child("datedoes").setValue(dateDoes.getText().toString());
                        Intent a = new Intent(NewTaskActivity.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}