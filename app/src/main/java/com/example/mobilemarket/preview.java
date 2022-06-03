package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class preview extends AppCompatActivity {
    ImageView testImage;
    String f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_preview);
        //testImage = (ImageView) findViewById(R.id.testImage);
        Toast.makeText(this, "in preview", Toast.LENGTH_SHORT).show();
        String link = "https://lamp.ms.wits.ac.za/~s2446577/cars.php";
        ContentValues params = new ContentValues();
        params.put("username","tebza");
        AsyncHttpPost asyncHttpPost = new AsyncHttpPost(link,params) {
            @Override
            protected void onPostExecute(String output) {
                Toast.makeText(preview.this, ""+output, Toast.LENGTH_LONG).show();
            }
        };







    }


}