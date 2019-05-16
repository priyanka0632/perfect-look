package com.Firebase.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class male_height extends Fragment {


    public male_height() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_male_height, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button below = view.findViewById(R.id.btn_male_below);
        below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
home.height = "Below_5'5";
                Fragment results = new ResultsActivity();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, results);
                transaction.commit();


            }
        });

        Button above = view.findViewById(R.id.btn_male_above);
        above.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.height = "Above_5'5";

                Fragment results = new ResultsActivity();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, results);
                transaction.commit();
            }
        });

    }

}
