package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Login_pkg;

/**
 * Created by valerio on 31/08/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

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

//import com.hourtel.user.hourtel.MainActivity.ArrayListAdapterBookingsHistory;
//import com.hourtel.user.hourtel.MainActivity.MainActivity;
//import com.hourtel.user.hourtel.RestaurantProfile.RestaurantProfile;

import com.hourtel.user.hourtel.MainActivity.HotelProfile.HotelProfile;
import com.hourtel.user.hourtel.MainActivity.MainActivity.MainActivity;
import com.hourtel.user.hourtel.R;

import javax.net.ssl.HttpsURLConnection;

public class Thread_GetLogin extends AsyncTask<Void, Void, String> {

    Context parentContext;
    View parentView;
    TextView errorMessageTextview;
    JSONObject data;
    String previousActivityName;

    public Thread_GetLogin(Context context, JSONObject obj, String name) {

        parentContext = context;
        parentView = ((Activity) context).getWindow().getDecorView();
        previousActivityName = name;
        data = obj;
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {

            URL url = new URL("http://hourtel.it/api/user/get_login.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            try {

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                StringBuilder result = new StringBuilder();

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

            System.out.println(responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
//                System.out.println("prova");

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

        JSONObject obj = null;
        errorMessageTextview = (TextView) parentView.findViewById(R.id.error_message);

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

                if (previousActivityName.trim().equals("MainActivity")) {

                    Intent intent = new Intent(parentContext, MainActivity.class);
                    parentContext.startActivity(intent);

                } else if (previousActivityName.trim().equals("HotelProfile")) {

                    Intent intent = new Intent(parentContext, HotelProfile.class);
                    parentContext.startActivity(intent);
                }

            } catch (JSONException e) {

                System.out.println("JSONException: "+e.getMessage());
            }

            errorMessageTextview.setVisibility(View.GONE);

        } else {

            errorMessageTextview.setVisibility(View.VISIBLE);
        }


    }

}
