package com.example.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;

public class TranslatorLookupActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    TextView firstName, lastName, rating, city, state, email, phoneNumber, bio, charge, languages;
    Button backButton;
    //FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator_lookup);
        User user = (User) getIntent().getSerializableExtra("user");

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        rating = findViewById(R.id.rating);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        bio = findViewById(R.id.bio);
        charge = findViewById(R.id.charge);
        languages = findViewById(R.id.languages);
        backButton = findViewById(R.id.back);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        rating.setText(user.getRating());
        city.setText(user.getCity());
        state.setText(user.getState());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        bio.setText(user.getProfile());
        charge.setText(user.getCharge());
        languages.setText(user.getLanguages().get(0));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TranslatorLookupActivity.this, ClientHomepageActivity.class);
                startActivity(i);
            }
        });
        //Log.e("Name" , user.getFirstName());
    }
}
