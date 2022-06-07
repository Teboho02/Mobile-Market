package com.example.mobilemarket;

import android.app.Activity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class makeRequest {

    String lnk;

    public String getLnk() {
        return lnk;
    }

    public void upload(Activity a, String link, String param1, String value1, String param2, String value2, String param3, String value3){
        OkHttpClient client = new OkHttpClient();


        HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();


        RequestBody formBody = new FormBody.Builder()
                .add(param1, value1)
                .add(param2,value2)
                .add(param3,value3)
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
                        System.out.println("responsedata = "+responseData);
                        lnk = responseData;
                    }
                });
            }
        });





    }





}
