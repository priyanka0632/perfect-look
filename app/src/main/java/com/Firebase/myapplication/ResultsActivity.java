package com.Firebase.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.Firebase.myapplication.data_models.body_type;
import com.Firebase.myapplication.data_models.height;
import com.Firebase.myapplication.data_models.skin_tone;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ResultsActivity extends Fragment {


    private body_type body_data;
    private skin_tone skin_data ;
    private height height_data;
    TextView what_not_to_wear;
    TextView what_to_wear;
    TextView colors_that_suits_you;
    TextView according_to_height;
    TextView BodyType;
    TextView SkinTone;
    TextView Height;
    TextView shopping_link;


    ProgressDialog pd ;

    ViewFlipper body_type_flipper;
    ViewFlipper skin_tone_flipper;
    ViewFlipper height_flipper;

    Button change_preference ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_results, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        change_preference = view.findViewById(R.id.change_preferences);

        change_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor sharedPreference = getContext().getSharedPreferences("results", MODE_PRIVATE).edit();

                sharedPreference.clear();

                sharedPreference.commit();

                startActivity(new Intent(getActivity(), home.class));

                getActivity().finishAffinity();
            }
        });

        what_not_to_wear = view.findViewById(R.id.what_not_to_wear);

        what_to_wear = view.findViewById(R.id.what_to_wear);

        body_type_flipper = view.findViewById(R.id.body_type_flipper);
        colors_that_suits_you = view.findViewById(R.id.colors_that_suits_you);

        skin_tone_flipper = view.findViewById(R.id.skin_tone_flipper);
        according_to_height = view.findViewById(R.id.according_to_height);
        height_flipper = view.findViewById(R.id.height_flipper);

        BodyType = view.findViewById(R.id.Body_Type);
        SkinTone = view.findViewById(R.id.Skin_Tone);
        Height = view.findViewById(R.id.Height);
        shopping_link = view.findViewById(R.id.link);


        pd = new ProgressDialog(getContext());

        pd.setTitle("Please wait..");

        pd.setMessage("Fetching results");

        pd.show();


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("results", MODE_PRIVATE);

        if (sharedPreferences.getString("gender", "").equals("")) {


            SharedPreferences.Editor shared_preference = getContext().getSharedPreferences("results", MODE_PRIVATE).edit();

            shared_preference.putString("gender", home.gender);

            shared_preference.putString("body_type", home.body_type);

            shared_preference.putString("skin_tone", home.skin_tone);

            shared_preference.putString("height", home.height);


            shared_preference.commit();
        }


        get_results();


        shopping_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (home.gender.equals("Male")) {
                    String url = "http://www.whatwearhow.in/his-blog/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

                if (home.gender.equals("Female")) {

                    String url1 = "http://www.whatwearhow.in/her-blog/";
                    Intent i1 = new Intent(Intent.ACTION_VIEW);
                    i1.setData(Uri.parse(url1));
                    startActivity(i1);

                }


            }
        });
        if (home.gender.equals("Male")) {

            shopping_link.setText("http://www.whatwearhow.in/his-blog/");

        }

        if (home.gender.equals("Female")) {
            shopping_link.setText("http://www.whatwearhow.in/her-blog/");


        }
    }




    private void get_results() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("results", MODE_PRIVATE);

        String gender = sharedPreferences.getString("gender", "");

        System.out.println("***************** saved gender is **** " + gender);

        String body_type = sharedPreferences.getString("body_type", "");

        String skin_tone = sharedPreferences.getString("skin_tone", "");

        String height = sharedPreferences.getString("height", "");




        BodyType.setText(body_type.replace("_"," "));
        SkinTone.setText(skin_tone.replace("_"," "));
        Height.setText(height.replace("_"," "));


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("Body_Type").child(gender).child(body_type);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                body_data = dataSnapshot.getValue(body_type.class);
                what_not_to_wear.setText(body_data.What_not_to_wear);
                what_to_wear.setText(body_data.What_to_wear);

                String[] img = body_data.Images.split(",");

                for (String image : img) {
                    ImageView imageView = new ImageView(getContext());
                    FrameLayout.LayoutParams params =
                            new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(30, 30, 30, 30);
                    params.gravity = Gravity.CENTER;
                    imageView.setLayoutParams(params);

                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Glide.with(ResultsActivity.this).load(image).into(imageView);

                    body_type_flipper.addView(imageView);
                }

                body_type_flipper.setAutoStart(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        System.out.println("********************** skin tone ********** "+skin_tone);
        DatabaseReference reference1 = database.getReference("Skin_tone").child( gender ).child( skin_tone );

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("********************** skin data ********** "+dataSnapshot.toString());
                skin_data = dataSnapshot.getValue(skin_tone.class);
                colors_that_suits_you.setText(skin_data.Colors_that_suits_you);
                String[] img1 = skin_data.Images.split(",");

                for (String image : img1) {
                    ImageView imageView1 = new ImageView(getContext());
                    FrameLayout.LayoutParams params =
                            new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(30, 30, 30, 30);
                    params.gravity = Gravity.CENTER;
                    imageView1.setLayoutParams(params);

                    imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Glide.with(ResultsActivity.this).load(image).into(imageView1);

                    skin_tone_flipper.addView(imageView1);
                }

                skin_tone_flipper.setAutoStart(true);

            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        System.out.println("********************** height ********** "+height);

        DatabaseReference reference2 = database.getReference("Height").child( gender ).child( height );

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("********************** height data ********** "+dataSnapshot.toString());


                height_data = dataSnapshot.getValue(height.class);
                according_to_height.setText(height_data.According_to_your_height);
                String[] img2 = height_data.Images.split(",");

                for (String image : img2) {
                    ImageView imageView2 = new ImageView(getContext());
                    FrameLayout.LayoutParams params =
                            new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(30, 30, 30, 30);
                    params.gravity = Gravity.CENTER;
                    imageView2.setLayoutParams(params);

                    imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Glide.with(ResultsActivity.this).load(image).into(imageView2);

                    height_flipper.addView(imageView2);
                }

                height_flipper.setAutoStart(true);
                pd.hide();

            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        DatabaseReference reference3 = database.getReference(home.gender);

        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Shopping_link").exists())
                {

                    shopping_link.setText(dataSnapshot.child("Shopping_link").getValue().toString());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    }



