package com.example.marsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView myFriendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        myFriendList = (RecyclerView)findViewById(R.id.friend_list);
        myFriendList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(  this);
        linearLayoutManager.setReverseLayout (true);
        linearLayoutManager.setStackFromEnd (true);
        myFriendList.setLayoutManager(linearLayoutManager);

        DisplayAllFriends ();
    }

    private void DisplayAllFriends () {
        FirebaseRecyclerAdapter<Friends, FriendsViewHolder> firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<Friends, FriendsViewHolder>
                (
                        Friends.class,
                        )

    }

    public static class FriendsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;



        }



        }

    }

}
