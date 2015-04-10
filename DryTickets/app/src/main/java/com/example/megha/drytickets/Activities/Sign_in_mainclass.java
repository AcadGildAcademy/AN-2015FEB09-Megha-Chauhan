package com.example.megha.drytickets.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
public class Sign_in_mainclass extends Activity {
    EditText user_name, user_password;
    Button signin, cancel;
    CheckBox remember_password;
    TextView forgot_password;

    final String url = "http://bishasha.com/json/whdeal_login.php";
    String TAG_SUCCESS = "success";
    JSONArray jsonArray = null;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Toast.makeText(getApplicationContext(), "entered", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        dataList = new ArrayList<HashMap<String, String>>();
        user_name = (EditText) findViewById(R.id.et_username);
        user_password = (EditText) findViewById(R.id.et_password);
        remember_password=(CheckBox)findViewById(R.id.remember_check);
        loadMySavedPrefrences();
        signin = (Button) findViewById(R.id.signin_btn);
        //cancel = (Button) findViewById(R.id.cancel_btn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username=user_name.getText().toString();
                String password=user_password.getText().toString();
                //final String email = user_name.getText().toString();
                //final String pass = user_password.getText().toString();
                if((username.isEmpty())||(password.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"ENTER THE REQUIRED FIELDS",Toast.LENGTH_LONG).show();
                }
               /* else if (!isValidEmail(username))
                {
                    Toast.makeText(getApplicationContext(),"ENTER email",Toast.LENGTH_LONG).show();
                    user_name.setError("Invalid Email");
                }
                else if (!isValidPassword(password))
                {
                    Toast.makeText(getApplicationContext(),"ENTER password",Toast.LENGTH_LONG).show();
                    user_password.setError("Invalid Password");
                }*/
                else
                {
                    new SubClass().execute();
                    saveMySharedPrefrences("CHECKBOX",remember_password.isChecked());
                    if (remember_password.isChecked())
                    {
                        String str1=user_name.getText().toString();
                        String str2=user_password.getText().toString();
                        saveMySharedPrefrences("USERNAME",str1);
                        saveMySharedPrefrences("PASSWORD",str2);
                    }
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

    private void saveMySharedPrefrences(String Key, boolean values)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed =sp.edit();
        ed.putBoolean(Key,values);
        ed.commit();
    }

    private void saveMySharedPrefrences(String Key, String values)
    {
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed =sp.edit();
        ed.putString(Key,values);
        ed.commit();
    }

    private void loadMySavedPrefrences()
    {
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        String str1=sp.getString("USERNAME", "abc@gmail.com");
        String str2=sp.getString("PASSWORD","def");
        Boolean ck=sp.getBoolean("CHECKBOX",true);
        user_name.setText(str1);
        user_password.setText(str2);
        remember_password.setChecked(ck);
    }

    private class SubClass extends AsyncTask<Void,Integer,Void>
    {
        boolean failure = false;
        @Override protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(Sign_in_mainclass.this);
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
            try
            {
                //Toast.makeText(getApplicationContext(),"helloo",Toast.LENGTH_LONG).show();
                List param = new ArrayList();
                param.add(new BasicNameValuePair("email", username));
                param.add(new BasicNameValuePair("password", password));
                Log.d("request!", "starting");
                ServiceHandler serviceHandler = new ServiceHandler();
                String jsonString=serviceHandler.makeServiceCall(url,1,param);
                JSONObject jsonObject=new JSONObject(jsonString);
                success=jsonObject.getInt(TAG_SUCCESS);
                publishProgress(success);
                Log.d("login!","success");
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
                Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Sign_in_mainclass.this, First_page.class);
                finish();
            }
            else
            {
                Log.d("Login Failure!","login fail");
                Toast.makeText(Sign_in_mainclass.this,"login fail",Toast.LENGTH_LONG).show();
            }
        }
    }
}

