package com.example.megha.drytickets;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by megha on 3/30/2015.
 */
public class SingleItemView extends Activity
{
    // Declare Variables
    String title;
    String description;
    String date;
    String time;
    String image;
    String venue;
    ImageLoader imageLoader = new ImageLoader(this);
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.single_item_view);
         Intent i = getIntent();
         image = i.getStringExtra("image_path");
         title = i.getStringExtra("title");
         description = i.getStringExtra("description");
         date = i.getStringExtra("event_date");
         time = i.getStringExtra("event_time");
         venue = i.getStringExtra("venue");

         ImageView event_image = (ImageView) findViewById(R.id.image);
         TextView event_title = (TextView) findViewById(R.id.title);
         TextView event_description = (TextView) findViewById(R.id.description);
         TextView event_date = (TextView) findViewById(R.id.date);
         TextView event_time = (TextView) findViewById(R.id.time);
         TextView event_venue = (TextView) findViewById(R.id.venue);
         // Capture position and set results to the ImageView
         // Passes image URL into ImageLoader.class
         imageLoader.DisplayImage(image, event_image);
         event_title.setText(title);
         event_description.setText(description);
         event_date.setText(date);
         event_time.setText(time);
         event_venue.setText(venue);
    }
}
