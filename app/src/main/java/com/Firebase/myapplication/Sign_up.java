package com.Firebase.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Sign_up extends AppCompatActivity {

    private Calendar mcalendar;
    private EditText mdob_et;
    private int day,month,year;


    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ProgressBar progressBar;
    TextInputEditText Password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        Password = findViewById(R.id.password);


        mcalendar = Calendar.getInstance();


        mdob_et = findViewById(R.id.dob);
        mdob_et.setOnClickListener(mClickListener);
        day = mcalendar.get(Calendar.DAY_OF_MONTH);
        year = mcalendar.get(Calendar.YEAR);
        month = mcalendar.get(Calendar.MONTH);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


    }


    View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog();
        }
    };
    public void DateDialog(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                mdob_et.setText(dayOfMonth + "/" + (monthOfYear +1)+ "/" + year);
            }};
        DatePickerDialog dpDialog=new DatePickerDialog(Sign_up.this, listener, year, month, day);
        mcalendar.set(1960,0,1);
        dpDialog.getDatePicker().setMinDate(mcalendar.getTimeInMillis());
        mcalendar.set(2001,11,31);
        dpDialog.getDatePicker().setMaxDate(mcalendar.getTimeInMillis());
        dpDialog.show();




    }

    public void signUp(View v)


    {

        TextInputLayout textInput_name  = findViewById(R.id.text_input_name);
        TextInputLayout textInputUsername = findViewById(R.id.text_input_username);
        TextInputLayout textInputDob = findViewById(R.id.text_input_dob);
        TextInputLayout textInputEmailID = findViewById(R.id.text_input_email);




        String nameInput = textInput_name.getEditText().getText().toString().trim();
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();
        String dobInput = textInputDob.getEditText().getText().toString().trim();
        String emailInput = textInputEmailID.getEditText().getText().toString().trim();
        String passwordInput = Password.getText().toString().trim();

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{10,15}$";

        if(nameInput.isEmpty() || usernameInput.isEmpty() || dobInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty() )
        {
            Toast.makeText(Sign_up.this,"Fill all fields",Toast.LENGTH_SHORT).show();
            return;
        }




        if(!passwordInput.matches(pattern)|| Password.length() <10 ){

            Toast.makeText(Sign_up.this, "Password must contain atleast one alphabet , digit, special character and length muct be 10 characters", Toast.LENGTH_SHORT).show();
        }

            OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    progressBar.setVisibility(View.INVISIBLE);
                    Boolean bb = task.isSuccessful();
                    Boolean aa = task.isComplete();

                    if(bb)
                    {
                        add_data();
                        Toast.makeText(Sign_up.this,"successfully registered", Toast.LENGTH_SHORT).show();

                    }

                    else{

                        Toast.makeText(Sign_up.this,"error try again", Toast.LENGTH_SHORT).show();

                    }
                }



            };

            mAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(Sign_up.this,listener);



















    }


    private void add_data()
    {

        EditText name_et = findViewById(R.id.name);
        EditText username_et = findViewById(R.id.username);
        EditText dob_et = findViewById(R.id.dob);
        EditText email_et = findViewById(R.id.email);




        String name = name_et.getText().toString();
        String username = username_et.getText().toString();
        String dob = dob_et.getText().toString();
        String email = email_et.getText().toString();


        DatabaseReference myRef = database.getReference("My profile");

        DatabaseReference ref2 = myRef.child(email.replace(".",""));


        SignUp_data_model dataModel = new SignUp_data_model(name, username, dob , email);

        ref2.setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    finish();
                }

            }
        });


    }

}
