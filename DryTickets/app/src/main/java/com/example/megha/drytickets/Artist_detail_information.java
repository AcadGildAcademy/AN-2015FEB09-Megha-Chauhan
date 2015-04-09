package com.example.megha.drytickets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by megha on 4/8/2015.
 */
public class Artist_detail_information extends Activity
{
    // Declare Variables
    // String event_name;
    String artist_name;
    String artist_photo_path;
    String artist_description;

    ImageLoader imageLoader = new ImageLoader(this);
    HashMap<String, String> resultp = new HashMap<String, String>();
    ArrayList<HashMap<String, String>> data;
    //Button buy_btn;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_page_design);
        artist_name = getIntent().getExtras().getString("artist_name");
        artist_photo_path = getIntent().getExtras().getString("artist_photo_path");
        artist_description = getIntent().getExtras().getString("artist_description");

        TextView name = (TextView) findViewById(R.id.artist_name);
        ImageView photo = (ImageView) findViewById(R.id.artist_image);
        TextView description = (TextView) findViewById(R.id.artist_description);

        // Capture position and set results to the ImageView
        // Passes image URL into ImageLoader.class
        imageLoader.DisplayImage(artist_photo_path, photo);
        name.setText(artist_name);
        description.setText(artist_description);
        final Context context = this;
        /*buy_btn = (Button) findViewById(R.id.buy_button);
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context,Ticket_booking.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",id);
                startActivity(intent);
            }
        });*/
    }
}
