package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class SearchForItem extends AppCompatActivity {


    Bitmap bit[];
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
     //   CustomBaseAdapter customBaseAdapter =  new CustomBaseAdapter(getApplicationContext(),name,desc,price,aveRating,bit);
       // listView.setAdapter(customBaseAdapter);




        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.VISIBLE);
            }
        });

    }
}