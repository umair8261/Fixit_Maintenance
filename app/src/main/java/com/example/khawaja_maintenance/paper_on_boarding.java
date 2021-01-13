package com.example.khawaja_maintenance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class paper_on_boarding extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_on_boarding);

        PaperOnboardingPage scr1 = new PaperOnboardingPage("Maintenance",
                "Get your home appliances fixed on time",
                Color.parseColor("#f0f2f5"), R.drawable.maintenance, R.drawable.main);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Home Services & More",
            "Air Conditioner Mechanic, Electrician, Plumber, Carpenter, Geyser Mechanic, Painter, Washing Machine Mechanic, Water Pump, Motor Winding",
            Color.parseColor("#f0f2f5"), R.drawable.home, R.drawable.home_icon);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Contact Us",
                "Mobile & Whatsapp Number \n 0900 78601",
                Color.parseColor("#f0f2f5"), R.drawable.contact, R.drawable.appointments);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);

        FragmentManager fragmentManager=getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, onBoardingFragment);
        fragmentTransaction.commit();

        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut()
            {
                Intent intent=new Intent(getApplication(), splash_referrals.class);
                startActivity(intent);

            }
        });

    }
}