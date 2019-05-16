package com.Firebase.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView show_pswrd;
    EditText password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        password_et = findViewById(R.id.password_et);

        show_pswrd = findViewById(R.id.show_pswrd);
        show_pswrd.setVisibility(View.GONE);

        password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(password_et.getText().length()>0){
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
                    password_et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password_et.setSelection(password_et
                            .length());
                    Typeface type = Typeface.createFromAsset(getAssets(),"font/monospacebold.ttf");
                    password_et.setTypeface(type);
                }
                else{
                    show_pswrd.setText("SHOW");
                    password_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password_et.setSelection(password_et.length());
                }
            }
        });





        if( mAuth.getCurrentUser() != null)
       {
           Intent I = new Intent(Login.this, home.class);
           startActivity(I);
       }

    }


    public void sign_up(View v) {

        Intent i = new Intent(Login.this, Sign_up.class);
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(i);


    }

    public void login(View v) {
        EditText email_et = findViewById(R.id.email_et);
         password_et = findViewById(R.id.password_et);




    String email = email_et.getText().toString();
        String password = password_et.getText().toString();

        if (email.isEmpty() && password.isEmpty()) {

            Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
            return;


        }



        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "LOGGED IN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);

                    Intent I = new Intent(Login.this, home.class);
                    startActivity(I);



                } else {

                    Toast.makeText(Login.this, "INVALID CREDENTIALS", Toast.LENGTH_SHORT).show();
                }
            }
        };


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, listener);

    }


    public void forgot_password(View view) {

        Intent i = new Intent(Login.this,forgot_password.class);
        startActivity(i);
    }
}

