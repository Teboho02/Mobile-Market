package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeAct extends AppCompatActivity {
    String[] username = new String[100];
    String[] desc = new String[100];
    String[] url = new String[100];
    String[] date = new String[100];
    String[]  price = new String[100];
    String[] image  = new String[100];
    String[] averageRating = new String[100];
    ArrayList<String>  Aurl, Aimage;
    ArrayList<String> Ausername = new ArrayList<String>();
    ArrayList<String> Aprice = new ArrayList<String>();
    ArrayList<String> Adesc = new ArrayList<String>();
    ArrayList<String> Adate = new ArrayList<String>();
    ArrayList<String> base64 = new ArrayList<String>();

    ArrayList<String>  AverageRating = new ArrayList<String >();
    ImageView upload;
    ImageView v;
    Bitmap[] bit= new Bitmap[100];
    int index = -1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView)findViewById(R.id.ListVi);
        v = (ImageView) findViewById(R.id.imagePosted);


        String link = "https://lamp.ms.wits.ac.za/~s2446577/getData.php";


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(link)
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
                HomeAct.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(HomeAct.this, ""+responseData, Toast.LENGTH_SHORT).show();
                        System.out.println(responseData);
                        try {
                            ProcessJson(responseData,"Price",Aprice);
                            ProcessJson(responseData,"Date",Adate);
                            ProcessJson(responseData,"imagebase64",base64);
                            ProcessJson(responseData,"DESCRIPTION",Adesc);
                            ProcessJson(responseData,"username",Ausername);


                            price = convertToArray(Aprice);
                            username = convertToArray(Ausername);
                            desc = convertToArray(Adesc);
                            image = convertToArray(base64);
                            date = convertToArray(Adate);


                            for(int i = 0; i < base64.size();i++){
                                bit[i] = convertBase64ToBitmap(image[i]);
                            }



                            System.out.println("size = "+Aprice.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                Intent x =new Intent(HomeAct.this, ViewSingleItem.class);
                startActivity(x);

            }
        });


        CustomBaseAdapter customBaseAdapter =  new CustomBaseAdapter(getApplicationContext(),username,desc,price,averageRating,bit);
        listView.setAdapter(customBaseAdapter);

    }




    public static int getData(){
        return 1;
    }

    public String ProcessJson(String json,String param, ArrayList<String> x) throws JSONException {
        String requested = null;
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length();i++){
            JSONObject jo = ja.getJSONObject(i);
            String result = jo.getString(param);
            x.add(result);


            // Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
            requested = result;
        }

        for(int i = 0; i < x.size();i++){
            System.out.println(x.get(i));
        }
        //  Toast.makeText(this, ""+requested, Toast.LENGTH_SHORT).show();
        return requested;

    }


    private Bitmap convertBase64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    public String[] convertToArray(ArrayList<String> a){
        String[] x = new String[a.size()];
        for(int i = 0; i < a.size();i++){
            x[i] = a.get(i);
        }
        return x;

    }




}