package com.example.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientRegistrationActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    EditText firstName, lastName, city, state, email, phoneNumber, bio;
    Button register;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
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
                mRef.child("city").setValue(city.getText().toString());
                mRef.child("state").setValue(state.getText().toString());
                mRef.child("email").setValue(email.getText().toString());
                mRef.child("phoneNumber").setValue(phoneNumber.getText().toString());
                mRef.child("rating").setValue("0");
                mRef.child("profile").setValue(bio.getText().toString());
                Intent i = new Intent(ClientRegistrationActivity.this, ClientProfileActivity.class);
                startActivity(i);
            }
        });
    }
}
