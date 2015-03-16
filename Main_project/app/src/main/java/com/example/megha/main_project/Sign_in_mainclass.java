package com.example.megha.main_project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by megha on 3/11/2015.
 */
public class Sign_in_mainclass extends Activity {
    EditText user_name, user_password;
    Button signin, cancel;
    CheckBox remember_password;
    TextView forgot_password;

    final String url = "http://bishasha.com/json/whdeal_login.php";
    String TAG_SUCCESS = "success";
    String TAG_FAIL = "fail";
    ServiceHandler serviceHandler = new ServiceHandler();
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        dataList = new ArrayList<HashMap<String, String>>();
        user_name = (EditText) findViewById(R.id.et_username);
        user_password = (EditText) findViewById(R.id.et_password);
        signin = (Button) findViewById(R.id.signin_btn);
        cancel = (Button) findViewById(R.id.cancel_btn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username=user_name.getText().toString();
                String password=user_password.getText().toString();
                new SubClass().execute();
            }
        });
    }
    private class SubClass extends AsyncTask<Void,Void,Void>
    {
        boolean failure = false;
        @Override protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sign_in_mainclass.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show(); }

    @Override
    protected Void doInBackground(Void... params)
    {
        int success;

        try
        {
                Toast.makeText(getApplicationContext(),"LOGIN FAILED",Toast.LENGTH_LONG).show();
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                String username=user_name.getText().toString();
                String password=user_password.getText().toString();
                param.add(new BasicNameValuePair("email", username));
                param.add(new BasicNameValuePair("password", password));
                Log.d("request!", "starting");
                String jsonString=serviceHandler.makeServiceCall(url,1,param);
                JSONObject jsonObject=new JSONObject(jsonString);
                success=jsonObject.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    Log.d("login!","success");
                }
                else
                {
                    Log.d("login!", "fail");
                }
        }
        catch(Exception e)
        {
                e.getStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void s)
    {
            super.onPostExecute(s);
    }
    }
}