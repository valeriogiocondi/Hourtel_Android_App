package com.hourtel.user.hourtel.MainActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by valerio on 06/09/17.
 */

public class Thread_SendErrorReport extends AsyncTask<String, Void, Bitmap> {

    ImageView imageField;

    public Thread_SendErrorReport(ImageView image) {

        imageField = image;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);

        } catch (Exception e) {

            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        imageField.setImageBitmap(bitmap);
    }
}
