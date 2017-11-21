package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.hourtel.user.hourtel.MainActivity.HotelProfile.Thread_GetHotel;
import com.hourtel.user.hourtel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegistrationPassword extends AppCompatActivity {

//    String first_name;
//    String last_name;
//    String email;
    Bundle previousActivityData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_password);


        final EditText passwordEditText;

        previousActivityData = getIntent().getExtras();
        if (previousActivityData == null)
            return;

        // Go back to previous activity
        ImageView iconButton = (ImageView) findViewById(R.id.back_to_main_activity);
        iconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
            }
        });

        Switch showPasswordSwitch = (Switch) findViewById(R.id.show_password);
        passwordEditText = (EditText) findViewById(R.id.password);

        showPasswordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

/*
//                Because each time the orientation changes the view is being recreated.
                String text = password.getText();
                password.setText("");
                password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password.setText(text);
                */
                if (isChecked)
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                else
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        Button createNewAccpuntButton = (Button) findViewById(R.id.create_new_account);
        createNewAccpuntButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                /*
                *
                *   CONTROLLO DUREZZA DELLA PASSWORD, E NON DEVE SUPERARE I 255 CARATTERI!
                *
                * */

                EditText password = (EditText) findViewById(R.id.password);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("first_name", previousActivityData.getString("first_name"));
                    obj.put("last_name", previousActivityData.getString("last_name"));
                    obj.put("email", previousActivityData.getString("email"));
                    obj.put("password", password.getText().toString());

                    // AsyncTask
                    Thread_SendRegistrationToServer thread = new Thread_SendRegistrationToServer(RegistrationPassword.this, obj);
                    thread.execute();

                } catch (JSONException e) {

                    System.out.println(e.getMessage());
                }

            }
        });

    }
}
