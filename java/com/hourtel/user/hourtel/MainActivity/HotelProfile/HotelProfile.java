package com.hourtel.user.hourtel.MainActivity.HotelProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.hourtel.user.hourtel.R;

public class HotelProfile extends AppCompatActivity {

    String hotelID;
    ListView infosHotel;
    ListView infosRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_profile);

        // Go back to previous activity
        ImageView iconButton = (ImageView) findViewById(R.id.back_to_main_activity);

        iconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
            }
        });

        // GET hotel_id

        Bundle restaurantData = getIntent().getExtras();
        if (restaurantData == null)
            return;

        hotelID = restaurantData.getString("hotel_id");


        // AsyncTask
        Thread_GetHotel restaurant = new Thread_GetHotel(this, hotelID);
        restaurant.execute();

        // PhotoGallery

        ImageView imageProfile = (ImageView) findViewById(R.id.image_profile);

        imageProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(HotelProfile.this, HotelPhotoGallery.class);
                HotelProfile.this.startActivity(intent);
            }
        });
    }
}

