package com.max.doggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login_Walker_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    Button btn_walker,btn_login;
    private TextView forgotPassword;

    private FirebaseAuth fAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_walker);

        fAuth = FirebaseAuth.getInstance();

        btn_walker = findViewById(R.id.walker_sign_in);
        btn_walker.setOnClickListener(this);

        btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.textview_email);
        editTextPassword =(EditText) findViewById(R.id.textview_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.walker_sign_in:
                startActivity(new Intent(Login_Walker_Activity.this, Walker_sign_up_Activity.class));
                break;
            case R.id.button_login:
                Login();
                break;
            case R.id.forgot_password:
                startActivity(new Intent(this,ForgotPassword_Activity.class));
                break;
        }
    }

    private void Login() {
        String Email = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();

        if(Email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(Password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(Password.length()< 6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        startActivity(new Intent(Login_Walker_Activity.this, Walker_profile_Activity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(Login_Walker_Activity.this,"Check your email to verify your account!",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Login_Walker_Activity.this, "Failed to Login! Please check your information",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}