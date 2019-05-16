package com.Firebase.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.Transaction;


/**
 * A simple {@link Fragment} subclass.
 */
public class female_bodyType extends Fragment {


    public female_bodyType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_female_type, container, false);



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         Button female_rectangle= view.findViewById(R.id.btn_female_rectangle);
            female_rectangle.setOnClickListener(new View.OnClickListener() {
         @Override


    public void onClick(View v) {


             Fragment female = new female_skin_tone();
             FragmentTransaction transaction = getFragmentManager().beginTransaction();
             transaction.add(R.id.frame, female); // give your fragment container id in first parameter
             transaction.addToBackStack("female");  // if written, this transaction will be added to backstack
             transaction.commit();
             home.body_type = "Rectangle_shape";

          }
     });



            Button female_pear = view.findViewById(R.id.btn_female_pear);
               female_pear.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Fragment female = new female_skin_tone();
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.add(R.id.frame, female); // give your fragment container id in first parameter
                       transaction.addToBackStack("female");// if written, this transaction will be added to backstack

                       home.body_type = "Pear_shape";

                       transaction.commit();

                   }
               });

             Button female_triangle = view.findViewById(R.id.btn_female_triangle);
                     female_triangle.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             Fragment female = new female_skin_tone();
                             FragmentTransaction transaction = getFragmentManager().beginTransaction();
                             transaction.add(R.id.frame, female);
                             transaction.addToBackStack("female");
                             home.body_type ="Triangle_shape";
                             transaction.commit();

                         }
                     });

                Button female_hourglass = view.findViewById(R.id.btn_female_hourglass);

                   female_hourglass.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           Fragment female = new female_skin_tone();
                           FragmentTransaction transaction = getFragmentManager().beginTransaction();
                           transaction.add(R.id.frame, female);
                           transaction.addToBackStack("female");
                           home.body_type = "Hourglass_shape";
                           transaction.commit();

                       }
                   });



                   Button female_oval = view.findViewById(R.id.btn_female_oval);
                   female_oval.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Fragment female = new female_skin_tone();
                           FragmentTransaction transaction = getFragmentManager().beginTransaction();
                           transaction.add(R.id.frame, female);
                           transaction.addToBackStack("female");
                           home.body_type = "Oval_shape";
                           transaction.commit();

                       }
                   });

                   Button female_inverted = view.findViewById(R.id.btn_female_inverted);
                   female_inverted.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Fragment female = new female_skin_tone();
                           FragmentTransaction transaction = getFragmentManager().beginTransaction();
                           transaction.add(R.id.frame, female);
                           transaction.addToBackStack("female");
                           home.body_type = "Inverted_triangle_shape";
                           transaction.commit();


                       }
                   });

    }








}
