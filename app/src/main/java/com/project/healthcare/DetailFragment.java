package com.project.healthcare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailFragment extends Fragment {


    ImageView imageView;
    TextView title, description;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        imageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        Bundle bundle = getArguments();
        String mimageurl = bundle.getString("imageurl");
        String mtitle = bundle.getString("title");
        String mdescription = bundle.getString("description");

        Glide.with(getContext()).load(mimageurl).into(imageView);
        title.setText(mtitle);
        description.setText(Html.fromHtml(mdescription));


        return view;
    }
}