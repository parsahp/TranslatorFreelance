package com.example.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

public class ClientTaskActivity extends AppCompatActivity {
    Button addTask;
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<String> tasksContent = new ArrayList<String>();
    FirebaseAuth mFirebaseAuth;
    ListView listView;
    BottomNavigationView bottomNavigationView;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_task);

        bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.task);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.task:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ClientProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), ClientHomepageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        FirebaseUser user =  mFirebaseAuth.getCurrentUser();
        userId = user.getUid();
        DatabaseReference ref =  database.getReference().child("Tasks");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Task post = (Task) postSnapshot.getValue(Task.class);
                    if (post != null && post.getClientId() != null) {
                        if (post.getClientId().equals(userId)) {
                            tasks.add(post);
                        }
                    }
                }
                //Log.e("Tasks " ,""+tasks.get(0).getJobName());
                for (Task tsk : tasks) {
                    tasksContent.add(tsk.getJobName() + "\n" + tsk.getLanguage() + "\n" + tsk.getDescription());
                }

                ArrayAdapter arrayAdapter = getAdapter();
                listView = (ListView) findViewById(R.id.window_list);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClientTaskActivity.this, TaskFormActivity.class);
                startActivity(i);
            }
        });
    }

    public ArrayAdapter<String> getAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasksContent);
        return arrayAdapter;
    }
}
