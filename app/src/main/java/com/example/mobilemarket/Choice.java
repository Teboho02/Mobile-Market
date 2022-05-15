package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Choice extends AppCompatActivity {
    ImageView gotoHome, search,upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        gotoHome = (ImageView) findViewById(R.id.gotoHomeActivity);
        search = (ImageView)findViewById(R.id.imgSearch);
        upload = (ImageView)findViewById(R.id.imgUpload);


        gotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice.this, HomeAct.class);
                startActivity(i);
            }
        });



        //TODO
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice.this,PostActivity.class);
                startActivity(i);
            }
        });
    }
}