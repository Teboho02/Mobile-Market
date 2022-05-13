package com.example.mobilemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {
    Button CAcreateAccount;
    EditText CAname, CAemail, CApassword, CAconfrimPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        CAcreateAccount = (Button) findViewById(R.id.CAcreateAccount);
        CAname = (EditText)findViewById(R.id.CAedtName);
        CAemail = (EditText)findViewById(R.id.CAedtEmail);
        CApassword = (EditText)findViewById(R.id.CAedtPassword);







        //TODO
        CAcreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreaateAcc creaateAcc = null;
                Intent intent = new Intent(CreateAccountActivity.this, HomeAct.class);
                startActivity(intent);
//                try {
//                    creaateAcc = new CreaateAcc(CAname.getText().toString(),CAemail.getText().toString(),CApassword.getText().toString());
//                }
//                catch (Exception e){
//                    Toast.makeText(CreateAccountActivity.this, "e", Toast.LENGTH_SHORT).show();
//                }
//
//                CreateAccoutdatabase database1 = new CreateAccoutdatabase(CreateAccountActivity.this,"table",null,1);
//                boolean isSuccess = database1.addItem(creaateAcc);
//                Toast.makeText(CreateAccountActivity.this, "IS SUC"+isSuccess, Toast.LENGTH_SHORT).show();

            }
        });

    }

}