package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    Button Login;
    Button CreateAccout;
    EditText email,password;
    TextView t;
    String f;
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
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView)

        ContentValues params = new ContentValues();
//        params.put("", "");
//        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s2446577/CARS.php",params) {
//            @Override
//            protected void onPostExecute(String output) {
//                t.setText(output);
//            }
//        };
//        asyncHttpPost.execute();

        //todo
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // postRequest R = new postRequest();
//                params.put("", "");
//                AsyncHttpPost asyncHttpPost = new AsyncHttpPost("https://lamp.ms.wits.ac.za/~s2446577/user.php?EMAIL="+email.getText().toString(),params) {
//                    @Override
//                    protected void onPostExecute(String output) {
//                        String s = output;
//                    }
//                };
//                asyncHttpPost.execute();
                String s = doreQuest("https://lamp.ms.wits.ac.za/~s2446577/user.php?EMAIL="+email.getText().toString());
                System.out.println(f);
                try {
                    if(ProcessJson(t.getText().toString()).equals(password.getText().toString())) {


                        Intent home = new Intent(MainActivity.this, Choice.class);
                        startActivity(home);}

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_LONG).show();
                }

                System.out.println(f);
                try {
                    if(ProcessJson(t.getText().toString()).equals(password.getText().toString())){
                        Intent home = new Intent(MainActivity.this, Choice.class);
                        startActivity(home);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_LONG).show();
                }


             //   everyone = x.getinfo();
//                Toast.makeText(MainActivity.this, " "+everyone, Toast.LENGTH_LONG).show();
//                try {
//                    for (int i = 0; i < everyone.size(); i++) {
//                        try {
//                            if (email.getText().toString().equals(everyone.get(i))) {
//                                if (password.getText().toString().equals(everyone.get(i + 1))) ;
//                                {
//                                    Toast.makeText(MainActivity.this, "succedded login in", Toast.LENGTH_SHORT).show();
//                                    Intent DoPost = new Intent(MainActivity.this, PostActivity.class);
//                                    startActivity(DoPost);
//                                }
//
//                            }
//                        } catch (Exception e) {
//                            //do noti
//                        }
//                    }
//                }catch(Exception e){
//
//                }

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
        String password = null;
        JSONArray Jarray = new JSONArray(json);

        for(int i = 0;i < Jarray.length();i++){
            try{
                JSONObject oneObject = Jarray.getJSONObject(i);
                // Pulling items from the array
                String oneObjectsItem = oneObject.getString("EMAIL");
                password = oneObject.getString("password");
            }catch (Exception e){
                    e.printStackTrace();
            }
        }

        Toast.makeText(this, ""+password, Toast.LENGTH_SHORT).show();
        return password;

    }

    public String doreQuest(String url){
        final String[] ret = new String[1];

        OkHttpClient client = new OkHttpClient();

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
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  ret[0] = responseData;
                        f = responseData;
                        f = t.getText().toString();
                    }
                });
            }
        });
        return f;
    }
}