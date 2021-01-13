package com.example.khawaja_maintenance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Order_Generate extends AppCompatActivity
{
    int order_no;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__generate);



            Intent intent= getIntent();
            order_no= intent.getIntExtra("Order", -1);

        txt=findViewById(R.id.txt_orderGen);
        txt.setText("Order ID "+order_no);


    }

    public void Order_Placed(View view)
    {
        Intent intent=new Intent(this,Home_Screen.class);
        startActivity(intent);
    }
}