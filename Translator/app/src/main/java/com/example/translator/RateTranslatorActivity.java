package com.example.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class RateTranslatorActivity extends AppCompatActivity {
    TextView textView, score;
    ImageButton upArrow, downArrow;
    Button back;
    FirebaseAuth mFirebaseAuth;
    String userId;
    DatabaseReference ref;
    String translatorScore;
    DatabaseReference userFirstName;
    DatabaseReference userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_translator);
        userId = (String) getIntent().getSerializableExtra("task");

        textView = findViewById(R.id.textView);
        score = findViewById(R.id.score);

        upArrow = findViewById(R.id.upArrow);
        downArrow = findViewById(R.id.downArrow);

        back = findViewById(R.id.back);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        FirebaseUser user =  mFirebaseAuth.getCurrentUser();
        ref =  database.getReference().child("Users").child(userId);

        userFirstName = ref.child("firstName");
        userScore = ref.child("rating");

        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(translatorScore) + 1;
                ref.child("rating").setValue(s+"");

                userScore.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ratingString = dataSnapshot.getValue(String.class);
                        translatorScore = ratingString;
                        score.setText(ratingString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(translatorScore) - 1;
                ref.child("rating").setValue(s+"");

                userScore.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ratingString = dataSnapshot.getValue(String.class);
                        translatorScore = ratingString;
                        score.setText(ratingString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        userFirstName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstNameString = dataSnapshot.getValue(String.class);
                textView.setText("Change " + firstNameString + "'s Score");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ratingString = dataSnapshot.getValue(String.class);
                translatorScore = ratingString;
                score.setText(ratingString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RateTranslatorActivity.this, ClientTaskActivity.class);
                startActivity(i);
            }
        });

    }
}
