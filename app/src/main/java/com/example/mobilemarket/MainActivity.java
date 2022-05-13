package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
                CreateAccoutdatabase x = new CreateAccoutdatabase(MainActivity.this,"name",null,1);
                ArrayList<String> everyone = x.getinfo();
                Toast.makeText(MainActivity.this, " "+everyone, Toast.LENGTH_LONG).show();
                for(int i = 0; i < everyone.size();i++) {
                    try {
                        if (email.getText().toString().equals(everyone.get(i))) {
                            if (password.getText().toString().equals(everyone.get(i + 1))) ;
                            {
                                Toast.makeText(MainActivity.this, "succedded login in", Toast.LENGTH_SHORT).show();
                                Intent DoPost = new Intent(MainActivity.this, PostActivity.class);
                                startActivity(DoPost);
                            }

                        }
                    }catch (Exception e){
                        //do noti
                    }
                }

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