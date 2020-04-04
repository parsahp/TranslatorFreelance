package com.example.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslatorProfileActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    TextView firstName, lastName, rating, city, state, email, phoneNumber, bio, charge, languages;
    FirebaseAuth mFirebaseAuth;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();

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

        bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.task:
                        startActivity(new Intent(getApplicationContext(), TranslatorTaskActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), TranslatorHomepageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        FirebaseUser user =  mFirebaseAuth.getCurrentUser();
        String userId = user.getUid();
        DatabaseReference mRef =  database.getReference().child("Users").child(userId);
        mRef.child(userId);

        DatabaseReference userFirstName = database.getReference().child("Users").child(userId).child("firstName");
        DatabaseReference userLastName = database.getReference().child("Users").child(userId).child("lastName");
        DatabaseReference userRating = database.getReference().child("Users").child(userId).child("rating");
        DatabaseReference userCity = database.getReference().child("Users").child(userId).child("city");
        DatabaseReference userState = database.getReference().child("Users").child(userId).child("state");
        DatabaseReference userEmail = database.getReference().child("Users").child(userId).child("email");
        DatabaseReference userPhoneNumber = database.getReference().child("Users").child(userId).child("phoneNumber");
        DatabaseReference userBio = database.getReference().child("Users").child(userId).child("profile");
        DatabaseReference userCharge = database.getReference().child("Users").child(userId).child("charge");
        DatabaseReference userLanguages = database.getReference().child("Users").child(userId).child("languages");

        userFirstName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstNameString = dataSnapshot.getValue(String.class);
                firstName.setText(firstNameString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userLastName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lastNameString = dataSnapshot.getValue(String.class);
                lastName.setText(lastNameString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userRating.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long ratingString = dataSnapshot.getValue(long.class);
                rating.setText(ratingString+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userCity.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cityString = dataSnapshot.getValue(String.class);
                city.setText(cityString + ",");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userState.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String stateString = dataSnapshot.getValue(String.class);
                state.setText(stateString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String emailString = dataSnapshot.getValue(String.class);
                email.setText(emailString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userPhoneNumber.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String phoneNumberString = dataSnapshot.getValue(String.class);
                phoneNumber.setText(phoneNumberString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userBio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String bioString = dataSnapshot.getValue(String.class);
                bio.setText(bioString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userCharge.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String chargeString = dataSnapshot.getValue(String.class);
                charge.setText("$ " + chargeString+"/hr");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userLanguages.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                List<String> yourStringArray = dataSnapshot.getValue(t);
                languages.setText(yourStringArray.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
