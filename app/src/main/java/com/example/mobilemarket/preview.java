package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class preview extends AppCompatActivity {
    ImageView testImage;
    String f;
    Uri imageuri;
    Bitmap img;
    Button click;
    static String Simage;
    Bitmap sec;
    private static int PICK_IMAGE = 1;
    ImageView imageshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_preview);
        testImage = (ImageView) findViewById(R.id.imagevv);


        click = (Button) findViewById(R.id.click);
        imageshow = (ImageView) findViewById(R.id.imageShow);


        testImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"select a picture"),PICK_IMAGE);
            }
        });


       click.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String link = "https://lamp.ms.wits.ac.za/~s2446577/imageX.php";

               OkHttpClient client = new OkHttpClient();


               HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();
           //    urlBuilder.addQueryParameter("id", "1");
         //      urlBuilder.addQueryParameter("img", Simage);
             //  String url = urlBuilder.build().toString();


               RequestBody formBody = new FormBody.Builder()
                       .add("id", "1")
                       .add("img",Simage)
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
                       preview.this.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(preview.this, ""+responseData, Toast.LENGTH_SHORT).show();
                               try {
                                   sec = convertBase64ToBitmap(ProcessJson(responseData));
                                   imageshow.setImageBitmap(sec);

                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                       });
                   }
               });


           }
       });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== PICK_IMAGE && resultCode == RESULT_OK){
            imageuri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                img = bitmap;
                Simage = imageToString(bitmap);

                testImage.setImageBitmap(bitmap);
            }catch (Exception e){

            }
        }
    }


    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }

    public String ProcessJson(String json) throws JSONException {
        String requested = null;
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length();i++){
            JSONObject jo = ja.getJSONObject(i);
            String result = jo.getString("IMG");
            f = result;
            // Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
            requested = result;
        }
        //  Toast.makeText(this, ""+requested, Toast.LENGTH_SHORT).show();
        return requested;

    }

    private Bitmap convertBase64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }





}