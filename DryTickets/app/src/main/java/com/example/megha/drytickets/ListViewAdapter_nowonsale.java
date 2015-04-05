package com.example.megha.drytickets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.now_on_sale_page, parent, false);
        resultp = data.get(position);     // Get the position
        image = (ImageView) itemView.findViewById(R.id.image);
        imageLoader.DisplayImage(resultp.get(Now_on_sale_firstpage.IMAGE_PATH), image);
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                resultp = data.get(position);
                Bundle bundle= new Bundle();
                //Bundle bundle = intent.getExtras();
                bundle.putString("image_path", resultp.get(Now_on_sale_firstpage.IMAGE_PATH));
                bundle.putString("id", resultp.get(Now_on_sale_firstpage.ID));
                bundle.putString("desc", resultp.get(Now_on_sale_firstpage.DESCRIPTION));
                bundle.putString("price", resultp.get(Now_on_sale_firstpage.PRICE));
                bundle.putString("date", resultp.get(Now_on_sale_firstpage.DATE));
                bundle.putString("time", resultp.get(Now_on_sale_firstpage.TIME));
                bundle.putString("venue", resultp.get(Now_on_sale_firstpage.VENUE));
                // Start SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return itemView;
    }
}
