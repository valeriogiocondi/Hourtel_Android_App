package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.Thread_GetImageFromURL;
import com.hourtel.user.hourtel.R;

import java.util.ArrayList;

/**
 * Created by valerio on 01/09/17.
 */

public class ArrayAdapter_FavoritesHotelsResult extends ArrayAdapter<String[]> {

    Context parentContext = null;

    public ArrayAdapter_FavoritesHotelsResult(Context context, ArrayList<String[]> List)  {

        super(context, R.layout.activity_main_left_menu, List);
        parentContext = context;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = LayoutInflater.from(getContext());
        View customView = layout.inflate(R.layout.list_adapter_favorites_hotels_list, parent, false);

        TextView nameTextView = (TextView) customView.findViewById(R.id.hotel_name);
        TextView addressTextView = (TextView) customView.findViewById(R.id.hotel_address);
        TextView typeTextView = (TextView) customView.findViewById(R.id.hotel_type);

        new Thread_GetImageFromURL((ImageView) customView.findViewById(R.id.image_profile)).execute(getItem(position)[3]);

        nameTextView.setText(getItem(position)[1]);
        addressTextView.setText(getItem(position)[2]);
        customView.setTag(getItem(position)[0]);
        typeTextView.setText(getItem(position)[4]);

//        try {
//
//            JSONObject obj = new JSONObject(getItem(position)[4]);
//
//            typeTextView.setText(obj.getString("name"));
//
//        } catch (JSONException e) {
//
//            System.out.println("JSONException: "+e.getMessage());
//        }

        return customView;
    }
}

