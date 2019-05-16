package com.Firebase.myapplication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class choose_gender extends Fragment {



    public choose_gender() {
        // Required empty public constructor
    }

    RadioGroup radioGroup;
TextView username;
    ProgressDialog pd ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_choose_gender, container, false);

        radioGroup = myview.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch (checkedId) {
                    case R.id.female:
                        Fragment female = new female_bodyType();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.add(R.id.frame, female); // give your fragment container id in first parameter
                        transaction.addToBackStack("female");  // if written, this transaction will be added to backstack
                        transaction.commit();
                        radioGroup.clearCheck();

                        home.gender = "Female";

                        break;

                    case R.id.male:
                        Fragment male = new male_bodyType();
                        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                        transaction1.add(R.id.frame, male); // give your fragment container id in first parameter
                        transaction1.addToBackStack("male");  // if written, this transaction will be added to backstack
                        transaction1.commit();
                        radioGroup.clearCheck();

                        home.gender = "Male";

                        break;
                }
            }
        });
        return myview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pd = new ProgressDialog(getContext());

        pd.setTitle("Please wait..");

        pd.setMessage("Loading");

        pd.show();

    username = view.findViewById(R.id.username);
    get_data_firebase();

    }
    private void get_data_firebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".", "");


        DatabaseReference reference = firebaseDatabase.getReference("My profile").child(email);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()) {

                    SignUp_data_model datamodel = dataSnapshot.getValue(SignUp_data_model.class);

                    if (datamodel.username != null) {
                        username.setText(datamodel.username);

                    }

                }
pd.hide();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        radioGroup.clearCheck();
    }

    @Override
    public void onResume() {
        super.onResume();

        radioGroup.clearCheck();
    }
}
