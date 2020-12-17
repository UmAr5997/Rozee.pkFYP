package com.example.rozeepk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.rozeepk.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    public RequestQueue mQueue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.data);
        Button buttonParse = findViewById(R.id.getdata);
        Button btnsign=findViewById(R.id.btnsignup);
        Button btnlogin=findViewById(R.id.btnLogin);
        mQueue = Volley.newRequestQueue(this);

        sharedPreferences= getSharedPreferences("MySharedPref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String s1 = sharedPreferences.getString("username", "");
        String role = sharedPreferences.getString("role", "");
        if(!s1.isEmpty() && role.equals("employer"))
        {
            Intent intent=new Intent(MainActivity.this, PostJobActivity.class);
            startActivity(intent);
        }
        else if(!s1.isEmpty())
        {
            Intent intent=new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        String url="http://192.168.0.105:4700/api/demo/allrecord";

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // jsonParse();
                new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String str=new String(responseBody);
                            mTextViewResult.setText(str);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        mTextViewResult.setText(error.toString());
                    }
                });

            }
        });

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        //set icon
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        //set title
                        .setTitle("Select")
                        //set message
                        .setMessage("SignUp as a Job-Seeker or Employeer!!")
                        //set positive button
                        .setPositiveButton("Job Seeker", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                Intent intent=new Intent(MainActivity.this, SignupActivity.class);
                                intent.putExtra("role","job seeker");
                                startActivity(intent);
                            }
                        })
                        //set negative button
                        .setNegativeButton("Employer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
                                //Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(MainActivity.this, EmployerSignUpActivity.class);
                                intent.putExtra("role","employer");
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        //set icon
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        //set title
                        .setTitle("Select")
                        //set message
                        .setMessage("Login as a Job-Seeker or Employer!!")
                        //set positive button
                        .setPositiveButton("Job-Seeker", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                Intent intent=new Intent(MainActivity.this, LoginActivity1.class);
                                intent.putExtra("role","job seeker");
                                startActivity(intent);
                            }
                        })
                        //set negative button
                        .setNegativeButton("Employer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked
                                //Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(MainActivity.this, LoginActivity1.class);
                                intent.putExtra("role","employer");
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }

    private void jsonParse() {

        String url="http://192.168.0.101:4700/api/demo/allrecord";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String firstName = employee.getString("fullname");
                                String email = employee.getString("email");
                                String pass = employee.getString("password");
                                mTextViewResult.append(firstName + ", " + email + ", " + pass + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextViewResult.setText(error.toString());
            }
        });
        mQueue.add(request);
    }
}