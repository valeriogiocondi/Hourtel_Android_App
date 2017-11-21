package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.UserProfile;

import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.Fragment_DatePicker;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.RegistrationPassword;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.Thread_SendRegistrationToServer;
import com.hourtel.user.hourtel.R;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileSettings extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_settings);


        final EditText oldPasswordEditText;
        final EditText newPasswordEditText;
        JSONObject obj = new JSONObject();
        prefs = UserProfileSettings.this.getSharedPreferences("login", MODE_PRIVATE);

        try {

            obj.put("id", prefs.getString("id","0"));

            // AsyncTask
            Thread_GetUserProfile thread = new Thread_GetUserProfile(UserProfileSettings.this, obj);
            thread.execute();

        } catch (JSONException e) {

            System.out.println(e.getMessage());
        }

        // Go back to back activity
        ImageView iconButton = (ImageView) findViewById(R.id.back_to_main_activity);

        iconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
            }
        });

        final TextView dateOfBirthEditTextView = (TextView) findViewById(R.id.date_of_birth);
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

        Switch showPasswordSwitch = (Switch) findViewById(R.id.show_password);
        oldPasswordEditText = (EditText) findViewById(R.id.old_password);
        newPasswordEditText = (EditText) findViewById(R.id.new_password);

        showPasswordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

/*
//                Because each time the orientation changes the view is being recreated.
                String text = password.getText();
                password.setText("");
                password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password.setText(text);
                */

                if (isChecked) {

                    oldPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    oldPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });


        Button saveInfo = (Button) findViewById(R.id.save_info);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                JSONObject obj = new JSONObject();
                EditText firstNameTextView = (EditText) findViewById(R.id.first_name);
                EditText lastNameTextView = (EditText) findViewById(R.id.last_name);
                EditText emailTextView = (EditText) findViewById(R.id.email);

                try {

                    obj.put("id", prefs.getString("id","0"));
                    obj.put("first_name", firstNameTextView.getText());
                    obj.put("last_name", lastNameTextView.getText());
                    obj.put("email", emailTextView.getText());
                    obj.put("date_of_birth", dateOfBirthEditTextView.getText());

//                    if (newPasswordEditText.getText().length() > 0)
//                        obj.put("password", newPasswordEditText.getText());

                    // AsyncTask
                    Thread_SetUserProfile thread = new Thread_SetUserProfile(UserProfileSettings.this, obj);
                    thread.execute();

                } catch (JSONException e) {

                    System.out.println(e.getMessage());
                }


            }
        });
    }
}
