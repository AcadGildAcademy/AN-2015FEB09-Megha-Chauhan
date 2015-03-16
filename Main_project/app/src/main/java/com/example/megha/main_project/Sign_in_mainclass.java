package com.example.megha.main_project;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by megha on 3/11/2015.
 */
public class Sign_in_mainclass extends Activity
{
    EditText user_name,user_password;
    Button signin,cancel;
    CheckBox remember_password;
    TextView forgot_password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final String url="http://bishasha.com/json/whdeal_login.php";
        String TAG_SUCCESS="success";
        final String TAG_PRODUCT="product";
        String TAG_ID="id";
        final String TAG_NAME="name";
        final String TAG_PASSWORD="password";
        final String TAG_EMAIL="email";
        final JSONArray[] product = new JSONArray[1];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        user_name= (EditText) findViewById(R.id.et_username);
        user_password= (EditText) findViewById(R.id.et_password);
        signin= (Button) findViewById(R.id.signin_btn);
        cancel= (Button) findViewById(R.id.cancel_btn);
        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                ServiceHandler serviceHandler=new ServiceHandler();
                String jsonString=serviceHandler.makeServiceCall(url,ServiceHandler.GET);
                Toast.makeText(getApplicationContext(),jsonString,Toast.LENGTH_LONG).show();
                if(jsonString!=null)
                {
                    Toast.makeText(getApplicationContext(),"user signed in",Toast.LENGTH_LONG).show();
                    Log.d("Response:",jsonString);
                    try
                    {
                        JSONObject jsonObject=new JSONObject(jsonString);
                        product[0] = jsonObject.getJSONArray(TAG_PRODUCT);
                        for(int i=0;i< product[0].length();i++)
                        {
                            JSONObject jsonObject1= product[0].getJSONObject(i);
                            String email=jsonObject1.getString(TAG_EMAIL);
                            String password=jsonObject1.getString(TAG_PASSWORD);

                            if(email.equals(user_name.getText()))
                            {
                                if(password.equals(user_password.getText()))
                                {
                                    Toast.makeText(getApplicationContext(),"user signed in",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"enter correct password",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        e.getStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"no data found",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
