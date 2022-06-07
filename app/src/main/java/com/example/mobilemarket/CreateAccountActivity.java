package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CreateAccountActivity extends AppCompatActivity {
    Button CAcreateAccount;
    EditText CAname, CAemail, CApassword, CAconfrimPassword;
    TextView creatingacc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        CAcreateAccount = (Button) findViewById(R.id.CAcreateAccount);
        CAname = (EditText)findViewById(R.id.CAedtName);
        CAemail = (EditText)findViewById(R.id.CAedtEmail);
        CApassword = (EditText)findViewById(R.id.CAedtPassword);
        CAconfrimPassword = (EditText)findViewById(R.id.CAconPassword);


        //TODO
        CAcreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if all require input fieds are filled
                //check if password matches

                if(CAemail.getText().toString().equals("")||CAname.getText().toString().equals("")||CApassword.getText().toString().equals("")||CAconfrimPassword.getText().toString().equals(" ")){
                    Toast.makeText(CreateAccountActivity.this, "Please make sure to fill all the fields", Toast.LENGTH_SHORT).show();
                }

                //if everything is okay then add to the database
               if(CheckPassword(CApassword,CAconfrimPassword)){


                    if(CApassword.getText().toString().length()<5){
                        Toast.makeText(CreateAccountActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                   addUser(CAname.getText().toString(),CAemail.getText().toString(),CApassword.getText().toString());

               }
               else{
                   Toast.makeText(CreateAccountActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
               }

            Intent i = new Intent(CreateAccountActivity.this,MainActivity.class);
               startActivity(i);

            }
        });

    }

    //TODO
    //Check if username exists in the database or not
    public void CheckIfUserExist(String email){
        //check in


    }

    public Boolean CheckPassword(EditText pass, EditText passcon){
        return pass.getText().toString().equals(passcon.getText().toString());
    }
    //Add user to the database
    public void addUser(String name, String username,String password){
        String link = "https://lamp.ms.wits.ac.za/~s2446577/insertData.php";
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();
        urlBuilder.addQueryParameter("username", username);
        urlBuilder.addQueryParameter("name", name);
        urlBuilder.addQueryParameter("password", password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding

                // Read data on the worker thread
                final String responseData = response.body().string();

                // Run view-related code back on the main thread
                CreateAccountActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }


}