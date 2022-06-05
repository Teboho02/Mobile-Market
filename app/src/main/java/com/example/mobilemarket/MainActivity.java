package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    Button Login;
    Button CreateAccout;
    EditText email,password;
    TextView t;
    String f;
    static String user;
    static String li = "https://lamp.ms.wits.ac.za/~s2446577/cars2.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.edtPassword);
        CreateAccout = (Button) findViewById(R.id.btnCreateAccount);
        Login = (Button)findViewById(R.id.btnLogin);
        t = (TextView)findViewById(R.id.t);
        //use picasso to get pictures
        //icasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView)

        String link= "https://lamp.ms.wits.ac.za/~s2446577/userdetails.php";

        //todo


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(MainActivity.this, Choice.class);
                // startActivity(intent);
                String link = "https://lamp.ms.wits.ac.za/~s2446577/userdetails.php";

                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();
                urlBuilder.addQueryParameter("username", email.getText().toString());

                String url = urlBuilder.build().toString();

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        // ... check for failure using `isSuccessful` before proceeding

                        // Read data on the worker thread
                        final String responseData = response.body().string();

                        // Run view-related code back on the main thread
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //  Toast.makeText(MainActivity.this, ""+responseData, Toast.LENGTH_SHORT).show();
                                    if(!email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()) {
                                        if (ProcessJson(responseData).equals(password.getText().toString())) {
                                            Intent i = new Intent(MainActivity.this, Choice.class);


                                            user = email.getText().toString();
                                            i.putExtra("key",email.getText().toString());
                                            startActivity(i);
                                        } else {
                                            // Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                            password.setText("");
                                        }
                                    }else{
                                        //Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });



            }
        });





        //goes to CreateAccount Activity
        CreateAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });




    }

    //TODO
    public static void checkInputBox(EditText e, EditText p){
        Boolean email = false;
        Boolean password = false;

        for(int i = 0; i < e.getText().toString().trim().length(); i++){
            if(e.getText().toString().charAt(i)=='@'){
                email = true;
            }
        }

    }

    //Converts Json type data into easily readable
    public String ProcessJson(String json) throws JSONException {
        String requested = null;
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length();i++){
            JSONObject jo = ja.getJSONObject(i);
            String result = jo.getString("password");
            f = result;
          // Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
            requested = result;
        }
      //  Toast.makeText(this, ""+requested, Toast.LENGTH_SHORT).show();
        return requested;

    }


}