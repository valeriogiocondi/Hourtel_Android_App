package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hourtel.user.hourtel.MainActivity.HotelProfile.HotelProfile;
import com.hourtel.user.hourtel.R;

import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * Created by valerio on 31/08/17.
 */

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Context thisContext = getApplicationContext();

        // Go back to previous activity
        ImageView iconButton = (ImageView) findViewById(R.id.back_to_main_activity);
        iconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
            }
        });


        TextView dateOfBirthEditTextView = (TextView) findViewById(R.id.date_of_birth);
        dateOfBirthEditTextView.setInputType(0);
        dateOfBirthEditTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            DialogFragment newFragment = new Fragment_DatePicker();
            newFragment.show(getSupportFragmentManager(), "DatePicker");

           /* Fragment_DatePicker myDateListener = new Fragment_DatePicker() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                }
                };*/
            }
        });

        Button goToPasswordButton = (Button) findViewById(R.id.go_to_password);
        goToPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*
                *
                *       CONTROLLARE I CAMPI - SPECIALMENTE L'EMAIL
                *
                *       1) CONTROLLARE VALIDITÁ DELL'EMAIL
                *
                *       2) CONTROLLARE SE L'EMAIL SIA GIÀ STATA USATA
                *
                * */

                EditText firstName = (EditText) findViewById(R.id.first_name);
                EditText lastName = (EditText) findViewById(R.id.last_name);
                EditText email = (EditText) findViewById(R.id.email);

                Intent intent = new Intent(thisContext, RegistrationPassword.class);
                intent.putExtra("first_name", firstName.getText().toString());
                intent.putExtra("last_name", lastName.getText().toString());
                intent.putExtra("email", email.getText().toString());
                thisContext.startActivity(intent);
            }
        });

    }
}

