package com.collage.bankofsparkfoundation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewAllCustomersActivity extends AppCompatActivity {


    EditText name , amount , accountnumber;
    Button insert,view;
    BDHelper BD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.amount);
        accountnumber = findViewById(R.id.accountnumber);
        insert = findViewById(R.id.btnInsert);


        view = findViewById(R.id.btnView);
        BD = new BDHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String accountnumberTXT = accountnumber.getText().toString();
                String amountTXT = amount.getText().toString();

                Boolean checkinsertdata = BD.insertuserdata(nameTXT, amountTXT, accountnumberTXT);
                if(checkinsertdata==true)
                    Toast.makeText(ViewAllCustomersActivity.this, "Money  transaction Sucessfull", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ViewAllCustomersActivity.this, "oops ! Something Wrong :(", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = BD.getdata();
                if(res.getCount()==0){
                    Toast.makeText(ViewAllCustomersActivity.this, "oops ! Something Wrong :(", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("accountnumber :"+res.getString(2)+"\n");
                    buffer.append("amount :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewAllCustomersActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Money Transaction Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}
