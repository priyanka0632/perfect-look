package com.Firebase.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



    }
         public void back_arrow(View v)
         {

             finish();
         }

        public void change_password (View v)
        {

            Intent i = new Intent(settings.this, change_password.class);
            startActivity(i);
        }

        public void About_us (View v)

        {


            Intent i = new Intent(settings.this, about_us.class);
            startActivity(i);

        }

        public void help (View v)
        {

            Intent i = new Intent(settings.this, help_settings.class);
            startActivity(i);


        }

        public void share(View v)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out this styling app at: https:/");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }


    public void logout(View view) {





        FirebaseAuth.getInstance().signOut();


        AlertDialog.Builder builder = new AlertDialog.Builder(settings.this);
        builder.setMessage("Are you sure you want to Log Out?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        settings.this.finishAffinity();
                        Intent i = new Intent(settings.this, Login.class);
                        SharedPreferences.Editor sharedPreference = getSharedPreferences("results" , MODE_PRIVATE).edit();

                        sharedPreference.clear();

                        sharedPreference.commit();

                        startActivity(i);
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




