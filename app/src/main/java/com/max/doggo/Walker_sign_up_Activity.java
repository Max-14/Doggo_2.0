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

public class Walker_sign_up_Activity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth fAuth;

    private EditText editTextemail,editTextpassword,editTextname,editTexttelephone,editTextpassport;
    private Button walker_sign_up;
    private ImageView Logo;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_sign_up);

        fAuth = FirebaseAuth.getInstance();

        walker_sign_up = (Button) findViewById(R.id.button_walker_sign_up);
        walker_sign_up.setOnClickListener(this);

        Logo = (ImageView) findViewById(R.id.imageView2);
        Logo.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editTextemail = (EditText) findViewById(R.id.textview_email);
        editTextpassword =(EditText) findViewById(R.id.textview_password);
        editTextname =(EditText) findViewById(R.id.textview_name);
        editTexttelephone =(EditText) findViewById(R.id.textview_telephone);
        editTextpassport = (EditText) findViewById(R.id.textview_passport);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_walker_sign_up:
                setWalker_sign_up();
                break;
            case R.id.imageView2:
                startActivity(new Intent(this, Login_Walker_Activity.class));
                break;
            default:
        }
    }

    private void setWalker_sign_up(){
        String Email = editTextemail.getText().toString().trim();
        String Password = editTextpassword.getText().toString().trim();
        String Name = editTextname.getText().toString().trim();
        String Telephone = editTexttelephone.getText().toString().trim();
        String Passport = editTextpassport.getText().toString().trim();

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

        if(Passport.isEmpty()){
            editTextpassport.setError("Passport Number is required!");
            editTextpassport.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        fAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Walker walker = new Walker(Name,Email,Telephone,Passport);

                            FirebaseDatabase.getInstance().getReference("Walker")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(walker).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Walker_sign_up_Activity.this,"Walker has been signed up successfully",Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(Walker_sign_up_Activity.this,"Failed to sign up! Check your information ",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        }else{
                            Toast.makeText(Walker_sign_up_Activity.this,"Failed to sign up! Check your information ",Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

}