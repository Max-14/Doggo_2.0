package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class Owner_profile_Activity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        bottomNavigationView= findViewById(R.id.Bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.owner_profile);

        signOut = (Button)findViewById(R.id.button_signout);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Owner_profile_Activity.this,ChooseUser.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.owner_profile:
                        return true;
                    case R.id.find_walker:
                        startActivity(new Intent(getApplicationContext(),Owner_find_walker_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.view_walker:
                        startActivity(new Intent(getApplicationContext(),Owner_view_walkers_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_animal:
                        startActivity(new Intent(getApplicationContext(),Owner_add_animal_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Owner");
        userID = user.getUid();

        final TextView txt_name = (TextView)findViewById(R.id.textView_name);
        final TextView txt_email = (TextView)findViewById(R.id.textView_title);
        final TextView txt_number = (TextView)findViewById(R.id.textView_number);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Owner ownerProfile = snapshot.getValue(Owner.class);

                if (ownerProfile != null){
                    String name = ownerProfile.Name;
                    String email = ownerProfile.Email_Address;
                    String number = ownerProfile.Telephone;

                    txt_name.setText(name);
                    txt_email.setText(email);
                    txt_number.setText(number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Owner_profile_Activity.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}