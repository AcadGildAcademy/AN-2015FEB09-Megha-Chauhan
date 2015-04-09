package com.example.megha.drytickets;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by megha on 4/6/2015.
 */
public class Artist_information extends Activity
{
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter_artists adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    static String ARTIST_ID = "artist_id";
    static String ARTIST_NAME = "artist_name";
    static String ARTIST_DESCRIPTION = "artist_description";
    static String ARTIST_PHOTO_PATH = "artist_photo_path";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.artist_listview);
        // Execute DownloadJSON AsyncTask
        new DownloadJSON().execute();
        getActionBar();
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Artist_information.this);
            mProgressDialog.setTitle("PLEASE wait for a while");
            mProgressDialog.setMessage("Loading...Artist page");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params)
        {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunction.getJSONfromURL("http://bishasha.com/json/artists_information.php");
            try
            {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("artist_information");
                for (int i = 0; i < jsonarray.length(); i++)
                {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("artist_name", jsonobject.getString("artist_name"));
                    map.put("artist_description", jsonobject.getString("artist_description"));
                    map.put("artist_photo_path",jsonobject.getString("artist_photo_path"));
                    //map.put("event_name", jsonobject.getString("event_name"));
                    arraylist.add(map);
                }
            }
            catch (JSONException e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void args)
        {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.artist_list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter_artists(Artist_information.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
