package com.example.megha.drytickets.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.megha.drytickets.Adapters.ListViewAdapter_nowonsale;
import com.example.megha.drytickets.JSONfunction;
import com.example.megha.drytickets.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by megha on 3/20/2015.
 */
public class Now_on_sale_firstpage extends Activity
{
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter_nowonsale adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;

    final String url="http://bishasha.com/json/now_on_sale_events.php";
    //static String EVENT_NAME = "event_name";
    public static String ID = "id";
    public static String DESCRIPTION = "desc";
    public static String DATE = "date";
    public static String TIME = "time";
    public static String PRICE = "price";
    public static String VENUE = "venue";
    public static String IMAGE_PATH = "image_path";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.custom_list);
        // Execute DownloadJSON AsyncTask
        new DownloadJSON().execute();
    }
    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Now_on_sale_firstpage.this);
            mProgressDialog.setTitle("PLEASE wait for a while");
            mProgressDialog.setMessage("Loading...events page");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();

           /* try
            {
                ServiceHandler serviceHandler = new ServiceHandler();
                String jsonString= makeServiceCall(url, 1);
                jsonobject=new JSONObject(jsonString);
            }
            catch(Exception e)
            {
                e.getStackTrace();
            }
*/

            // Retrieve JSON Objects from the given URL address
           jsonobject = JSONfunction.getJSONfromURL("http://bishasha.com/json/now_on_sale_events.php");
            try
            {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("events");
                for (int i = 0; i < jsonarray.length(); i++)
                {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    map.put("image_path", jsonobject.getString("image_path"));
                    map.put("id", jsonobject.getString("id"));
                    map.put("desc", jsonobject.getString("desc"));
                    map.put("date", jsonobject.getString("date"));
                    map.put("time", jsonobject.getString("time"));
                    map.put("price", jsonobject.getString("price"));
                    map.put("venue", jsonobject.getString("venue"));
                    // Set the JSON Objects into the array
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
            listview = (ListView) findViewById(R.id.custom_list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter_nowonsale(Now_on_sale_firstpage.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}