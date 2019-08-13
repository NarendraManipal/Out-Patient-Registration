package com.example.visioncare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button OPRegistration = findViewById(R.id.OPRegistration);

        OPRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opRegistrationActivity();
            }
        });
    }

    public void opRegistrationActivity()
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}

