package com.hourtel.user.hourtel.MainActivity.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hourtel.user.hourtel.R;

/**
 * Created by valerio on 23/08/17.
 */

public class Fragment_Home extends Fragment {

    View mainLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainLayout = inflater.inflate(R.layout.fragment_home, container, false);

        // AsyncTask
        Thread_GetHotelsList restaurant = new Thread_GetHotelsList(getContext(), (ListView) mainLayout.findViewById(R.id.hotels_list));
        restaurant.execute();

        return mainLayout;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
