package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Owner_view_walkers_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view_walkers);


        bottomNavigationView= findViewById(R.id.Bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.owner_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.owner_profile:
                        Intent resume = new Intent(Owner_view_walkers_Activity.this,Owner_profile_Activity.class);
                        resume.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.find_walker:
                        Intent resume1 = new Intent(Owner_view_walkers_Activity.this,Owner_find_walker_Activity.class);
                        resume1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume1, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.view_walker:
                        return true;
                    case R.id.add_animal:
                        Intent resume2 = new Intent(Owner_view_walkers_Activity.this,Owner_add_animal_Activity.class);
                        resume2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume2, 0);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}