package com.example.khawaja_maintenance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class splash_referrals extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_referrals);



    }

    public void next(View view)
    {
        Intent intent=new Intent(this,splash_dealing_two.class );
        startActivity(intent);
    }
}