package com.example.rozeepk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rozeepk.Class.Post;
import com.example.rozeepk.Model.RetrofitClient;
import com.example.rozeepk.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PostJobActivity extends AppCompatActivity {
    TextView tvpublish,tvapply;
    EditText edtjobtitle,edtexp,edtindustry,edtjobtype,edtdepartment,edteducation,edtcareerlevel,
    edtjobdescription;
    Button btnaddpost;
    Spinner spinnercity,spinnergender;
    ImageView imgback,imgshare;
    final Calendar myCalendar = Calendar.getInstance();
    String spincity,spingender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        tvpublish=findViewById(R.id.publishdate);
        tvapply=findViewById(R.id.applydate);

        edtjobtitle=findViewById(R.id.edtjobtitle);
        edtexp=findViewById(R.id.edtexp);
        edtindustry=findViewById(R.id.edtindustry);
        edtjobtype=findViewById(R.id.edtjobtype);
        edtdepartment=findViewById(R.id.edtdepartment);
        edteducation=findViewById(R.id.edteducation);
        edtcareerlevel=findViewById(R.id.edtcareerlevel);
        edtjobdescription=findViewById(R.id.edtjobdescription);

        spinnercity=findViewById(R.id.spinnercityname);
        spinnergender=findViewById(R.id.spingender);

        imgback=findViewById(R.id.back);
        imgshare=findViewById(R.id.imgshare);


        btnaddpost=findViewById(R.id.addpost);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercity.setAdapter(adapter);

        ArrayAdapter<CharSequence> genadapter = ArrayAdapter.createFromResource(this,R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergender.setAdapter(genadapter);


        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spincity=(String) spinnercity.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spincity=(String) spinnercity.getSelectedItem();
            }
        });

        spinnergender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spingender=(String) spinnergender.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spingender=(String) spinnergender.getSelectedItem();
            }
        });
        btnaddpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postdata();

            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        tvapply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PostJobActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void postdata() {
        String jobtitle=edtjobtitle.getText().toString();
        String exp=edtexp.getText().toString();
        String industry=edtindustry.getText().toString();
        String jobtype=edtjobtype.getText().toString();
        String departmen=edtdepartment.getText().toString();
        String education=edteducation.getText().toString();
        String careerlevel=edtcareerlevel.getText().toString();
        String jobdesc=edtjobdescription.getText().toString();
        String postdate=tvpublish.getText().toString();
        String applydate=tvapply.getText().toString();

        if(jobtitle!=null || jobdesc!=null || jobtype!=null) {
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .employerjobpost(jobtitle, jobdesc, exp, spincity, industry, jobtype, departmen, education, careerlevel, spingender, postdate, applydate);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.isSuccessful()) {
                            String res = response.body().string();
                            Toast.makeText(PostJobActivity.this, "Job SuccessFully Posted ", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(PostJobActivity.this, DashBordActivity.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(PostJobActivity.this, "Sign Up Failed..." + response.errorBody(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(PostJobActivity.this, "Failed  " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(this, "Fill All Data first....!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvapply.setText(sdf.format(myCalendar.getTime()));
    }
}