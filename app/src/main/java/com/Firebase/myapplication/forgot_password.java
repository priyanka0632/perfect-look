package com.Firebase.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    TextView email;
    String email_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email=(EditText)findViewById(R.id.enter_email);


    }

    public void submit(View v){
        email_s=  email.getText().toString();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email_s)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( forgot_password.this ,"Email Sent!!!Check Email!!" , Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else {
                            Toast.makeText(forgot_password.this ,"error" ,
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        Intent i = new Intent(forgot_password.this, Login.class);
        startActivity(i);

    }


}
