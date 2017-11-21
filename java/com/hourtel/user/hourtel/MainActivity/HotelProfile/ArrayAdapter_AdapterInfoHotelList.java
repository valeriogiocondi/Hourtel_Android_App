package com.hourtel.user.hourtel.MainActivity.HotelProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hourtel.user.hourtel.R;

import java.util.ArrayList;

/**
 * Created by valerio on 26/08/17.
 */


public class ArrayAdapter_AdapterInfoHotelList extends ArrayAdapter<String[]> {

    Context parentContext = null;

    public ArrayAdapter_AdapterInfoHotelList(Context context, ArrayList<String[]> List)  {

        super(context, R.layout.list_adapter_hotel_profile_hotel_info, List);
        parentContext = context;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layout = LayoutInflater.from(getContext());
        View customView = layout.inflate(R.layout.list_adapter_hotel_profile_hotel_info, parent, false);

        TextView valueTextView = (TextView) customView.findViewById(R.id.info_value);
        valueTextView.setText(getItem(position)[0]);
        System.out.println(position+" - "+getItem(position)[0]);

        return customView;
    }
}
