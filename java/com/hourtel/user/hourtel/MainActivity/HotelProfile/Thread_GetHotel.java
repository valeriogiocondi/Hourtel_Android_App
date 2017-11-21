package com.hourtel.user.hourtel.MainActivity.HotelProfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.Thread_GetImageFromURL;
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
 * Created by valerio on 24/08/17.
 */

public class Thread_GetHotel extends AsyncTask<Void, Void, String> {

    Context parentContext = null;
    View parentView = null;
    String hotelID;
    private LinearLayout infosHotelListView;
    private LinearLayout infosRoomListView;
    private ImageView staticMapImageView;
    JSONObject obj = null;

    public Thread_GetHotel(Context context, String hotel_id){

        parentContext = context;
        parentView = ((Activity)parentContext).getWindow().getDecorView();
        hotelID = hotel_id;
    }

    @Override
    protected String doInBackground(Void... urls) {

        try {

            URL url = new URL("http://hourtel.it/api/get_hotel.php?id="+hotelID);
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

        staticMapImageView = (ImageView) parentView.findViewById(R.id.static_map);
        infosHotelListView = (LinearLayout) parentView.findViewById(R.id.layout_infos_hotel);
        infosRoomListView = (LinearLayout) parentView.findViewById(R.id.layout_infos_room);

        TextView title;
        TextView name;
        TextView address;
        ConstraintLayout mainLayout;
//        ImageView staticMapImageView = (ImageView) parentView.findViewById(R.id.static_map);

        try {

            obj = new JSONObject(result);

            new Thread_GetImageFromURL((ImageView) parentView.findViewById(R.id.image_profile)).execute(obj.getString("profile_picture"));
//            mainLayout = (ConstraintLayout) parentView.findViewById(R.id.menu_layout);
            title = (TextView) parentView.findViewById(R.id.title);
            name = (TextView) parentView.findViewById(R.id.restaurant_name);
            address = (TextView) parentView.findViewById(R.id.address);
            TextView description = (TextView) parentView.findViewById(R.id.description);
            TextView checkIn = (TextView) parentView.findViewById(R.id.check_in);
            TextView checkOut = (TextView) parentView.findViewById(R.id.check_out);

//            title.setText(obj.getString("name"));
            name.setText(obj.getString("name"));
            address.setText(obj.getString("address"));

            description.setText(Html.fromHtml((obj.getString("description"))));

//            checkIn.setText(obj.getString("check_in"));
//            checkOut.setText(obj.getString("check_out"));
//

            JSONArray itemInfosHotel;
            JSONArray itemInfosRoom;
            ArrayList<String[]> list = new ArrayList<String[]>();

            try {

                itemInfosHotel = new JSONArray(obj.getString("infos_hotel"));

                for (int i=0, n=itemInfosHotel.length(); i<n; i++) {

                    JSONArray tempJSONElement = new JSONArray(itemInfosHotel.getString(i));

                    LinearLayout layoutItemInfoHotel = new LinearLayout(parentContext);
                    layoutItemInfoHotel.setOrientation(LinearLayout.HORIZONTAL);
                    layoutItemInfoHotel.setPadding(0, 20, 0, 20);

                    ImageView imageTemp = new ImageView(parentContext);

                    if (tempJSONElement.getString(0).equals("parking"))
                        imageTemp.setImageResource(R.drawable.icon_car_20x20);
                    else if (tempJSONElement.getString(0).equals("pets"))
                        imageTemp.setImageResource(R.drawable.icon_paw_20x20);
                    else if (tempJSONElement.getString(0).equals("wifi"))
                        imageTemp.setImageResource(R.drawable.icon_wifi_24x24);
                    else
                        imageTemp.setImageResource(R.drawable.icon_check_24x24);

                    imageTemp.setPadding(0, 15, 50, 10);
                    layoutItemInfoHotel.addView(imageTemp);

                    TextView tempTextView = new TextView(parentContext);

                    tempTextView.setText(tempJSONElement.getString(1));
                    tempTextView.setTextSize(23);
                    layoutItemInfoHotel.addView(tempTextView);

                    infosHotelListView.addView(layoutItemInfoHotel);

                }

                itemInfosRoom = new JSONArray(obj.getString("infos_room"));

                for (int i=0, n=itemInfosRoom.length(); i<n; i++) {

                    LinearLayout layoutItemInfoRoom = new LinearLayout(parentContext);
                    layoutItemInfoRoom.setOrientation(LinearLayout.HORIZONTAL);
                    layoutItemInfoRoom.setPadding(0, 15, 0, 15);

                    ImageView imageTemp = new ImageView(parentContext);
                    imageTemp.setImageResource(R.drawable.icon_check_24x24);
                    imageTemp.setPadding(0, 15, 50, 10);
                    layoutItemInfoRoom.addView(imageTemp);

                    TextView tempTextView = new TextView(parentContext);
                    JSONArray tempJSONElement = new JSONArray(itemInfosRoom.getString(i));

                    tempTextView.setText(tempJSONElement.getString(1));
                    tempTextView.setTextSize(23);
                    layoutItemInfoRoom.addView(tempTextView);

                    infosRoomListView.addView(layoutItemInfoRoom);

                }


            } catch (JSONException e) {

                System.out.println("JSONException: "+e.getMessage());
            }


            String staticMapURL = "http://maps.google.com/maps/api/staticmap?center="+obj.getString("lat")+","+obj.getString("lng")+"&zoom=19&size="+staticMapImageView.getWidth()+"x"+staticMapImageView.getHeight()+"&sensor=false";
            new Thread_GetImageFromURL(staticMapImageView).execute(staticMapURL);

            staticMapImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(parentContext, MapsActivity.class);

                    try {

                        intent.putExtra("lat", obj.getString("lat"));
                        intent.putExtra("lng", obj.getString("lng"));
                        intent.putExtra("title", obj.getString("name"));

                    } catch (JSONException e) {

                        System.out.println("JSONException: "+e.getMessage());
                    }

                    parentContext.startActivity(intent);
                }
            });

//
//            WebView webView = (WebView) parentView.findViewById(R.id.description);
//            webView.loadData(String.format(htmlText, obj.getString("description")), "text/html", "utf-8");

//            String staticMapURL = "http://maps.google.com/maps/api/staticmap?center="+obj.getString("lat")+","+obj.getString("lng")+"&zoom=19&size="+staticMapImageView.getWidth()+"x"+staticMapImageView.getHeight()+"&sensor=false";
//            new Thread_GetImageFromURL(staticMapImageView).execute(staticMapURL);
//
//            System.out.println(staticMapURL);


        } catch (JSONException e) {

            System.out.println("JSONException: "+e.getMessage());
        }

    }

}



