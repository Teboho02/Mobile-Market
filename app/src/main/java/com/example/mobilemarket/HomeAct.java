package com.example.mobilemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
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

public class HomeAct extends AppCompatActivity {
    String[] name = {"Car","PS5","Pen","Macbook","Dog","Car","PS5","Pen","Macbook","Dog","Car","PS5","Pen","Macbook","Dog","Car","PS5","Pen","Macbook","Dog"};
    String[] desc = {"2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog","2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog","2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog","2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog"};
    static String[] url = {"https://lamp.ms.wits.ac.za/~s2446577/image/car.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/ps5.png",
    "https://lamp.ms.wits.ac.za/~s2446577/image/pen.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/macbook.jpeg","" +
            "https://lamp.ms.wits.ac.za/~s2446577/image/dog.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/car.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/ps5.png",
            "https://lamp.ms.wits.ac.za/~s2446577/image/pen.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/macbook.jpeg","" +
            "https://lamp.ms.wits.ac.za/~s2446577/image/dog.jpg",
            "https://lamp.ms.wits.ac.za/~s2446577/image/car.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/ps5.png",
            "https://lamp.ms.wits.ac.za/~s2446577/image/pen.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/macbook.jpeg","" +
            "https://lamp.ms.wits.ac.za/~s2446577/image/dog.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/car.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/ps5.png",
            "https://lamp.ms.wits.ac.za/~s2446577/image/pen.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/macbook.jpeg","" +
            "https://lamp.ms.wits.ac.za/~s2446577/image/dog.jpg"};
    String[] price = {"R200 000","R7000","R5","R25000","R700","R200 000","R7000","R5","R25000","R700","R200 000","R7000","R5","R25000","R700","R200 000","R7000","R5","R25000","R700"};
    ImageView upload;
    ImageView v;
    int index = -1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView)findViewById(R.id.ListVi);
       v = (ImageView) findViewById(R.id.imagePosted);
        CustomBaseAdapter customBaseAdapter =  new CustomBaseAdapter(getApplicationContext(),name,desc,price,url);
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    index = i;
                    Intent x =new Intent(HomeAct.this, ViewSingleItem.class);
                    x.putExtra("key",i);
                    startActivity(x);

            }
        });


    }

    public static int getData(){
        //get data from serves and database
        return -1;
    }


}