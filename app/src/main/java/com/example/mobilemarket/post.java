package com.example.mobilemarket;


import android.app.Activity;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class post {


    //String link = "https://lamp.ms.wits.ac.za/~s2446577/imageX.php";
    static String returndata;
    public String upload(Activity a,String link,String in1){
        OkHttpClient client = new OkHttpClient();


        HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();


        RequestBody formBody = new FormBody.Builder()
                .add("username", in1)
                .build();
        Request request = new Request.Builder()
                .url(link).post(formBody)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding

                // Read data on the worker thread
                final String responseData = response.body().string();

                // Run view-related code back on the main thread
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(responseData);
                        returndata = responseData;

                    }
                });
            }
        });


        return returndata;
    }


}
