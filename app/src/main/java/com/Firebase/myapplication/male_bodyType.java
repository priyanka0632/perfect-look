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
public class male_bodyType extends Fragment {


    public male_bodyType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_male_type, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button male_rectangle = view.findViewById(R.id.btn_male_rectangle);
        male_rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment male = new male_skin_tone();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frame, male); // give your fragment container id in first parameter
                transaction.addToBackStack("male");  // if written, this transaction will be added to backstack
                home.body_type ="Rectangle_shape";
                transaction.commit();



            }
        });

            Button male_oval = view.findViewById(R.id.btn_male_oval);
            male_oval.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment male = new male_skin_tone();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.add(R.id.frame, male);
                    transaction.addToBackStack("male");
                    home.body_type ="Oval_shape";
                    transaction.commit();



                }
            });

              Button male_inverted = view.findViewById(R.id.btn_male_inverted);

              male_inverted.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Fragment male = new male_skin_tone();
                      FragmentTransaction transaction = getFragmentManager().beginTransaction();
                      transaction.add(R.id.frame, male);
                      transaction.addToBackStack("male");
                      home.body_type ="Triangle_shape";
                      transaction.commit();
                  }
              });


              Button male_trapezoid = view.findViewById(R.id.btn_male_trapezoid);
              male_trapezoid.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      Fragment male = new male_skin_tone();
                      FragmentTransaction transaction = getFragmentManager().beginTransaction();
                      transaction.add(R.id.frame,male);
                      transaction.addToBackStack("male");
                      home.body_type ="Trapezoid_shape";
                      transaction.commit();

                  }
              });


    }
}
