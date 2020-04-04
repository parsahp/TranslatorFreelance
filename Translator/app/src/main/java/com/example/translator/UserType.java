package com.example.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserType extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button next;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        mFirebaseAuth = FirebaseAuth.getInstance();

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        next = findViewById(R.id.button2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database =  FirebaseDatabase.getInstance();
                FirebaseUser user =  mFirebaseAuth.getCurrentUser();
                String userId = user.getUid();
                DatabaseReference mRef =  database.getReference().child("Users").child(userId);
                mRef.child(userId);
                int selectId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectId);
                mRef.child("userType").setValue(radioButton.getText());
                if (radioButton.getText().equals("Client")) {
                    Intent i = new Intent(UserType.this, ClientRegistrationActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(UserType.this, TranslatorRegistrationActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
