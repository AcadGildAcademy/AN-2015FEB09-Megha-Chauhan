package com.example.megha.todo_miniproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by megha on 2/26/2015.
 */
public class mainjavafile extends ActionBarActivity
{
    ListView list;
    String[] data={};
    FragmentManager fm=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_layout);

        list= (ListView) findViewById(R.id.list_of_items);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,data);

        list.setAdapter(aa);
        getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menufile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.plus)
        {

            final Context context= this;
            final EditText text1,text2;
            final DatePicker dp;
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.dialog_elements, null);
            dp=(DatePicker)view.findViewById(R.id.datePicker);
            dp.setCalendarViewShown(false);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(view);
            text1=(EditText) view.findViewById(R.id.txt1);
            text2=(EditText) view.findViewById(R.id.txt2);
            alertDialogBuilder
                .setTitle("My TO_DO app")
                .setPositiveButton("SAVE THE DATA", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler database = new DatabaseHandler(context);
                        //Toast.makeText(getApplicationContext(), "Megha", Toast.LENGTH_LONG).show();

                        Log.d("Inserting", "Data into DATABASE");
                        String selected_date = String.valueOf(dp.getDayOfMonth() +"-"+ dp.getMonth() +"-"+ dp.getYear());
                       // Toast.makeText(getApplicationContext(),selected_date,Toast.LENGTH_LONG).show();
                        database.addData(new ListClass(101, text1.getText().toString(), text2.getText().toString(), selected_date, 0));

                        Log.d("Reading", "Data from DATABASE");
                        List<ListClass> list = database.getFullList();

                        for (ListClass listClass : list)
                        {
                            String log = "Id: " + listClass.getId() + " Title: " + listClass.getTitle() + " Description: " + listClass.getDescription() + "Date: " + listClass.getDate() + "Status: " +listClass.getStatus();
                            Log.d("List of data:", log);
                        }

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

            AlertDialog ad=alertDialogBuilder.create();
            ad.show();
        }
        return super.onOptionsItemSelected(item);

    }
}
