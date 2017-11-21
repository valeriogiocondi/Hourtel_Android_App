package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Favorites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.hourtel.user.hourtel.MainActivity.MainActivity.Thread_GetHotelsList;
import com.hourtel.user.hourtel.R;

public class FavoritesHotelsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_hotels_list);


        // Go back to main activity
        ImageView iconButton = (ImageView) findViewById(R.id.back_to_main_activity);

        iconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
            }
        });

        // AsyncTask
        Thread_GetFavoritesHotelsList restaurant = new Thread_GetFavoritesHotelsList(this);
        restaurant.execute();

    }
}
