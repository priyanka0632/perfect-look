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
public class female_skin_tone extends Fragment {


    public female_skin_tone() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_female_skin_tone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button female_fair = view.findViewById(R.id.btn_female_fair);
          female_fair.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Fragment female_skinTone = new female_height();
                  FragmentTransaction transaction = getFragmentManager().beginTransaction();
                  transaction.add(R.id.frame, female_skinTone); // give your fragment container id in first parameter
                  transaction.addToBackStack("female");  // if written, this transaction will be added to backstack

                  home.skin_tone = "Fair_tone";

                  transaction.commit();


              }
          });

        Button female_dusky = view.findViewById(R.id.btn_female_dusky);
        female_dusky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment female_skinTone = new female_height();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frame, female_skinTone);
                transaction.addToBackStack("female");
                home.skin_tone ="Dusky_tone";


                transaction.commit();



            }
        });

        Button female_dark = view.findViewById(R.id.btn_female_dark);
        female_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment female_skinTone = new female_height();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frame, female_skinTone);
                transaction.addToBackStack("female");
                home.skin_tone ="Dark_tone";
                transaction.commit();


            }
        });


    }


}
