package com.example.rozeepk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rozeepk.Adapter.PostAdapter;
import com.example.rozeepk.Model.RetrofitClient;
import com.example.rozeepk.Class.Post;
import com.example.rozeepk.Fragment.NewsFeed;
import com.example.rozeepk.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    PostAdapter adapter;
    RecyclerView recyclerView;
    private ActionBarDrawerToggle drawerToggle;
    View mHeaderView;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    String s1,shrdemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar =  findViewById(R.id.toolbar);

        Intent intent = getIntent();
        sh= getSharedPreferences("MySharedPref", MODE_PRIVATE);
        editor = sh.edit();

        s1 = sh.getString("username", "");
        shrdemail=sh.getString("email","");


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mHeaderView =  navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NewsFeed()).commit();
            navigationView.setCheckedItem(R.id.nav_feeds);
        }
        String image = getIntent().getStringExtra("img");

        TextView txtusername=mHeaderView.findViewById(R.id.username);
        TextView txtuseremail=mHeaderView.findViewById(R.id.useremail);
        ImageView userimage=mHeaderView.findViewById(R.id.userimg);
            txtusername.setText(s1);
            txtuseremail.setText(shrdemail);
            //userimage.setImageBitmap(image);
        Button btnsearch=findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,DashBordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = RetrofitClient
                .getInstance()
                .getApi()
                .Allposts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful())
                {
                    List<Post> res = response.body();
                    adapter=new PostAdapter(HomeActivity.this,res);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_feeds:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewsFeed()).commit();
                break;
            case R.id.nav_profile:
                    Intent intent=new Intent(HomeActivity.this, EditProfileActivity.class);
                    startActivity(intent);
                break;
            case R.id.nav_matchjobs:
                Toast.makeText(this, "Match Job Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jobmatchsetting:
                Toast.makeText(this, "Job Match Setting Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Cvmanager:
                Toast.makeText(this, "CV Manager Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_myjobs:
                Toast.makeText(this, "My Job Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_qrscanner:
                Toast.makeText(this, "QR Scanner Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_changepass:
                Toast.makeText(this, "Change Password Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_helpfeedback:
                Toast.makeText(this, "Help And Feedback Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_msignout:
                editor.clear();
                editor.apply();
                moveactivity();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if(!s1.isEmpty())
            finishAffinity();
        super.onBackPressed();
    }

    public void moveactivity()
    {
        Intent intent=new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
    }
}