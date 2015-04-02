package com.example.megha.drytickets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by megha on 3/30/2015.
 */
public class ListViewAdapter_nowonsale extends BaseAdapter
{
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public ListViewAdapter_nowonsale(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // Declare Variables
        ImageView image;
        TextView title;
        TextView description;
        TextView event_date;
        TextView event_time;
        TextView venue;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.now_on_sale_page, parent, false);
        resultp = data.get(position);     // Get the position
                // Locate the ImageView in listview_item.xml
        image = (ImageView) itemView.findViewById(R.id.image);
                // Capture position and set results to the ImageView
                // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(Now_on_sale_firstpage.IMAGE_PATH), image);
                // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtra("image_path", resultp.get(Now_on_sale_firstpage.IMAGE_PATH));
                //intent.putExtra("event_name", resultp.get(Now_on_sale_firstpage.EVENT_NAME));
                intent.putExtra("desc", resultp.get(Now_on_sale_firstpage.DESCRIPTION));
                intent.putExtra("price", resultp.get(Now_on_sale_firstpage.PRICE));
                intent.putExtra("date", resultp.get(Now_on_sale_firstpage.DATE));
                intent.putExtra("time", resultp.get(Now_on_sale_firstpage.TIME));
                intent.putExtra("venue", resultp.get(Now_on_sale_firstpage.VENUE));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
