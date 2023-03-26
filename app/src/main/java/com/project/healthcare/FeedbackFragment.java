package com.project.healthcare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {

    RatingBar ratingBar;
    EditText co, referringDoctor, referredtowhom, review, suggestion;
    Button btnSend;

    TextView txtrating;

    String mysub, mydes;
    Float myrating;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        ratingBar = view.findViewById(R.id.ratingbar);
        co = view.findViewById(R.id.co);
        referringDoctor = view.findViewById(R.id.referringDoctor);
        referredtowhom = view.findViewById(R.id.referredtowhom);
        review = view.findViewById(R.id.review);
        suggestion = view.findViewById(R.id.suggestion);

        btnSend = view.findViewById(R.id.btnSend);
        txtrating = view.findViewById(R.id.txtrating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                myrating = rating;
                txtrating.setText(" " + rating);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myco = co.getText().toString();
                String myreferringDoctor = referringDoctor.getText().toString();
                String myreferredtowhom = referredtowhom.getText().toString();
                String myreview = review.getText().toString();
                String mysuggestion = suggestion.getText().toString();

                if (co.getText().toString().isEmpty() || referringDoctor.getText().toString().isEmpty() || myrating == null) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        String[] to = {"ajayhindole15@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL, to);
//                        intent.putExtra(Intent.EXTRA_SUBJECT, mysub);
                        intent.putExtra(Intent.EXTRA_TEXT, "Rating: " + myrating + "\n" +
                                "C/O? :" + myco + "\n" +
                                "Name of Referring doctor: " + myreferringDoctor + "\n" +
                                "Referred to whom: " + myreferredtowhom + "\n" +
                                "Review: " + myreview + "\n" +
                                "Suggestion: " + mysuggestion + "\n");

                        startActivity(Intent.createChooser(intent, "Send Email"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });


        return view;
    }
}