package com.collage.bankofsparkfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button ViewAllCustomers;
    Button CustomerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewAllCustomers = (Button) findViewById(R.id.ViewAllCustomers);
        ViewAllCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewAllCustomersActivity.class);
                startActivity(intent);

            }
        });

        CustomerDetails = (Button) findViewById(R.id.CustomerDetails);
        CustomerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomerDetailsActivity.class);
                startActivity(intent);

            }
        });
    }
}

