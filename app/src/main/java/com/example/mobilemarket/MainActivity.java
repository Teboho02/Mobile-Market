package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button Login;
    Button CreateAccout;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.edtEmail);
        password = (EditText)findViewById(R.id.edtPassword);
        CreateAccout = (Button) findViewById(R.id.btnCreateAccount);
        Login = (Button)findViewById(R.id.btnLogin);


        //todo
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        CreateAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });




    }


    //TODO
    public static void checkInputBox(EditText e, EditText p){
        Boolean email = false;
        Boolean password = false;

        for(int i = 0; i < e.getText().toString().trim().length(); i++){
            if(e.getText().toString().charAt(i)=='@'){
                email = true;
            }
        }

    }
}