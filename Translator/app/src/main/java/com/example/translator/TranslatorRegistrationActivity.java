package com.example.translator;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TranslatorRegistrationActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    EditText firstName, lastName, city, state, chargingFee, email, phoneNumber, bio;
    Spinner mySpinner;
    Button register;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TranslatorRegistrationActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.languages));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        chargingFee = findViewById(R.id.charge);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        register = findViewById(R.id.register);
        bio = findViewById(R.id.bio);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database =  FirebaseDatabase.getInstance();
                FirebaseUser user =  mFirebaseAuth.getCurrentUser();
                String userId = user.getUid();
                DatabaseReference mRef =  database.getReference().child("Users").child(userId);
                mRef.child(userId);
                mRef.child("firstName").setValue(firstName.getText().toString());
                mRef.child("lastName").setValue(lastName.getText().toString());
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(mySpinner.getSelectedItem().toString());
                mRef.child("languages").setValue(arrayList);
                mRef.child("city").setValue(city.getText().toString());
                mRef.child("state").setValue(state.getText().toString());
                mRef.child("charge").setValue(chargingFee.getText().toString());
                mRef.child("email").setValue(email.getText().toString());
                mRef.child("phoneNumber").setValue(phoneNumber.getText().toString());
                mRef.child("rating").setValue(0);
                mRef.child("profile").setValue(bio.getText().toString());
                Intent i = new Intent(TranslatorRegistrationActivity.this, TranslatorProfileActivity.class);
                startActivity(i);
            }
        });
    }
}
