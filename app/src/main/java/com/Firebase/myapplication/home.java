package com.Firebase.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.Firebase.myapplication.brand_detection.Camera2BasicFragment;
import com.Firebase.myapplication.brand_detection.CameraActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mvc.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;

import static com.Firebase.myapplication.My_profile.img;

public class home extends AppCompatActivity {

    public static String gender = "";
    public static String body_type = "";
    public static String skin_tone = "";
    public static String height ="";
    public static String imageUrl = "";

    static ProgressDialog pd;

    public static Boolean LOCATION_FOUND  = false;

    public static String LAST_FOUND = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        pd = new ProgressDialog(this);
        pd.setMessage("Loading");

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);


        SharedPreferences sharedPreferences = getSharedPreferences("results" , MODE_PRIVATE);




    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    SharedPreferences sharedPreferences = getSharedPreferences("results" , MODE_PRIVATE);

                    if(!sharedPreferences.getString("gender" , "").equals(""))
                    {
                        Fragment results = new ResultsActivity();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, results);

                        transaction.commit();
                    }
                    else {
                        FragmentManager fm = getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();

                        choose_gender gender_fragment = new choose_gender();
                        ft.replace(R.id.frame, gender_fragment);

                        ft.commit();
                    }


                    return true;

                case R.id.navigation_scan:

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame, Camera2BasicFragment.newInstance())
                            .commit();

                   //startActivity(new Intent(home.this , CameraActivity.class));
                    return true;
                case R.id.navigation_myProfile:
                    FragmentManager fm2 = getSupportFragmentManager();

                    FragmentTransaction ft2 = fm2.beginTransaction();

                    My_profile myProfile_fragment = new My_profile();
                    ft2.replace(R.id.frame, myProfile_fragment);

                    ft2.commit();


                    return true;

            }

            return false;


        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {
            Bitmap bitmap = ImagePicker.getImageFromResult(home.this, requestCode, resultCode, data);

            img.setImageBitmap(bitmap);

            uploadFile(bitmap);
        }

    }

    private void uploadFile(Bitmap bitmap) {

        pd.show();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference mountainImagesRef = storageRef.child("images/" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if (taskSnapshot.getMetadata() != null) {
                    if (taskSnapshot.getMetadata().getReference() != null) {
                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                pd.hide();
                                 imageUrl = uri.toString();
                                //createNewPost(imageUrl);
                            }
                        });
                    }
                }
            }});

    }

    @Override
    protected void onResume() {
        super.onResume();

        LOCATION_FOUND = false;


    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        home.this.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
        }



