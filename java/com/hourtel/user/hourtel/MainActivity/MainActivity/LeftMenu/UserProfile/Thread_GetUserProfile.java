package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.UserProfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.RegistrationError;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.RegistratonSucceed;
import com.hourtel.user.hourtel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by valerio on 04/09/17.
 */

public class Thread_GetUserProfile extends AsyncTask<Void, Void, String> {

    Context parentContext = null;
    View parentView;
    JSONObject data;

    public Thread_GetUserProfile(Context context, JSONObject obj){

        parentContext = context;
        parentView = ((Activity) context).getWindow().getDecorView();
        data = obj;
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {

            URL url = new URL("http://hourtel.it/api/user/get_user_profile.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            try {

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                StringBuilder result = new StringBuilder();

                result.append(URLEncoder.encode("id", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(data.getString("id").toString(), "UTF-8"));

                writer.write(result.toString());
                writer.flush();
                writer.close();
                os.close();

                connection.connect();

            } catch (JSONException e) {

                System.out.println(e.getMessage());
            }

            int responseCode=connection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            } else
                return "-1";

        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    protected void onPostExecute(String result) {

        EditText firstNameEditText = (EditText) parentView.findViewById(R.id.first_name);
        EditText lastNameEditText = (EditText) parentView.findViewById(R.id.last_name);
        EditText emailEditText = (EditText) parentView.findViewById(R.id.email);
        TextView dateOfBirthTextView = (TextView) parentView.findViewById(R.id.date_of_birth);

        JSONObject obj = null;

        if (result.trim().equals("-1") == false) {

            try {

                obj = new JSONObject(result);

                firstNameEditText.setText(obj.getString("first_name"));
                lastNameEditText.setText(obj.getString("last_name"));
                emailEditText.setText(obj.getString("email"));
                dateOfBirthTextView.setText(obj.getString("date_of_birth"));

            } catch (JSONException e) {

                System.out.println("JSONException: "+e.getMessage());
            }

        } else {

            // REGISTRATION FAIL

            Intent intent = new Intent(parentContext, RegistrationError.class);
            parentContext.startActivity(intent);

        }


    }

}

