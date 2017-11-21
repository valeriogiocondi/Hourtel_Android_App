package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Favorites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hourtel.user.hourtel.MainActivity.HotelProfile.HotelProfile;
import com.hourtel.user.hourtel.MainActivity.MainActivity.ArrayAdapter_HomeHotelsResult;
import com.hourtel.user.hourtel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by valerio on 01/09/17.
 */

public class Thread_GetFavoritesHotelsList extends AsyncTask<Void, Void, String> {

    private ListView restaurantsListView;
    Context parentContext = null;
    View parentView = null;

    public Thread_GetFavoritesHotelsList(Context context){

        parentContext = context;
        parentView = ((Activity)parentContext).getWindow().getDecorView();
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {

            URL url = new URL("http://hourtel.it/api/get_favorites_hotels_list.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            InputStreamReader streamConnection = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamConnection);

            StringBuffer json = new StringBuffer(1024);
            String tmp;

            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            return json.toString();

        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    protected void onPostExecute(String result) {

        JSONArray obj = null;
        ArrayList<String[]> list = new ArrayList<String[]>();

        try {

            obj = new JSONArray(result);

            for (int i=0, n=obj.length(); i<n; i++) {

                JSONObject jsonobject = obj.getJSONObject(i);
                list.add(new String[]{jsonobject.getString("id"), jsonobject.getString("name"), jsonobject.getString("address"), jsonobject.getString("image"), jsonobject.getString("type")});
            }

            ArrayAdapter_FavoritesHotelsResult restaurantsAdapter = new ArrayAdapter_FavoritesHotelsResult(parentContext, list);
            restaurantsListView = (ListView) parentView.findViewById(R.id.favorites_hotels_hotels_list);
            restaurantsListView.setAdapter(restaurantsAdapter);

        } catch (JSONException e) {

            System.out.println("JSONException: "+e.getMessage());
        }

        restaurantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(parentContext, HotelProfile.class);
                intent.putExtra("hotel_id", view.getTag().toString());
                parentContext.startActivity(intent);
            }
        });

    }

}
