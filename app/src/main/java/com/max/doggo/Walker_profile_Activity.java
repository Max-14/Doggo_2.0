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

public class Walker_profile_Activity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_home);

        bottomNavigationView= findViewById(R.id.Bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.walker_profile);

        signOut = (Button)findViewById(R.id.button_signout);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Walker_profile_Activity.this,ChooseUser.class));
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.walker_profile:
                        return true;
                    case R.id.walker_accept_order:
                        startActivity(new Intent(getApplicationContext(),Walker_Accept_Order_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.walker_walk_history:
                        startActivity(new Intent(getApplicationContext(),Walker_walk_history_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Walker");
        userID = user.getUid();

        final TextView txt_name = (TextView)findViewById(R.id.textView_name);
        final TextView txt_email = (TextView)findViewById(R.id.textView_title);
        final TextView txt_number = (TextView)findViewById(R.id.textView_number);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Walker walkerProfile = snapshot.getValue(Walker.class);

                if (walkerProfile != null){
                    String name = walkerProfile.Name;
                    String email = walkerProfile.Email_Address;
                    String number = walkerProfile.Telephone;

                    txt_name.setText(name);
                    txt_email.setText(email);
                    txt_number.setText(number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Walker_profile_Activity.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}