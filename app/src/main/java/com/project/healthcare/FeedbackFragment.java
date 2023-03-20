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
    EditText subject, descrition;
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
        subject = view.findViewById(R.id.subject);
        descrition = view.findViewById(R.id.description);
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

                mysub = subject.getText().toString();
                mydes = descrition.getText().toString();

                if (subject.getText().toString().isEmpty() || descrition.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        String[] to = {"ajayhindole15@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL, to);
                        intent.putExtra(Intent.EXTRA_SUBJECT, mysub);
                        intent.putExtra(Intent.EXTRA_TEXT, "Rating:" + myrating + "\n" + mydes);

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