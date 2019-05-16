package com.Firebase.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class
change_password extends AppCompatActivity {
    TextView show_pswrd;
    TextView show_pswrd1;
    TextView show_pswrd2;
    EditText existingPwd;
     EditText newPwd;
    EditText confirmPwd;

    FirebaseUser user;
  String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        existingPwd =  findViewById(R.id.existingpwd);
        newPwd = findViewById(R.id.newpwd);
        confirmPwd = findViewById(R.id.confirmpwd);

        show_pswrd = findViewById(R.id.show_pswrd);
        show_pswrd.setVisibility(View.GONE);
        existingPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(existingPwd.getText().length()>0){
                    show_pswrd.setVisibility(View.VISIBLE);
                }
                else {
                    show_pswrd.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        show_pswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pswrd.getText()=="SHOW"){
                    show_pswrd.setText("HIDE");
                    existingPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    existingPwd.setSelection(existingPwd
                            .length());
                    Typeface type = Typeface.createFromAsset(getAssets(),"font/monospacebold.ttf");
                    existingPwd.setTypeface(type);
                }
                else{
                    show_pswrd.setText("SHOW");
                    existingPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    existingPwd.setSelection(existingPwd.length());
                }
            }
        });


        show_pswrd1 = findViewById(R.id.show_pswrd1);
        show_pswrd1.setVisibility(View.GONE);
        newPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(newPwd.getText().length()>0){
                    show_pswrd1.setVisibility(View.VISIBLE);
                }
                else {
                    show_pswrd1.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        show_pswrd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pswrd1.getText()=="SHOW"){
                    show_pswrd1.setText("HIDE");
                    newPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    newPwd.setSelection(newPwd
                            .length());
                    Typeface type2 = Typeface.createFromAsset(getAssets(),"font/monospacebold.ttf");
                    newPwd.setTypeface(type2);
                }
                else{
                    show_pswrd1.setText("SHOW");
                    newPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPwd.setSelection(newPwd.length());
                }
            }
        });


        show_pswrd2 = findViewById(R.id.show_pswrd2);
        show_pswrd2.setVisibility(View.GONE);
        confirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(confirmPwd.getText().length()>0){
                    show_pswrd2.setVisibility(View.VISIBLE);
                }
                else {
                    show_pswrd2.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        show_pswrd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pswrd2.getText()=="SHOW"){
                    show_pswrd2.setText("HIDE");
                    confirmPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    confirmPwd.setSelection(confirmPwd
                            .length());
                    Typeface type1 = Typeface.createFromAsset(getAssets(),"font/monospacebold.ttf");
                    confirmPwd.setTypeface(type1);
                }
                else{
                    show_pswrd2.setText("SHOW");
                    confirmPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confirmPwd.setSelection(confirmPwd.length());
                }
            }
        });


    }

    public void back_arrow(View v)
    {

         finish();
    }

     public void change_password(View v)
     {

         existingPwd =  findViewById(R.id.existingpwd);
         newPwd = findViewById(R.id.newpwd);
         confirmPwd = findViewById(R.id.confirmpwd);





         String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
         String existingPWD = existingPwd.getText().toString();
         String newPWD = newPwd.getText().toString();
         String confirmPWd = confirmPwd.getText().toString();
         String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{10,15}$";

         if(!newPWD.matches(pattern)|| newPwd.length() <10 ){

             Toast.makeText(change_password.this, "Password must contain atleast one alphabet , digit, special character and length muct be 10 characters\"", Toast.LENGTH_SHORT).show();
             return;
         }





         if (newPWD.equals(existingPWD))
         {

             Toast.makeText( change_password.this ," new password should not match existing password", Toast.LENGTH_SHORT).show();
             return;

         }

         if (newPWD.equals(confirmPWd))
         {

             user = FirebaseAuth.getInstance().getCurrentUser();
             AuthCredential credential = (AuthCredential) EmailAuthProvider.getCredential(email, existingPwd.getText().toString());
             user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if (task.isSuccessful())
                     {
                         user.updatePassword(newPwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {

                                 if(task.isSuccessful())
                                 {
                                     Toast.makeText(change_password.this, "PASSWORD UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                     Intent i = new Intent(change_password.this, settings.class);
                                     startActivity(i);
                                 }
                                 else {

                                     Toast.makeText(change_password.this, "ERROR", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });


                     }


                     else {
                         Log.d (TAG, "Error auth failed");
                         Toast.makeText(change_password.this, "ERROR", Toast.LENGTH_SHORT).show();
                     }

                 }
             });

         }

        else {

            Toast.makeText(change_password.this, "Old password is incorrect",Toast.LENGTH_SHORT).show();
         }


     }



}


