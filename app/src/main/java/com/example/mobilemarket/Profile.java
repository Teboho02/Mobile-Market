package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    Button changepass;
    EditText oldpass, newpass;
    Button btnUpdate;

    Button btnupdatename;
    EditText upname;
    Button updname;
    Button deleteacc;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        changepass = (Button) findViewById(R.id.btnChagepassword);
        oldpass = (EditText) findViewById(R.id.edtOldpadd);
        newpass = findViewById(R.id.edtNewpass);
        btnupdatename = (Button) findViewById(R.id.btnUpdateName);
        upname = (EditText) findViewById(R.id.edtUpdateame);
        updname = (Button) findViewById(R.id.btnU);
        deleteacc = (Button) findViewById(R.id.btnDeleteAccount);
        save = (Button) findViewById(R.id.btnSsave);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this,Choice.class);
                startActivity(i);
            }
        });


        deleteacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                makeRequest make = new makeRequest();
                make.upload(Profile.this,"https://lamp.ms.wits.ac.za/~s2446577/deleteUser.php","username",MainActivity.user,"x","x","y","y"
                );


                Toast.makeText(Profile.this, "deleting account", Toast.LENGTH_LONG).show();

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(Profile.this, MainActivity.class);
                startActivity(i);

            }
        });

        btnupdatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upname.setVisibility(View.VISIBLE);
                updname.setVisibility(View.VISIBLE);

            }
        });


        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                oldpass.setVisibility(View.VISIBLE);
                newpass.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.VISIBLE);



            }
        });


        updname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    makeRequest make = new makeRequest();
                    String link = "https://lamp.ms.wits.ac.za/~s2446577/updateName.php";
                    make.upload(Profile.this,link,"username",MainActivity.user,"name",upname.getText().toString()
                    ,"x","x");

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(MainActivity.pass.equals(oldpass.getText().toString())){
                    String link = "https://lamp.ms.wits.ac.za/~s2446577/ChangePass.php";
                    if(!newpass.getText().toString().isEmpty()){

                        makeRequest make = new makeRequest();
                        make.upload(Profile.this,link,"username",MainActivity.user,"password",newpass.getText().toString(),"x","x");


                    }
                    else{

                        Toast.makeText(Profile.this, "new passwprd cannot be empty", Toast.LENGTH_SHORT).show();

                    }
                    makeRequest make = new makeRequest();
                    make.upload(Profile.this,link,"username",MainActivity.user,"password",newpass.getText().toString(),"x","x");


                }
                else{
                    //do not change password
                }



            }
        });







        /*delete user

             makeRequest make = new makeRequest();
                make.upload(Profile.this,"https://lamp.ms.wits.ac.za/~s2446577/deleteUser.php","username",MainActivity.user,"x","x","y","y"
                );
         */

        /*change pass



         */









    }
}