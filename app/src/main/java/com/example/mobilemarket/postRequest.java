package com.example.mobilemarket;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class postRequest {
    static String ret;
    public static String pos(String link, Activity a,String param1,String param1Value){
        final String[] response_cl = {null};
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();
        urlBuilder.addQueryParameter(param1, param1Value);
  //      urlBuilder.addQueryParameter("q", "android");
   //     urlBuilder.addQueryParameter("rsz", "8");
        String url = urlBuilder.build().toString();




        Request request = new Request.Builder()
                .url(link)
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
                //return responseData;
                response_cl[0] = responseData;

                // Run view-related code back on the main thread
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(a, response_cl[0], Toast.LENGTH_SHORT).show();
                        ret = response_cl[0];

                    }
                });
            }
        });

        return ret;
    }

}
