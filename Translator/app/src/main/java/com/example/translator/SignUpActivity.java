package com.example.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    EditText name, lastName, charge, city, state, email, phoneNumber;
    MultiSpinner mySpinner;
    Button submit;
    //RadioButton radioTranslator, radioClient;
    RadioGroup radioGroup;
    RadioButton radioButton;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mySpinner = (MultiSpinner) findViewById(R.id.languages);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.languages));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        charge = findViewById(R.id.charge);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        submit = findViewById(R.id.submit);
        //radioTranslator = findViewById(R.id.radio_translator);
        //radioClient = findViewById(R.id.radio_client);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database =  FirebaseDatabase.getInstance();
                FirebaseUser user =  mFirebaseAuth.getCurrentUser();
                String userId = user.getUid();
                DatabaseReference mRef =  database.getReference().child("Users").child(userId);
                mRef.child(userId);
                mRef.child("firstName").setValue(name.getText().toString());
                mRef.child("lastName").setValue(lastName.getText().toString());
                ArrayList<String> lan = new ArrayList<String>();
                String firstLang = mySpinner.getSelectedItem().toString();
                lan.add(firstLang);
                //lan.add("Spanish");
                mRef.child("languages").setValue(lan);
                mRef.child("charge").setValue(charge.getText().toString());
                mRef.child("profile").setValue("prof");
                mRef.child("city").setValue(city.getText().toString());
                mRef.child("state").setValue(state.getText().toString());
                mRef.child("verify").setValue(false);
                mRef.child("email").setValue(email.getText().toString());
                mRef.child("phoneNumber").setValue(phoneNumber.getText().toString());
                mRef.child("rating").setValue(0.0);
                int selectId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectId);
                mRef.child("userType").setValue(radioButton.getText());
                //mRef.child("typeTranslator").setValue(radioTranslator);
                //mRef.child("typeClient").setValue(radioClient);

                Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(i);
            };
        });
    }
}
