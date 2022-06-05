package com.example.mobilemarket;

import static java.lang.Boolean.FALSE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

public class ViewSingleItem extends AppCompatActivity {

    EditText rate;
    TextView name, price, desciptiom, average_rating,date;
    Button submit_rating;
    ImageView imageView;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_item);
        imageView = (ImageView) findViewById(R.id.imageView);
        //rate  = (EditText) findViewById(R.id.VSIedtrating);
        //submit_rating = (Button) findViewById(R.id.VSIsubmitRating);
        name  = (TextView) findViewById(R.id.VSIname);
        price  = (TextView) findViewById(R.id.VSIprice);
        date = (TextView) findViewById(R.id.date);
        desciptiom  = (TextView) findViewById(R.id.VSIdescription);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);

       // String link = "https://lamp.ms.wits.ac.za/~s2446577/getData.php";
        String link2 = "https://lamp.ms.wits.ac.za/~s2446577/RatingInsert.php";







        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(ViewSingleItem.this, ""+v, Toast.LENGTH_SHORT).show();
                Float ratingbar;
               // ratingBar.isClickable(False);
                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse(link2).newBuilder();
                urlBuilder.addQueryParameter("username", MainActivity.user);
                urlBuilder.addQueryParameter("itemID", "android");
                urlBuilder.addQueryParameter("rating", String.valueOf(v));
                String url = urlBuilder.build().toString();

                Request request = new Request.Builder()
                        .url(url)
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
                        ViewSingleItem.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ViewSingleItem.this, ""+responseData, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


            }
        });


        int pos = HomeAct.pos;
        Bitmap bitmap = HomeAct.bit[pos];
        name.setText(HomeAct.username[pos]);
        desciptiom.setText(HomeAct.desc[pos]);
        price.setText(HomeAct.price[pos]);
        int a = HomeAct.getData();
        imageView.setImageBitmap(bitmap);

     //   Picasso.get().load("https://lamp.ms.wits.ac.za/~s2446577/image/car.jpg").into(imageView);

    }
    public String ProcessJson(String json, String param) throws JSONException {
        String requested = null;
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length();i++){
            JSONObject jo = ja.getJSONObject(i);
            String result = jo.getString(param);
         //    Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
            requested = result;
        }
        //Toast.makeText(this, ""+requested, Toast.LENGTH_SHORT).show();
        return requested;

    }
}