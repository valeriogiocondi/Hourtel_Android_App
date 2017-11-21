package com.hourtel.user.hourtel.MainActivity.MainActivity;

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
 * Created by valerio on 23/08/17.
 */

public class ArrayAdapter_HomeHotelsResult extends ArrayAdapter<String[]> {

    Context parentContext = null;

    public ArrayAdapter_HomeHotelsResult(Context context, ArrayList<String[]> List)  {

        super(context, R.layout.activity_main, List);
        parentContext = context;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = LayoutInflater.from(getContext());
        View customView = layout.inflate(R.layout.list_adapter_home_hotels_result_search, parent, false);

        TextView nameTextView = (TextView) customView.findViewById(R.id.restaurant_name);
        TextView addressTextView = (TextView) customView.findViewById(R.id.restaurant_address);

        new Thread_GetImageFromURL((ImageView) customView.findViewById(R.id.image_profile)).execute(getItem(position)[3]);

        nameTextView.setText(getItem(position)[1]);
        addressTextView.setText(getItem(position)[2]);

        customView.setTag(getItem(position)[0]);

        return customView;
    }
}
