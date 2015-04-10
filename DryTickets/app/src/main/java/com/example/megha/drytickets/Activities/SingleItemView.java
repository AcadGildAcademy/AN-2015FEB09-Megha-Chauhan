package com.example.megha.drytickets.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.megha.drytickets.Utils.ImageLoader;
import com.example.megha.drytickets.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by megha on 3/30/2015.
 */
public class SingleItemView extends Activity
{
    // Declare Variables
   // String event_name;
    String description;
    String id;
    String date;
    String time;
    String image;
    String price;
    String venue;
    ImageLoader imageLoader = new ImageLoader(this);
    HashMap<String, String> resultp = new HashMap<String, String>();
    ArrayList<HashMap<String, String>> data;
    Button buy_btn;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.single_item_view);
         //Intent i = getIntent();
         image = getIntent().getExtras().getString("image_path");
         //event_name = i.getStringExtra("event_name");
         id = getIntent().getExtras().getString("id");
         description = getIntent().getExtras().getString("desc");
         date = getIntent().getExtras().getString("date");
         time = getIntent().getExtras().getString("time");
         price = getIntent().getExtras().getString("price");
         venue = getIntent().getExtras().getString("venue");

        Toast.makeText(getApplication(),id,Toast.LENGTH_LONG).show();

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
        final Context context = this;
         buy_btn = (Button) findViewById(R.id.buy_button);
         buy_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 Intent intent = new Intent(context,Ticket_booking.class);
                 Bundle bundle=new Bundle();
                 bundle.putString("id",id);
                 startActivity(intent);
             }
         });
    }
}