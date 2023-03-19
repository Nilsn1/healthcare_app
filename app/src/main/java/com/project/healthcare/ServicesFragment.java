package com.project.healthcare;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ServicesFragment extends Fragment {

    CardView dental, eye;

    public ServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        dental = view.findViewById(R.id.dental);
        eye = view.findViewById(R.id.eye);

        dental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeFragment(new DentalFragment());

            }
        });

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeFragment(new EyeFragment());

            }
        });

        return view;
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainContainer, fragment);
        ft.commit();
    }
}