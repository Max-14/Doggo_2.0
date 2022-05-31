package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Walker_Accept_Order_Activity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_accept_order);

        bottomNavigationView= findViewById(R.id.Bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.walker_accept_order);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.walker_profile:
                        Intent resume = new Intent(Walker_Accept_Order_Activity.this,Walker_profile_Activity.class);
                        resume.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.walker_accept_order:
                        return true;
                    case R.id.walker_walk_history:
                        Intent resume1 = new Intent(Walker_Accept_Order_Activity.this,Walker_walk_history_Activity.class);
                        resume1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume1, 0);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}