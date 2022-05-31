package com.max.doggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseUser extends AppCompatActivity implements View.OnClickListener {
    private Button btn_owner,btn_walker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        btn_owner =(Button) findViewById(R.id.button_choose_owner);
        btn_owner.setOnClickListener(this);

        btn_walker = (Button)  findViewById(R.id.button_choose_walker);
        btn_walker.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_choose_owner:
                startActivity(new Intent(ChooseUser.this, Login_Owner_Activity.class));
                break;
            case R.id.button_choose_walker:
                startActivity(new Intent(ChooseUser.this,Login_Walker_Activity.class));
                break;
        }
    }
}