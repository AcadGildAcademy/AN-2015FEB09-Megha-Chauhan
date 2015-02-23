package com.example.megha.hide_show;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by megha on 2/11/2015.
 */
public class mainpage extends Activity
{
    Button hide_btn,show_btn;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        hide_btn=(Button)findViewById(R.id.h_btn);
        image=(ImageView) findViewById(R.id.img);
        hide_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(hide_btn.getText().equals("HIDE"))
                {
                    image.setVisibility(View.GONE);
                    hide_btn.setText("SEEK");
                }
                else if(hide_btn.getText().equals("SEEK"))
                {
                    image.setVisibility(View.VISIBLE);
                    hide_btn.setText("HIDE");
                }
            }
        });

    }
}
