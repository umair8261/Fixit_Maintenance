package com.example.khawaja_maintenance;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);

    }

    public void AC_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","ac");
        startActivity(intent);

    }

    public void electrical_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","electrical");
        startActivity(intent);
    }

    public void carpenter_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","carpenter");
        startActivity(intent);
    }

    public void plumber_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","plumber");
        startActivity(intent);
    }

    public void painter_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","painter");
        startActivity(intent);
    }

    public void machine_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","machine");
        startActivity(intent);
    }

    public void Gas_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","gas");
        startActivity(intent);
    }

    public void Washing_onclick(View view)
    {
        Intent intent=new Intent(this, User_information.class);
        intent.putExtra("selection","Washing_Machine");
        startActivity(intent);
    }
}