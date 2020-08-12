package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button loginBtn = findViewById(R.id.startButtonLogin);
        Button registrationBtn = findViewById(R.id.startButtonRegistration);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(StartActivity.this, LoginActivity2.class);
                startActivity(goToLogin);
            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegistration = new Intent(StartActivity.this, RegistrationActivity2.class);
                startActivity(goToRegistration);
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}