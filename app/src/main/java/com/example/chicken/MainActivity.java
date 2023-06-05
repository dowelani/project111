package com.example.chicken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static Context context;
    static String message="";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context =this;
    }


    public void send(View view) {
        EditText name=findViewById(R.id.editTextTextPersonName);
        EditText phoneNumber=findViewById(R.id.editTextTextPersonName2);
        EditText orderType=findViewById(R.id.editTextTextPersonName3);
        EditText numLife=findViewById(R.id.editTextTextPersonName4);
        EditText numProcessed=findViewById(R.id.editTextTextPersonName5);
        EditText extraNotes=findViewById(R.id.editTextTextPersonName6);

        if(orderType.getText().toString().equals("Enter address if not collecting order")||orderType.getText().toString().equals("")){
            message= "Name:"+name.getText().toString()+System.getProperty("line.separator")+"Phone number:"+phoneNumber.getText().toString()
                    +System.getProperty("line.separator")+"Order type:collection"+System.getProperty("line.separator")+"No. of life chickens:"+numLife.getText().toString()+
                    System.getProperty("line.separator")+"No. of processed chickens:"+numProcessed.getText().toString()+System.getProperty("line.separator")+
                    "Extra notes:"+extraNotes.getText().toString();}
        else {
           message= "Name:"+name.getText().toString()+System.getProperty("line.separator")+"Phone number:"+phoneNumber.getText().toString()
                    +System.getProperty("line.separator")+"Order type:delivery"+System.getProperty("line.separator")+"Delivery address:"+orderType.getText().toString()
            +System.getProperty("line.separator")+"No. of life chickens:"+numLife.getText().toString()+System.getProperty("line.separator")+
                   "No. of processed chickens:"+numProcessed.getText().toString()+System.getProperty("line.separator")+
                   "Extra notes:"+extraNotes.getText().toString();
        }

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0815127567", null, message, null, null);
            name.setVisibility(View.GONE);
            phoneNumber.setVisibility(View.GONE);
            orderType.setVisibility(View.GONE);
            numLife.setVisibility(View.GONE);
            numProcessed.setVisibility(View.GONE);
            extraNotes.setVisibility(View.GONE);
            TextView textView =findViewById(R.id.textView2);
            textView.setVisibility(View.GONE);
            TextView textView1 =findViewById(R.id.textView3);
            textView1.setVisibility(View.GONE);
            Button button=findViewById(R.id.button4);
            button.setVisibility(View.GONE);
            Button button1=findViewById(R.id.button);
            button1.setVisibility(View.VISIBLE);
            TextView textView2 =findViewById(R.id.textView);
            textView2.setVisibility(View.VISIBLE);
            TextView textView3 =findViewById(R.id.textView4);
            int price=(90* Integer.parseInt(numLife.getText().toString()));
            int price1=(100* Integer.parseInt(numProcessed.getText().toString()));
            int totalPrice=price+price1;
            textView3.setText("Amount due: R"+totalPrice);
            textView3.setVisibility(View.VISIBLE);


        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Order failed, please try again.", Toast.LENGTH_LONG).show();
        }
    }


    public void done(View view) {
        Intent intent = new Intent(this, FirstPage.class);
        startActivity(intent);
    }
}