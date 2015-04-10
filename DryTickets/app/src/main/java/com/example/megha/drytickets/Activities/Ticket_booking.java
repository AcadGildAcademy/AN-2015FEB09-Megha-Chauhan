package com.example.megha.drytickets.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.megha.drytickets.JSONfunction;
import com.example.megha.drytickets.Adapters.ListViewAdapter_tickets;
import com.example.megha.drytickets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by megha on 4/2/2015.
 */
public class Ticket_booking extends Activity
{
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter_tickets adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    //Spinner spinner = (Spinner) findViewById(R.id.spinner);
    //String[] seats;
    public static String ID = "id";
    public static String TYPE_CATEGORY = "type_category";
    public static String PRICE = "price";
    public static String EVENT_ID = "event_id";
    public static String EVENT_NAME = "event_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_listview);
        new DownloadJSON().execute();
        getActionBar();

       /* ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,seats);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id)
            {
                /*TextView total= (TextView) findViewById(R.id.total_price);
                TextView price= (TextView) findViewById(R.id.price);
                Spinner spinner= (Spinner) findViewById(R.id.spinner);
                String val1= (String) price.getText();
                String val2= (String) spinner.getSelectedItem();
                String sum=val1+val2;
                total.setText("$"+sum);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });*/
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Ticket_booking.this);
            mProgressDialog.setTitle("PLEASE wait for a while");
            mProgressDialog.setMessage("Loading...Ticket Booking page");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params)
        {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunction.getJSONfromURL("http://bishasha.com/json/ticket_booking.php");
            try
            {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("ticket_booking");
                for (int i = 0; i < jsonarray.length(); i++)
                {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("type_category", jsonobject.getString("type_category"));
                    map.put("price", jsonobject.getString("price"));
                    map.put("event_id", jsonobject.getString("event_id"));
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
            listview = (ListView) findViewById(R.id.ticket_list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter_tickets(Ticket_booking.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}
