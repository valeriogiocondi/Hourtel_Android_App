package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.UserProfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.RegistrationError;
import com.hourtel.user.hourtel.MainActivity.MainActivity.MainActivity;
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

public class Thread_SetUserProfile extends AsyncTask<Void, Void, String> {

    Context parentContext = null;
    View parentView;
    JSONObject data;

    public Thread_SetUserProfile(Context context, JSONObject obj){

        parentContext = context;
        parentView = ((Activity) context).getWindow().getDecorView();
        data = obj;
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {

            URL url = new URL("http://hourtel.it/api/user/set_user_profile.php");
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
                result.append("&");
                result.append(URLEncoder.encode("first_name", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(data.getString("first_name").toString(), "UTF-8"));
                result.append("&");
                result.append(URLEncoder.encode("last_name", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(data.getString("last_name").toString(), "UTF-8"));
                result.append("&");
                result.append(URLEncoder.encode("email", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(data.getString("email").toString(), "UTF-8"));

//                if (data.getString("password").toString().length() > 0) {
//
//                    result.append("&");
//                    result.append(URLEncoder.encode("password", "UTF-8"));
//                    result.append("=");
//                    result.append(URLEncoder.encode(data.getString("password").toString(), "UTF-8"));
//                }

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

        System.out.println(result);

        if (result.trim().equals("0") == true) {

            // INFOS SAVED CORRECTLY
            Intent intent = new Intent(parentContext, MainActivity.class);
            parentContext.startActivity(intent);

        } else {

            // SAVED FAIL

            Intent intent = new Intent(parentContext, RegistrationError.class);
            parentContext.startActivity(intent);

        }


    }

}

