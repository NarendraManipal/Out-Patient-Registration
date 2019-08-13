package com.example.visioncare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(v -> registrationActivity());
    }

    public void registrationActivity()
    {
        Intent registrationIntent = new Intent(Register.this, Registration.class);
        startActivity(registrationIntent);
    }
}
