package com.example.megha.drytickets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by megha on 3/24/2015.
 */
public class EventAdapter extends ArrayAdapter<Events>
{
    ArrayList<Events> eventList;
    LayoutInflater layoutInflater;
    int Resource;

    public EventAdapter(Context context, int resource, ArrayList<Events> objects)
    {
        super(context, resource, objects);
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource=resource;
        eventList=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v=convertView;
        ViewHolder viewHolder;
        if(v==null)
        {
            viewHolder = new ViewHolder();
            v=layoutInflater.inflate(Resource,null);
            viewHolder.imageView=(ImageView)v.findViewById(R.id.image);
            //viewHolder.details=(TextView)v.findViewById(R.id.text);
            v.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)v.getTag();
        }
        viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
        new DownloadImageTask(viewHolder.imageView).execute(eventList.get(position).getImage());
        return v;
    }
    static class ViewHolder {
        public ImageView imageView;
        //public TextView details;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        ImageView bmImage;
        public DownloadImageTask(ImageView image) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls)
        {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try
            {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            }
            catch (Exception e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}