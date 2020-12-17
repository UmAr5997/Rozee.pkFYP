package com.example.rozeepk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rozeepk.Model.RetrofitClient;
import com.example.rozeepk.R;
import com.example.rozeepk.Class.User;

public class LoginActivity1 extends AppCompatActivity {

    EditText email,password;
    Button btnlogin;
    CheckBox chkremember;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);

        btnlogin=findViewById(R.id.btnLogin);

        sharedPreferences= getSharedPreferences("MySharedPref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=email.getText().toString();
                String pass=password.getText().toString();

                if(em.isEmpty())
                    email.setError("Please Enter Email");
                if(pass.isEmpty())
                    password.setError("Please Enter Password");

                Call<User> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .loginrecord(em,pass);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            User user=response.body();
                            Toast.makeText(LoginActivity1.this, "SuccessFully Login", Toast.LENGTH_SHORT).show();
                            email.setText("");
                            password.setText("");
                            String role=user.getGender();
                            if(role==null)
                            {
                                Intent intent=new Intent(LoginActivity1.this, PostJobActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(LoginActivity1.this, HomeActivity.class);
                                intent.putExtra("id", user.getId());
                                intent.putExtra("username", user.getFullname());
                                intent.putExtra("email", user.getEmail());
                                intent.putExtra("img", user.getImage());
                                editor.putInt("id", Integer.parseInt((user.getId())));
                                editor.putString("username", user.getFullname());
                                editor.putString("email", user.getEmail());
                                editor.putString("role", user.getRole());
                                editor.commit();
                                startActivity(intent);
                            }
                        }
                        else {
                            if(em.isEmpty() || pass.isEmpty())
                            {
                                Toast.makeText(LoginActivity1.this, "Enter UserName or Password", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(LoginActivity1.this, "Invalid UserName or Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity1.this, "Failed  "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}