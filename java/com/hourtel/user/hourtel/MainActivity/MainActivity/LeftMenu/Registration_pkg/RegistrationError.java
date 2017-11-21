package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.UserProfile.Thread_SetUserProfile;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.UserProfile.UserProfileSettings;
import com.hourtel.user.hourtel.MainActivity.MainActivity.MainActivity;
import com.hourtel.user.hourtel.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_error);

//        TextView iconButton = (TextView) findViewById(R.id.back_to_registration);
//        iconButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(RegistrationError.this, Registration.class);
//                RegistrationError.this.startActivity(intent);
//            }
//        });


        Button saveInfo = (Button) findViewById(R.id.back_to_home);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                JSONObject obj = new JSONObject();
//                EditText firstNameTextView = (EditText) findViewById(R.id.first_name);
//                EditText lastNameTextView = (EditText) findViewById(R.id.last_name);
//                EditText emailTextView = (EditText) findViewById(R.id.email);
//
//                try {
//
//                    obj.put("id", prefs.getString("id","0"));
//                    obj.put("first_name", firstNameTextView.getText());
//                    obj.put("last_name", lastNameTextView.getText());
//                    obj.put("email", emailTextView.getText());

//                     AsyncTask
//                    Thread_SetUserProfile thread = new Thread_SetUserProfile(RegistrationError.this, obj);
//                    thread.execute();
//
//                } catch (JSONException e) {
//
//                    System.out.println(e.getMessage());
//                }

                Intent intent = new Intent(RegistrationError.this, MainActivity.class);
                RegistrationError.this.startActivity(intent);

            }
        });
    }
}
