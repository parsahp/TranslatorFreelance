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

import java.util.Random;

public class TaskFormActivity extends AppCompatActivity {
    EditText name, description;
    Spinner language;
    Button add, back;
    FirebaseAuth mFirebaseAuth;
    String clientId, translatorId;

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
                clientId = userId;
                translatorId = " ";
                Random random = new Random();
                int rand = random.nextInt(1000000000);
                DatabaseReference mRef =  database.getReference().child("Tasks").child("" + rand);
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
