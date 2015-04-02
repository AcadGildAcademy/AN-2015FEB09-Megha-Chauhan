package com.example.megha.drytickets;
/**
 * Created by DELL on 21-03-2015.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.megha.drytickets.ImageLoader;
import com.example.megha.drytickets.R;

public class ListViewAdapter_tickets extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter_tickets(Context context,ArrayList<HashMap<String, String>> arraylist)
    {
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
        ImageView image_path;
        TextView type_category;
        TextView price;
        Button button;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.ticket_selection, parent, false);
        // Get the position
        resultp = data.get(position);
        type_category = (TextView) itemView.findViewById(R.id.type_category);
        price = (TextView) itemView.findViewById(R.id.price);
        //button = (Button) itemView.findViewById(R.id.buy_button);

        type_category.setText(resultp.get(Ticket_booking.TYPE_CATEGORY));
        price.setText(resultp.get(Ticket_booking.PRICE));

        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
            /*    resultp = data.get(position);
                Intent intent = new Intent(context, seat_allocation.class);
                intent.putExtra("type_category", resultp.get(Ticket_booking.TYPE_CATEGORY));
                intent.putExtra("price", resultp.get(Ticket_booking.PRICE));
                intent.putExtra("event_id",resultp.get(Ticket_booking.EVENT_ID));
                intent.putExtra("event_name", resultp.get(Ticket_booking.EVENT_NAME));
                context.startActivity(intent);*/
            }
        });
        return itemView;
    }
}