package com.example.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Task;
import Model.User;

public class TranslatorHomepageActivity extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    ArrayList<Task> tasks = new ArrayList<Task>();
    ArrayList<String> tasksContent = new ArrayList<String>();
    ArrayList<String> tasksId = new ArrayList<String>();
    String userId;
    ListView listView;
    DatabaseReference ref;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    BottomNavigationView bottomNavigationView;
    String clientFirstName;
    String clientLastName;
    String clientRating;
    String clientEmail;
    String clientPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator_homepage);
        btnLogout = findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(TranslatorHomepageActivity.this, LoginActivity.class);
                startActivity(intToMain);
            }
        });

        bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.task:
                        startActivity(new Intent(getApplicationContext(), TranslatorTaskActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), TranslatorProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        FirebaseUser user =  mFirebaseAuth.getCurrentUser();
        userId = user.getUid();
        ref =  database.getReference().child("Tasks");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Task post = (Task) postSnapshot.getValue(Task.class);
                    if (post != null && post.getIsActive() != null) {
                        if (post.getIsActive().equals("true")) {
                            tasksId.add(postSnapshot.getKey()+"");
                            tasks.add(post);
                        }
                    }
                }
                Log.e("Tasks " ,""+tasks.get(0).getJobName());
                Log.e("Task ID", ""+tasksId.get(0));
                for (Task tsk : tasks) {
                    mFirebaseAuth = FirebaseAuth.getInstance();
                    FirebaseDatabase database =  FirebaseDatabase.getInstance();
                    DatabaseReference reff =  database.getReference().child("Users").child(tsk.getClientId());
                    DatabaseReference userFirstName = reff.child("firstName");
                    DatabaseReference userLastName = reff.child("lastName");

                    clientFirstName = tsk.getClientFirstName();
                    clientLastName = tsk.getClientLastName();
                    clientRating = tsk.getClientRating();
                    clientEmail = tsk.getClientEmail();
                    clientPhoneNumber = tsk.getClientPhoneNumber();


                    tasksContent.add(clientFirstName + " " + clientLastName + "\n\nJob Name: " + tsk.getJobName() + "\nLanguage: " + tsk.getLanguage() +
                            "\nDescription: " + tsk.getDescription() + "\nEmail: " + clientEmail + "\nPhone Number: " + clientPhoneNumber + "\n");
                }

                ArrayAdapter arrayAdapter = getAdapter();
                listView = (ListView) findViewById(R.id.window_list);
                listView.setAdapter(arrayAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Task taskk = tasks.get(position);
                        String taskId = tasksId.get(position);
                        ref.child(taskId).child("isActive").setValue("false");
                        ref.child(taskId).child("translatorId").setValue(userId);
                        Intent intent = new Intent(TranslatorHomepageActivity.this, TranslatorTaskActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayAdapter<String> getAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasksContent) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.WHITE);

                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);

                return view;
            }
        };
        return arrayAdapter;
    }
}
