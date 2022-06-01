package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Owner_find_walker_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button findwalker;
    private String OwnerID;
    private DatabaseReference OwnerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_find_walker);


        bottomNavigationView= findViewById(R.id.Bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.owner_profile);

        OwnerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        OwnerRef = FirebaseDatabase.getInstance().getReference().child("Owner").child("Address");

        findwalker = (Button)findViewById(R.id.button_find_walker);

        findwalker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeoFire geoFire = new GeoFire(OwnerRef);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.owner_profile:
                        Intent resume = new Intent(Owner_find_walker_Activity.this,Owner_profile_Activity.class);
                        resume.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.find_walker:
                        return true;
                    case R.id.view_walker:
                        Intent resume1 = new Intent(Owner_find_walker_Activity.this,Owner_view_walkers_Activity.class);
                        resume1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume1, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_animal:
                        Intent resume2 = new Intent(Owner_find_walker_Activity.this,Owner_add_animal_Activity.class);
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