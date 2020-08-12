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
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        Button registrationBtn = findViewById(R.id.registrationButtonLogin);
        this.loadingProgressBar = findViewById(R.id.registrationLoading);

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationActivity2.this.loadingProgressBar.setVisibility(v.VISIBLE);

                RegistrationActivity2.this.nickNameInput = findViewById(R.id.registrationUsername);
                RegistrationActivity2.this.passwordInput = findViewById(R.id.registrationPassword);
                String nickName = "" + RegistrationActivity2.this.nickNameInput.getText();
                String password = "" + RegistrationActivity2.this.passwordInput.getText();
                UserController.getInstance().registerUser(nickName, password, RegistrationActivity2.this, v);

                //loadingProgressBar.setVisibility(v.GONE);
            }
        });

    }

    public TextView getNickNameInput() {
        return nickNameInput;
    }

    public TextView getPasswordInput() {
        return passwordInput;
    }

    public ProgressBar getLoadingProgressBar() {
        return loadingProgressBar;
    }

}