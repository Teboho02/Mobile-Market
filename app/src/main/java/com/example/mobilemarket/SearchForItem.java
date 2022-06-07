package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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

public class SearchForItem extends AppCompatActivity {





    static ArrayList<String> Ausername = new ArrayList<String>();
    static String[] username = new String[15];
    static String[] desc  = new String[15];
    static String[]  price = new String[15];
    static String[] product_name  = new String[15];
    static String[] averageRating  = new String[15];
    static String[] DATE =  new String[15];
    static ArrayList<String> Aprice = new ArrayList<String>();
    static  ArrayList<String> Adesc = new ArrayList<String>();
    static ArrayList<String> Adate = new ArrayList<String>();
    static ArrayList<String> base64 = new ArrayList<String>();
    static ArrayList<String> pname = new ArrayList<String>();
    EditText e;
    static int pos;
    ImageView v;
    static Bitmap[] bit= new Bitmap[100];
    int index = -1;
    ListView listView;



    Button searchButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_item);

        e = (EditText) findViewById(R.id.edtSearch);

        listView = (ListView) findViewById(R.id.ListView);
        searchButton = (Button) findViewById(R.id.btnSea);
        String link = "https://lamp.ms.wits.ac.za/~s2446577/search.php";

        makeRequest make = new makeRequest();
        make.upload(SearchForItem.this,link,"name","err","x","x","x","x");
        String result = make.getLnk();
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        System.out.println("result + "+ result);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse(link).newBuilder();
                urlBuilder.addQueryParameter("name", e.getText().toString());
                urlBuilder.addQueryParameter("q", "android");
                urlBuilder.addQueryParameter("rsz", "8");
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
                        SearchForItem.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(HomeAct.this, ""+responseData, Toast.LENGTH_SHORT).show();
                                System.out.println("response data = "+responseData);

                                try {
                                    ProcessJson(responseData,"imagebase64",base64);
                                    ProcessJson(responseData,  "DESCRIPTION",Adesc);
                                    ProcessJson(responseData,"Price",Aprice);
                                    ProcessJson(responseData,"username",Ausername);
                                    ProcessJson(responseData,"itemname",pname);
                                    ProcessJson(responseData,"date",Adate);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }





                                for(int i =0 ; i < base64.size(); i++){
                                    bit[i] = convertBase64ToBitmap(base64.get(i));
                                    desc[i] = Adesc.get(i);
                                    price[i] = "price " +Aprice.get(i);
                                    username[i] = Ausername.get(i);
                                    product_name[i] = pname.get(i);
                                    DATE[i] = Adate.get(i);




                                }


                                System.out.println("image array leng = "+bit.length);

                                Adesc.clear();
                                Adate.clear();
                                pname.clear();
                                Ausername.clear();
                                base64.clear();
                                Aprice.clear();




                            }
                        });
                    }
                });



            }
        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //    index = i;
                pos = i;
                Intent x =new Intent(SearchForItem.this, ViewSingleItem.class);
                startActivity(x);

            }
        });


        CustomBaseAdapter customBaseAdapter =  new CustomBaseAdapter(getApplicationContext(),username,desc,price,averageRating,bit,product_name,DATE);
        Toast.makeText(SearchForItem.this, "happened", Toast.LENGTH_LONG).show();
        listView.setAdapter(customBaseAdapter);








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