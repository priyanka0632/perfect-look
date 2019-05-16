package com.Firebase.myapplication;


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
public class male_skin_tone extends Fragment {


    public male_skin_tone() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_male_skin_tone, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button male_fair = view.findViewById(R.id.btn_male_fair);
        male_fair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment male_skinTone = new male_height();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frame, male_skinTone); // give your fragment container id in first parameter
                transaction.addToBackStack("male");// if written, this transaction will be added to backstack
                home.skin_tone ="Fair_tone";
                transaction.commit();


            }
        });

        Button male_dusky = view.findViewById(R.id.btn_male_dusky);
        male_dusky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment male_skinTone = new male_height();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frame, male_skinTone);
                transaction.addToBackStack("male");
                home.skin_tone ="Dusky_tone";
                transaction.commit();



            }
        });

        Button male_dark = view.findViewById(R.id.btn_male_dark);
        male_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment male_skinTone = new male_height();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frame, male_skinTone);
                transaction.addToBackStack("male");
                home.skin_tone ="Dark_tone";
                transaction.commit();


            }
        });


    }
}
