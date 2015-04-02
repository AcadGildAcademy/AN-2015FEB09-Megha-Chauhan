package com.example.megha.drytickets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by megha on 3/30/2015.
 */
public class SingleItemView extends Activity
{
    // Declare Variables
   // String event_name;
    String description;
    String date;
    String time;
    String image;
    String price;
    String venue;
    ImageLoader imageLoader = new ImageLoader(this);

    Button buy_btn;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.single_item_view);
         Intent i = getIntent();
         image = i.getStringExtra("image_path");
         //event_name = i.getStringExtra("event_name");
         description = i.getStringExtra("desc");
         date = i.getStringExtra("date");
         time = i.getStringExtra("time");
         price = i.getStringExtra("price");
         venue = i.getStringExtra("venue");

         ImageView event_image = (ImageView) findViewById(R.id.image);
         //TextView e_name = (TextView) findViewById(R.id.event_name);
         TextView event_description = (TextView) findViewById(R.id.description);
         TextView event_date = (TextView) findViewById(R.id.date);
         TextView event_time = (TextView) findViewById(R.id.time);
         TextView event_price = (TextView) findViewById(R.id.price);
         TextView event_venue = (TextView) findViewById(R.id.venue);
         // Capture position and set results to the ImageView
         // Passes image URL into ImageLoader.class
         imageLoader.DisplayImage(image, event_image);
        // e_name.setText(event_name);
         event_description.setText(description);
         event_date.setText(date);
         event_time.setText(time);
         event_price.setText(price);
         event_venue.setText(venue);
    }
}