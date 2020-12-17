package com.example.rozeepk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rozeepk.Model.RetrofitClient;
import com.example.rozeepk.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EmployerSignUpActivity extends AppCompatActivity {

    EditText edtname,edtcompanydes,edtemail,edtpass;
    TextView uploadphoto;
    Spinner spinnercity;
    String strcode,strgender;
    ImageView imageView;
    Button btnsignup;
    String spincity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_sign_up);

        btnsignup=findViewById(R.id.btnsignup);
        uploadphoto=findViewById(R.id.lnkuploadphoto);
        edtname=findViewById(R.id.txtName);
        edtemail=findViewById(R.id.txtEmail);
        edtpass=findViewById(R.id.txtPwd);
        edtcompanydes=findViewById(R.id.edtcompanydesc);
        spinnercity=findViewById(R.id.cityname);
        imageView=findViewById(R.id.uploadimg);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercity.setAdapter(adapter);

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

        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtname.getText().toString();
                String companydes=edtcompanydes.getText().toString();
                String email=edtemail.getText().toString();
                String pass=edtpass.getText().toString();
                String city=spincity;
                String role=getIntent().getStringExtra("role");


                if(name.isEmpty() || companydes.isEmpty() || email.isEmpty() ||pass.isEmpty()  ||pass.isEmpty())
                {
                    Toast.makeText(EmployerSignUpActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .employersignupdata(name,companydes,email,city, pass,role);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                if(response.isSuccessful()) {
                                    String res = response.body().string();
                                    Toast.makeText(EmployerSignUpActivity.this, "SuccessFully Signup ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EmployerSignUpActivity.this, PostJobActivity.class);
                                    startActivity(intent);
                                }else
                                    Toast.makeText(EmployerSignUpActivity.this, "Sign Up Failed..."+response.errorBody(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(EmployerSignUpActivity.this, "Failed  "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                edtname.setText("");
                edtemail.setText("");
                edtpass.setText("");
                edtcompanydes.setText("");
            }
        });

    }
    private void selectImage() {
        final CharSequence[] options = { "CAMERA", "GALLERY"};
        AlertDialog.Builder builder = new AlertDialog.Builder(EmployerSignUpActivity.this);
        builder.setTitle("Upload Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("CAMERA"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("GALLERY"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    imageView.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                imageView.setImageBitmap(thumbnail);
            }
        }
    }
}