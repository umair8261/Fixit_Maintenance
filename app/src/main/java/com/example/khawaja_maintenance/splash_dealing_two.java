package com.example.khawaja_maintenance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class splash_dealing_two extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_dealing_two);

    }

    public void next_login(View view)
    {
        Intent intent=new Intent(this,Login.class );
        startActivity(intent);
    }
}