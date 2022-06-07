package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Choice extends AppCompatActivity {
    ImageView gotoHome, search,upload, profile;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        //


        //pass data between two activities
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
            //Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
            String s = MainActivity.user;
            Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
            //The key argument here must match that used in the other activity
        }

        gotoHome = (ImageView) findViewById(R.id.gotoHomeActivity);
        search = (ImageView)findViewById(R.id.imgSearch);
        upload = (ImageView)findViewById(R.id.imgUpload);
        profile = (ImageView) findViewById(R.id.imageProfile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice.this, Profile.class);
                startActivity(i);

            }
        });


        gotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice.this, HomeAct.class);
                i.putExtra("key",value);
                startActivity(i);
            }
        });



        //TODO
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice.this,SearchForItem.class);
                startActivity(i);
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