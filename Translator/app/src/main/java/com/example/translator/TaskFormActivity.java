package com.example.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class TaskFormActivity extends AppCompatActivity {
    EditText name, description;
    Spinner language;
    Button add, back;
    FirebaseAuth mFirebaseAuth;
    String clientId, translatorId;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        mFirebaseAuth = FirebaseAuth.getInstance();

        language = (Spinner) findViewById(R.id.language);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TaskFormActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.languages));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(myAdapter);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        add = findViewById(R.id.add);
        back = findViewById(R.id.back);
        language = findViewById(R.id.language);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TaskFormActivity.this, ClientTaskActivity.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database =  FirebaseDatabase.getInstance();
                FirebaseUser user =  mFirebaseAuth.getCurrentUser();
                String userId = user.getUid();
                Random random = new Random();
                int rand = random.nextInt(1000000000);
                mRef =  database.getReference().child("Tasks").child("" + rand);
                final DatabaseReference clientFirstName = database.getReference().child("Users").child(userId).child("firstName");
                DatabaseReference clientLastName = database.getReference().child("Users").child(userId).child("lastName");
                DatabaseReference clientRating = database.getReference().child("Users").child(userId).child("rating");
                DatabaseReference clientEmail = database.getReference().child("Users").child(userId).child("email");
                DatabaseReference clientPhoneNumber = database.getReference().child("Users").child(userId).child("phoneNumber");
                clientFirstName.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String firstNameString = dataSnapshot.getValue(String.class);
                        mRef.child("clientFirstName").setValue(firstNameString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                clientLastName.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String lastNameString = dataSnapshot.getValue(String.class);
                        mRef.child("clientLastName").setValue(lastNameString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                clientRating.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ratingString = dataSnapshot.getValue(String.class);
                        mRef.child("clientRating").setValue(ratingString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                clientEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String emailString = dataSnapshot.getValue(String.class);
                        mRef.child("clientEmail").setValue(emailString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                clientPhoneNumber.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String phoneNumberString = dataSnapshot.getValue(String.class);
                        mRef.child("clientPhoneNumber").setValue(phoneNumberString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                clientId = userId;
                translatorId = " ";
                mRef.child("translatorId").setValue(translatorId);
                mRef.child("clientId").setValue(clientId);
                mRef.child("description").setValue(description.getText().toString());
                mRef.child("language").setValue(language.getSelectedItem().toString());
                mRef.child("jobName").setValue(name.getText().toString());
                mRef.child("isActive").setValue("true");
                Intent i = new Intent(TaskFormActivity.this, ClientProfileActivity.class);
                startActivity(i);
            }
        });
    }
}
