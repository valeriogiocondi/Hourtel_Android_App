package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Login_pkg;

/**
 * Created by valerio on 31/08/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Switch;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.ForgottenPassword_pkg.ForgottenPassword;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.Registration;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.RegistrationPassword;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.Thread_SendRegistrationToServer;
import com.hourtel.user.hourtel.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private String previousActivityName;
    Bundle previousActivityData;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText passwordEditText;

        previousActivityData = getIntent().getExtras();
        if (previousActivityData == null)
            return;

        previousActivityName = previousActivityData.getString("from");

        // Go back to back activity
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

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                username = ((TextView) findViewById(R.id.username)).getText().toString();
                password = ((TextView) findViewById(R.id.password)).getText().toString();

                if (validate(username) && validate(password)) {

                    JSONObject obj = new JSONObject();

                    try {

                        obj.put("email", username);
                        obj.put("password", password);

                        // AsyncTask
                        Thread_GetLogin getLogin = new Thread_GetLogin(Login.this, obj, previousActivityName);
                        getLogin.execute();

                    } catch (JSONException e) {

                        System.out.println(e.getMessage());
                    }

                } else {

                }
            }
        });

        // Go to registration activity
        TextView registrationButton = (TextView) findViewById(R.id.forgotten_password);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, ForgottenPassword.class);
                Login.this.startActivity(intent);
            }
        });

        // Go to forgotten_password activity
        TextView forgottenPasswordButton = (TextView) findViewById(R.id.go_to_registration);
        forgottenPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Registration.class);
                Login.this.startActivity(intent);
            }
        });
    }

    public boolean validate(String str) {

        // email valida

        return true;
    }
}
