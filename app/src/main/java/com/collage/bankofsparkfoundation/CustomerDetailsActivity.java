package com.collage.bankofsparkfoundation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerDetailsActivity extends AppCompatActivity {


    EditText name , contact ,email, balance ;
    Button insert , update , delete , view ;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        balance  = findViewById(R.id.balance);
        contact = findViewById(R.id.contact);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String emailTXT = email.getText().toString();
                String balanceTXT = balance.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, balanceTXT , emailTXT);
                if(checkinsertdata==true)
                    Toast.makeText(CustomerDetailsActivity.this, "New Customer Customer   Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CustomerDetailsActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String emailTXT = email.getText().toString();
                String balanceTXT = balance.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, emailTXT, balanceTXT);
                if(checkupdatedata==true)
                    Toast.makeText(CustomerDetailsActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CustomerDetailsActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(CustomerDetailsActivity.this, "Customer Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CustomerDetailsActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(CustomerDetailsActivity.this, "No Customer Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("email :"+res.getString(3)+"\n\n");
                    buffer.append("balance :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerDetailsActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Customer Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });



    }
}