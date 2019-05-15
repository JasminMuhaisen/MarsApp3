package com.example.marsapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private EditText userName, userProfName, userStatus, userCountry, userGender, userRelation, userDOB;
    private Button UpdateAccountSettingsButton;
    protected CircleImageView userProfImage;
    private DatabaseReference settingsuserRef;
    private FirebaseAuth mAuth;
    private  String currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        settingsuserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);


        mtoolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Account Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        userName = findViewById(R.id.settings_username);
        userProfName = findViewById(R.id.settings_profile_full_name);
        userStatus = findViewById(R.id.settings_status);
        userCountry = findViewById(R.id.settings_country);
        userGender = findViewById(R.id.settings_gender);
        userRelation = findViewById(R.id.settings_relationship_status);
        userDOB = findViewById(R.id.settings_dob);
        userProfImage = findViewById(R.id.settings_profile_image);
        UpdateAccountSettingsButton = findViewById(R.id.update_account_settiings_buttons);
\
        settingsuserRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot)
            {
                String myProfileImage = dataSnapshot.child("profileimage").getValue().toString();
                String myUserName = dataSnapshot.child("username").getValue().toString();
                String myProfileName = dataSnapshot.child("fullname").getValue().toString();
                String myProfileStatus = dataSnapshot.child("status").getValue().toString();
                String myDOB = dataSnapshot.child("dob").getValue().toString();
                String myCountry = dataSnapshot.child("country").getValue().toString();
                String myGender = dataSnapshot.child("gender").getValue().toString();
                String myRelationStatus = dataSnapshot.child("relationshipstatus").getValue().toString();


                picasso.with(SettingsActivity.this).load(myProfileImage).placeholder(R.drawable.profile).into(userProfImage);

                userName.set

            }
            @Override
            public void onCancelled (DatabaseError databaseError){

            }});






    }
}

