package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Owner_sign_up_Activity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText editTextemail,editTextpassword,editTextname,editTexttelephone;
    private EditText editTextname_animal,editTextgender,editTextage,editTextbreed;
    private Button owner_sign_up;
    private ImageView Logo;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_sign_up);

        mAuth = FirebaseAuth.getInstance();

        owner_sign_up = (Button) findViewById(R.id.button_owner_sign_up);
        owner_sign_up.setOnClickListener(this);

        Logo = (ImageView) findViewById(R.id.imageView2);
        Logo.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editTextemail = (EditText) findViewById(R.id.textview_email);
        editTextpassword =(EditText) findViewById(R.id.textview_password);
        editTextname =(EditText) findViewById(R.id.textview_name);
        editTexttelephone =(EditText) findViewById(R.id.textview_telephone);

        editTextname_animal =(EditText) findViewById(R.id.textview_animal);
        editTextgender =(EditText) findViewById(R.id.textview_animal_gender);
        editTextage = (EditText) findViewById(R.id.textview_age);
        editTextbreed = (EditText) findViewById(R.id.textview_breed);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_owner_sign_up:
                setOwner_sign_up();
                break;
            case R.id.imageView2:
                startActivity(new Intent(this, Login_Owner_Activity.class));
                break;
            default:
        }
    }

    private void setOwner_sign_up(){
        String Email = editTextemail.getText().toString().trim();
        String Password = editTextpassword.getText().toString().trim();
        String Name = editTextname.getText().toString().trim();
        String Telephone = editTexttelephone.getText().toString().trim();

        String Name_Animal = editTextname_animal.getText().toString().trim();
        String Gender = editTextgender.getText().toString().trim();
        String Age = editTextage.getText().toString().trim();
        String Breed = editTextbreed.getText().toString().trim();

        if(Email.isEmpty()){
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextemail.setError("Please provide valid email!");
            editTextemail.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            editTextpassword.setError("Password is required!");
            editTextpassword.requestFocus();
            return;
        }

        if(Password.length()< 6){
            editTextpassword.setError("Min password length should be 6 characters!");
            editTextpassword.requestFocus();
            return;
        }

        if(Name.isEmpty()){
            editTextname.setError("Full name is required!");
            editTextname.requestFocus();
            return;
        }

        if(Telephone.isEmpty()){
            editTexttelephone.setError("Telephone number is required!");
            editTexttelephone.requestFocus();
            return;
        }

        if(Name_Animal.isEmpty()){
            editTextname_animal.setError("Name of animal is required!");
            editTextname_animal.requestFocus();
            return;
        }

        if(Gender.isEmpty()){
            editTextgender.setError("Animal gender is required!");
            editTextgender.requestFocus();
            return;
        }

        if(Age.isEmpty()){
            editTextage.setError("Age of animal is required!");
            editTextage.requestFocus();
            return;
        }

        if(Breed.isEmpty()){
            editTextbreed.setError("Breed of animal is required!");
            editTextbreed.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Owner owner = new Owner(Name,Email,Telephone);
                            Animal animal = new Animal(Name_Animal,Gender,Age,Breed);

                            FirebaseDatabase.getInstance().getReference("Owner")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(owner).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Owner_sign_up_Activity.this,"Owner has been signed up successfully",Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(Owner_sign_up_Activity.this,"Failed to sign up! Check your information ",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                            FirebaseDatabase.getInstance().getReference("Animal")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(animal).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Owner_sign_up_Activity.this,"Owner has been signed up successfully",Toast.LENGTH_LONG).show();
                                            }else {
                                                Toast.makeText(Owner_sign_up_Activity.this,"Failed to sign up! Check your information ",Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(Owner_sign_up_Activity.this,"Failed to sign up! Check your information ",Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }
}