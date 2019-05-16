package com.example.marsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonProfileActivity extends AppCompatActivity {


    private TextView userName, userProfName, userStatus, userCountry, userGender, userRelation, userDOB;
    private CircleImageView userProfileImage;
    private Button SenderiendRegbutton, DeclineFriendRequestbutton;
    private DatabaseReference profileUserRef,UserRef;
    private FirebaseAuth mAuth;
    private String senderUserId,recieverUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);

        mAuth = FirebaseAuth.getInstance();

        recieverUserId = getIntent().getExtras().get("visit_user_id").toString();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        intializeFields();

        UserRef.child(recieverUserId).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()){
                    String myProfileImage = dataSnapshot.child("profileimage").getValue().toString();
                    String myUserName = dataSnapshot.child("username").getValue().toString();
                    String myProfileName = dataSnapshot.child("fullname").getValue().toString();
                    String myProfileStatus = dataSnapshot.child("status").getValue().toString();
                    String myDOB = dataSnapshot.child("dob").getValue().toString();
                    String myCountry = dataSnapshot.child("country").getValue().toString();
                    String myGender = dataSnapshot.child("gender").getValue().toString();
                    String myRelationStatus = dataSnapshot.child("relationshipstatus").getValue().toString();

                    Picasso.with(PersonProfileActivity.this).load(myProfileImage).placeholder(R.drawable.profile).into(userProfileImage);

                    userName.setText("@"+ myUserName);
                    userProfName.setText(myProfileName);
                    userStatus.setText(myProfileStatus);
                    userDOB.setText("DOB: "+myDOB);
                    userCountry.setText("Country: "+myCountry);
                    userGender.setText("Gender: "+myGender);
                    userRelation.setText("RelationShip: "+myRelationStatus);

                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError){

            }
        });


    }

    private void intializeFields() {
        userName = findViewById(R.id.person_userrname);
        userProfName = findViewById(R.id.person_full_name);
        userStatus = findViewById(R.id.person_status);
        userCountry = findViewById(R.id.person_country);
        userGender = findViewById(R.id.person_gender);
        userRelation = findViewById(R.id.person_relationship_status);
        userDOB = findViewById(R.id.person_dob);
        userProfileImage = findViewById(R.id.person_profile_pic);
        SenderiendRegbutton = findViewById(R.id.person_send_friend_request_btn);
        DeclineFriendRequestbutton = findViewById(R.id.person_decline_friend_request);


    }
}
