package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Owner_add_animal_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_animal);

        bottomNavigationView= findViewById(R.id.Bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.owner_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.owner_profile:
                        Intent resume = new Intent(Owner_add_animal_Activity.this,Owner_profile_Activity.class);
                        resume.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.find_walker:
                        Intent resume1 = new Intent(Owner_add_animal_Activity.this,Owner_find_walker_Activity.class);
                        resume1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume1, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.view_walker:
                        Intent resume2 = new Intent(Owner_add_animal_Activity.this,Owner_view_walkers_Activity.class);
                        resume2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivityIfNeeded(resume2, 0);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_animal:
                        return true;
                }
                return false;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Animal");
        userID = user.getUid();

        final TextView txt_name = (TextView)findViewById(R.id.textView_name);
        final TextView txt_gender = (TextView)findViewById(R.id.textView_gender);
        final TextView txt_age = (TextView)findViewById(R.id.textView_age);
        final TextView txt_breed = (TextView)findViewById(R.id.textView_breed);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Animal animalProfile = snapshot.getValue(Animal.class);

                if (animalProfile != null){
                    String name = animalProfile.Name_Animal;
                    String gender = animalProfile.Gender;
                    String age = animalProfile.Age;
                    String breed = animalProfile.Breed;

                    txt_name.setText(name);
                    txt_age.setText(age);
                    txt_gender.setText(gender);
                    txt_breed.setText(breed);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Owner_add_animal_Activity.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}