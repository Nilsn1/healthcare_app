package com.project.healthcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    TextView username;

    FirebaseAuth authprofile;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        username = view.findViewById(R.id.username);

        authprofile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authprofile.getCurrentUser();

        if (firebaseUser != null) {
            String userID = firebaseUser.getUid();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UsersDetails");
            reference.child(userID).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String value = snapshot.getValue(String.class);

                    username.setText("Hello, " + value + "!");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return view;
    }
}