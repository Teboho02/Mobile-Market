package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {
    Button changepass,update, btUpdateName, UPDATE;
    EditText oldpass, newpass, updatename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        changepass = (Button) findViewById(R.id.btnChagepassword);
        oldpass = (EditText) findViewById(R.id.edtOldpadd);
        newpass = (EditText) findViewById(R.id.edtNewpass);
        update = (Button) findViewById(R.id.btnUpdate);
        btUpdateName = (Button) findViewById(R.id.btnUpdateName);
        UPDATE = (Button) findViewById(R.id.btnU);
        updatename = (EditText) findViewById(R.id.edtUpdateame);






            btUpdateName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        updatename.setVisibility(View.VISIBLE);
                        UPDATE.setVisibility(View.VISIBLE);



                }
            });



        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpass.setVisibility(View.VISIBLE);
                newpass.setVisibility(View.VISIBLE);
                update.setVisibility(View.VISIBLE);


            }
        });

    }
}