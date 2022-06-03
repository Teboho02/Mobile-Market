package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class SearchForItem extends AppCompatActivity {
    String[] name = {"Car","PS5","Pen","Macbook","Dog","Car","PS5","Pen","Macbook","Dog","Car","PS5","Pen","Macbook","Dog","Car","PS5","Pen","Macbook","Dog"};
    String[] desc = {"2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog","2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog","2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog","2002 Audi A4 with 80 miles . Still looks and works like a new car" +
            "","New PlayStaion 5","Blue 5mm pen","2020 M1 MAX macbook air","big dog"};
    String[] url = {"https://lamp.ms.wits.ac.za/~s2446577/image/car.jpg","https://lamp.ms.wits.ac.za/~s2446577/image/ps5.png",
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
    Button search;
            ListView listView;
    ImageView upload;
    ImageView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_item);
        search = (Button) findViewById(R.id.btnSea);
        listView = (ListView)findViewById(R.id.ListView);
        listView.setVisibility(View.INVISIBLE);
        v = (ImageView) findViewById(R.id.imagePosted);
        CustomBaseAdapter customBaseAdapter =  new CustomBaseAdapter(getApplicationContext(),name,desc,price,url);
        listView.setAdapter(customBaseAdapter);




        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.VISIBLE);
            }
        });

    }
}