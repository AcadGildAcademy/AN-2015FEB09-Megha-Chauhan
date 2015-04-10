package com.example.megha.drytickets.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.megha.drytickets.R;
import com.example.megha.drytickets.ServiceHandler;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by megha on 3/20/2015.
 */

public class Sign_up_mainclass extends Activity
{
    EditText user_name, user_password, name;
    Button signup, cancel;
    TextView forgot_password;

    final String url = "http://bishasha.com/json/whdeal_signup.php";
    String TAG_SUCCESS = "success";
    JSONArray jsonArray = null;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Toast.makeText(getApplicationContext(), "entered", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        dataList = new ArrayList<HashMap<String, String>>();
        user_name = (EditText) findViewById(R.id.et_username);
        user_password = (EditText) findViewById(R.id.et_password);
        name = (EditText) findViewById(R.id.et_name);
        signup = (Button) findViewById(R.id.signup_btn);
        //cancel = (Button) findViewById(R.id.cancel_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username=user_name.getText().toString();
                String password=user_password.getText().toString();
                String uname=name.getText().toString();
                if((username.isEmpty())||(password.isEmpty())||(uname.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"ENTER THE REQUIRED FIELDS",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new SubClass().execute();
                }
            }
        });
    }

    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String username)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private class SubClass extends AsyncTask<Void,Integer,Void>
    {
        boolean failure = false;
        @Override protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sign_up_mainclass.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            int success;
            String username=user_name.getText().toString();
            String password=user_password.getText().toString();
            String uname=name.getText().toString();
            try
            {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("email", username));
                param.add(new BasicNameValuePair("password", password));
                param.add(new BasicNameValuePair("name", uname));
                Log.d("request!", "starting");
                ServiceHandler serviceHandler = new ServiceHandler();
                String jsonString=serviceHandler.makeServiceCall(url,2,param);
                JSONObject jsonObject=new JSONObject(jsonString);
                success=jsonObject.getInt(TAG_SUCCESS);
                publishProgress(success);
                Log.d("signup!","success");
            }
            catch(Exception e)
            {
                e.getStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void s)
        {
            super.onPostExecute(s);
            pDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            int success = values[0];
            if (success == 1)
            {
                Log.d("Login Successful!", "Login Success");
                Toast.makeText(getApplicationContext(),"signup success",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Sign_up_mainclass.this, First_page.class);
                finish();
            }
            else
            {
                Log.d("Login Failure!","login fail");
                Toast.makeText(Sign_up_mainclass.this,"signup fail",Toast.LENGTH_LONG).show();
            }
        }
    }
}