package com.example.rozeepk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rozeepk.Adapter.PostAdapter;
import com.example.rozeepk.Class.AllFields;
import com.example.rozeepk.Class.Post;
import com.example.rozeepk.Model.RetrofitClient;
import com.example.rozeepk.R;

import java.io.IOException;
import java.util.List;

public class DashBordActivity extends AppCompatActivity {

    PostAdapter adapter;
    RecyclerView recyclerView;
    EditText autoCompleteSearch;
    private Toolbar toolbar;
    String[] arr;
//            = { "Paries,France", "PA,United States","Parana,Brazil",
//            "Padua,Italy", "Pasadena,CA,United States"};
    List<AllFields> arrskills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        autoCompleteSearch=findViewById(R.id.edtsearch);

        getAllSkills();


        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getPosts();

        autoCompleteSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }

            private void performSearch() {
                autoCompleteSearch.clearFocus();
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(autoCompleteSearch.getWindowToken(), 0);
                getAllSkills();
            }
        });
        ImageView imgback=findViewById(R.id.back);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBordActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getAllSkills() {

        String title=autoCompleteSearch.getText().toString();
        if(!title.isEmpty()) {
            Call<List<Post>> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Allskills(title);
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.isSuccessful()) {
                        List<Post> res = response.body();
                        adapter = new PostAdapter(DashBordActivity.this, res);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        if(res.size()==0){
                            searchbycity();
                        }
                    } else {
                        try {
                            Toast.makeText(DashBordActivity.this, "Failed"+response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
        }
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
                    adapter=new PostAdapter(DashBordActivity.this,res);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(DashBordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
    public void searchbycity(){
        String title=autoCompleteSearch.getText().toString();
        Call<List<Post>> call = RetrofitClient
                .getInstance()
                .getApi()
                .searchbycity(title);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> res = response.body();
                    adapter = new PostAdapter(DashBordActivity.this, res);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    try {
                        Toast.makeText(DashBordActivity.this, "Failed"+response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}