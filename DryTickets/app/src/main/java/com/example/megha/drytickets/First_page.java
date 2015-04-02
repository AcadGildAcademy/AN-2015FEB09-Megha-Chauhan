package com.example.megha.drytickets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by megha on 3/20/2015.
 */
public class First_page extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_file_layout);
        getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_file,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.add_user)
        {
            Intent intent = new Intent(this, Sign_in_mainclass.class);
            startActivity(intent);
        }
        else if(id==R.id.addnew_user)
        {
            Intent intent = new Intent(this, Sign_up_mainclass.class);
            startActivity(intent);
        }
        else if(id==R.id.now_on_sale)
        {
            Intent intent=new Intent(this, Now_on_sale_firstpage.class);
            startActivity(intent);
        }
        else if(id==R.id.ticket_booking)
        {
            Intent intent=new Intent(this, Ticket_booking.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
