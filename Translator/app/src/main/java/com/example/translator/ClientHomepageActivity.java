package com.example.translator;

import Model.User;
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
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClientHomepageActivity extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    ListView listView;
    BottomNavigationView bottomNavigationView;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    ArrayList<User> translators = new ArrayList<>();
    ArrayList<String> translatorsContent = new ArrayList<String>();
    //ArrayList<String> translatorsContent2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_homepage);

        btnLogout = findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(ClientHomepageActivity.this, LoginActivity.class);
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
                        startActivity(new Intent(getApplicationContext(), ClientTaskActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ClientProfileActivity.class));
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
        String userId = user.getUid();
        DatabaseReference ref =  database.getReference().child("Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    User post = postSnapshot.getValue(User.class);
                    if (post.getUserType().equals("Translator")) {
                        translators.add(post);
                    }
                }
                //Log.e("Translators " ,""+translators.get(0).getFirstName());
                for (User usr : translators) {
                    if (usr.getLanguages() != null && usr.getLanguages().get(0) != null) {
                        translatorsContent.add(usr.getFirstName() + " " + usr.getLastName() + "\n\nCurrent Score: " +
                                usr.getRating() + "\nLanguage(s): " + usr.getLanguages().get(0)
                                + "\nLocation: " + usr.getCity() + ", " + usr.getState() + "\n$ " + usr.getCharge() + "/hr\nEmail: " + usr.getEmail() + "\nPhone Number: " + usr.getPhoneNumber() + "\n");
                    }
                }

                ArrayAdapter arrayAdapter = getAdapter();

                listView = (ListView) findViewById(R.id.window_list);
                listView.setAdapter(arrayAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        User userr = translators.get(position);
                        Intent intent = new Intent(ClientHomepageActivity.this, TranslatorLookupActivity.class);
                        intent.putExtra("user", userr);
                        //based on item add info to intent
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Log.e("Translators " ,""+translators.size());
        /*for (User usr : translators) {
            translatorsContent.add(usr.getFirstName() + " " + usr.getLastName() + "\t \t \t"+ usr.getRating() +"\n" + usr.getLanguages().get(0)
            + "\n" + usr.getCity() + ", " + usr.getState() + "\n$ " + usr.getCharge());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, translatorsContent);

        listView = (ListView) findViewById(R.id.window_list);
        listView.setAdapter(arrayAdapter);*/
    }
    public ArrayAdapter<String> getAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, translatorsContent) {

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
