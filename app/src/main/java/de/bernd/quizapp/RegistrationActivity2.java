package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegistrationActivity2 extends AppCompatActivity {

    private TextView nickNameInput;
    private TextView passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        Button registrationBtn = findViewById(R.id.registrationButtonLogin);
        final ProgressBar loadingProgressBar = findViewById(R.id.registrationLoading);

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(v.VISIBLE);
                boolean callDone = false;

                RegistrationActivity2.this.nickNameInput = findViewById(R.id.registrationUsername);
                RegistrationActivity2.this.passwordInput = findViewById(R.id.registrationPassword);
                String nickName = "" + RegistrationActivity2.this.nickNameInput.getText();
                String password = "" + RegistrationActivity2.this.passwordInput.getText();
                Globals.getInstance().setOnCall(true);
                boolean loggedIn = UserController.getInstance().registerUser(nickName, password);

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        while (Globals.getInstance().isOnCall()) {
                        }
                        if (Globals.getInstance().getUser().isLoggedIn()) {
                            Intent goToMenu = new Intent(RegistrationActivity2.this, MenuActivity.class);
                            startActivity(goToMenu);
                        }
                    }
                });
                thread.start();

                loadingProgressBar.setVisibility(v.GONE);
            }
        });

    }
}