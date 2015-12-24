package com.example.ameen.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Ameen on 23-Dec-2015.
 */

public class hotelsAdapter extends ArrayAdapter<Hotel> {
    public hotelsAdapter
            (Context context, ArrayList<Hotel> Hotels) {
        super(context, 0, Hotels);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {

            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Hotel hotel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_hotels, parent, false);
        }
        // Lookup view for data population
        TextView hotelName = (TextView) convertView.findViewById(R.id.textHotelName);
        TextView distance = (TextView) convertView.findViewById(R.id.textDistance);
        TextView avg = (TextView) convertView.findViewById(R.id.textAvg);
        TextView pay = (TextView) convertView.findViewById(R.id.textPay);
        RatingBar rate = (RatingBar) convertView.findViewById(R.id.ratingBar);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);

        // Populate the data into the template view using the data object
        hotelName.setText(hotel.getHotel_name());

        distance.setText(hotel.getDistance_from_city_center());
        if (!hotel.isPre_paid())
            pay.setText("PAY AT HOTEL");

        avg.setText("" + hotel.getAvgRate());
        rate.setRating(hotel.getStars());
        try {

            img.setImageBitmap(getBitmapFromURL(hotel.getCoverImage()));

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Return the completed view to render on screen
        return convertView;
    }
}

