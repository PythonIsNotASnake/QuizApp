package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.bernd.quizapp.ui.login.LoginActivity;
import de.bernd.quizapp.ui.login.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.mainButtonLogin);
        Button registrationBtn = findViewById(R.id.mainButtonRegistration);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent goToLogin = new Intent(MainActivity.this, LoginActivity2.class);
                // UserController userController = new UserController();
                // UserController.getInstance().getLeaderBoard("Bernd", "abc");
                Intent goToLogin = new Intent(MainActivity.this, LoginActivity2.class);
                startActivity(goToLogin);
            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionController.getInstance().getQuestions(5);
                Intent goToRegistration = new Intent(MainActivity.this, RegistrationActivity2.class);
                startActivity(goToRegistration);
            }
        });
    }
}