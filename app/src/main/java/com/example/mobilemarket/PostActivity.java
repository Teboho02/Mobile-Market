package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostActivity extends AppCompatActivity {
    ImageView image;
    static String date, price, name, desc;
    Uri imageuri;
    static Bitmap img;
    Button upload;
    private static int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        image = (ImageView) findViewById(R.id.img);
        upload = (Button)findViewById(R.id.btnUpload);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PostActivity.this, "Click registered", Toast.LENGTH_SHORT).show();
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"select a picture"),PICK_IMAGE);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                date = now.toString();
                Toast.makeText(PostActivity.this, ""+date, Toast.LENGTH_SHORT).show();
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://lamp.ms.wits.ac.za/~s2446577/up2.php";

                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();
                urlBuilder.addQueryParameter("image", imageToString(img));

                urlBuilder.addQueryParameter("fileToUpload", imageToString(img));
                //urlBuilder.addQueryParameter("rsz", "8");
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

                        // Run view-related code back on the main thread
                        PostActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PostActivity.this, ""+responseData, Toast.LENGTH_SHORT).show();
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


                image.setImageBitmap(bitmap);
            }catch (Exception e){
                //
            }
        }
    }

    public static ArrayList<String> return_data(){
        ArrayList<String> s = new ArrayList<>();
        s.add(name);
        s.add(price);
        s.add(desc);
        s.add(date);

        //todo
        //Upload to the database


        return s;
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }

}