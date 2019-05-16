package com.Firebase.myapplication;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mvc.imagepicker.ImagePicker;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class My_profile extends Fragment {



    EditText fullname_et;
    EditText username_et;
    EditText dob_et;
    EditText emailId_et;
    TextView edit_details;
    Button save_details;
    TextView username;
    ImageView edit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static ProgressDialog pd;
    ImageView imagePicker;
     public static ImageView img;
    ImageView imageView;



    public My_profile() {



        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //imagepicker(pixels)

        ImagePicker.setMinQuality(600, 600);





        ImageView fashionImg;

        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        img = rootView.findViewById(R.id.profile_image);

        fashionImg = rootView.findViewById(R.id.settings);
        fashionImg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent mainIntent = new Intent( getActivity(), settings.class);
                startActivity(mainIntent);
            }
        });

        return rootView;





    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //upload image



            pd = new ProgressDialog(getActivity());
            pd.setTitle("Loading");
            pd.setMessage("Please Wait");

            //data_base

        fullname_et = view.findViewById(R.id.fullname_et);
        username_et = view.findViewById(R.id.username_et);
        dob_et = view.findViewById(R.id.dob_et);
        emailId_et = view.findViewById(R.id.emailId_et);
        username = view.findViewById(R.id.username);
        save_details = view.findViewById(R.id.save_details);
        edit = view.findViewById(R.id.edit);
        pd.show();
             get_data_firebase();


           //edit_details(enabled)


         edit_details = view.findViewById(R.id.edit_details);
        edit_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname_et.setEnabled(true);
                username_et.setEnabled(true);
                save_details.setVisibility(View.VISIBLE);
                edit_details.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.INVISIBLE);


               InputMethodManager inputMethodManager =
                      (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
               inputMethodManager.toggleSoftInputFromWindow(fullname_et.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
               fullname_et.requestFocus();



            }
        });

        //fragment my_profile data from firebase

        save_details = view.findViewById(R.id.save_details);
        save_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = fullname_et.getText().toString();
                String username = username_et.getText().toString();
                String email = emailId_et.getText().toString();
                String dob = dob_et.getText().toString();

                DatabaseReference myRef = database.getReference("My profile");

                DatabaseReference ref2 = myRef.child(email.replace(".",""));




                SignUp_data_model dataModel = new SignUp_data_model(name, username, dob, email);
                dataModel.image = home.imageUrl;

                ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(getActivity(),"Saved", Toast.LENGTH_SHORT).show();
                            fullname_et.setEnabled(false);
                            username_et.setEnabled(false);
                            save_details.setVisibility(View.INVISIBLE);
                            edit_details.setVisibility(View.VISIBLE);
                            edit.setVisibility(View.VISIBLE);
                        }



                    }
                });


            }
        });



        imagePicker = view.findViewById(R.id.onPickImage);
        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ImagePicker.pickImage(getActivity(), "Select your image:");

            }
        });




    }
        //store upload image


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    private void get_data_firebase()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace(".","");


        DatabaseReference reference = firebaseDatabase.getReference("My profile").child(email);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pd.hide();

                SignUp_data_model datamodel = dataSnapshot.getValue(SignUp_data_model.class);

                username_et.setText(datamodel.username);
                fullname_et.setText(datamodel.fullname);
                dob_et.setText(datamodel.dob);
                emailId_et.setText(datamodel.email);
                username.setText(datamodel.username);
if (dataSnapshot.child("image").exists()){

    Glide.with(getActivity()).load(dataSnapshot.child("image").getValue().toString()).into(img);

}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }




}

