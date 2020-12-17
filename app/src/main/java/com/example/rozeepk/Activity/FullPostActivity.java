package com.example.rozeepk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rozeepk.R;

public class FullPostActivity extends AppCompatActivity {

    TextView tvcompanynname,tvjobtitle,tvlocation,tvexperience,tvfield,tvpubdate,tvapplydate
            ,tvindustry,tvjobtype,tvdepartment,tveducation,tvcareerlevel,tvgender;
    ImageView imgcompanyimg,imgshare,imgback;
    Button btnsendcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);

        tvjobtitle=findViewById(R.id.txtjobtitle);
        tvcompanynname=findViewById(R.id.txtcompanyname);
        tvlocation=findViewById(R.id.txtlocation);
        tvexperience=findViewById(R.id.txtexp);
        tvfield=findViewById(R.id.txtfield);
        tvpubdate=findViewById(R.id.publishdate);
        tvapplydate=findViewById(R.id.applydate);
        tvindustry=findViewById(R.id.txtindustry);
        tvjobtype=findViewById(R.id.txtjobtype);
        tveducation=findViewById(R.id.txteducation);
        tvcareerlevel=findViewById(R.id.txtcareerlevel);
        tvgender=findViewById(R.id.txtgender);
        imgcompanyimg=findViewById(R.id.imgcompanyprofile);
        imgshare=findViewById(R.id.imgshare);
        imgback=findViewById(R.id.back);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FullPostActivity.this, DashBordActivity.class);
                startActivity(intent);
            }
        });
        setdata();
    }
    public void setdata()
    {
        String id = getIntent().getStringExtra("id");
        String jobtitle = getIntent().getStringExtra("jobtitle");
        String jobdesc = getIntent().getStringExtra("jobdesc");
        String jobexp = getIntent().getStringExtra("jobexp");
        String city = getIntent().getStringExtra("city");
        String industry = getIntent().getStringExtra("industry");
        String jobtype = getIntent().getStringExtra("jobtype");
        String department = getIntent().getStringExtra("department");
        String eduction = getIntent().getStringExtra("eduction");
        String careerlevel = getIntent().getStringExtra("careerlevel");
        String gender = getIntent().getStringExtra("gender");
        String age = getIntent().getStringExtra("age");
        String postdate = getIntent().getStringExtra("postdate");
        String applydate = getIntent().getStringExtra("applydate");
        String img = getIntent().getStringExtra("img");

        //--------------SetText

        tvjobtitle.setText(jobtitle);
        tvcompanynname.setText(jobdesc);
        tvexperience.setText(jobexp);
        tvlocation.setText(city);
        tvindustry.setText(industry);
        tvjobtype.setText(jobtype);
//        tvdepartment.setText(department);
        tveducation.setText(eduction);
        tvcareerlevel.setText(careerlevel);
        tvgender.setText(gender);
        tvpubdate.setText(postdate);
        tvapplydate.setText(applydate);
        tvgender.setText(gender);



    }
}