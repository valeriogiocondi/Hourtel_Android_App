package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.HotelProfile.HotelProfile;
import com.hourtel.user.hourtel.MainActivity.MainActivity.MainActivity;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by valerio on 03/09/17.
 */

public class Thread_SendRegistrationToServer extends AsyncTask<Void, Void, String> {

    Context parentContext = null;
    JSONObject data;

    public Thread_SendRegistrationToServer(Context context, JSONObject obj){

        parentContext = context;
        data = obj;
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {

            URL url = new URL("http://hourtel.it/api/user/registration.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            try {

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                StringBuilder result = new StringBuilder();

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
                result.append("&");
                result.append(URLEncoder.encode("password", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(data.getString("password").toString(), "UTF-8"));

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

//            InputStreamReader streamConnection = new InputStreamReader(connection.getInputStream());
//            BufferedReader reader = new BufferedReader(streamConnection);
//
//            StringBuffer json = new StringBuffer(1024);
//            String tmp;
//
//            while((tmp=reader.readLine())!=null)
//                json.append(tmp).append("\n");
//            reader.close();
//
//            return json.toString();

        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    protected void onPostExecute(String result) {

        System.out.println(result);

        JSONObject obj = null;

        if (result.trim().equals("-1") == false) {

            try {

                obj = new JSONObject(result);

                SharedPreferences prefs = parentContext.getSharedPreferences("login", parentContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("logged", "yes");
                editor.putString("id", (obj.getString("id")));
                editor.putString("first_name", (obj.getString("first_name")));
                editor.putString("last_name", (obj.getString("last_name")));
                editor.putString("email", (obj.getString("email")));
                editor.commit();

                Intent intent = new Intent(parentContext, RegistratonSucceed.class);
                parentContext.startActivity(intent);

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

