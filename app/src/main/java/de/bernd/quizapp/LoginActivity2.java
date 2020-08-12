package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoginActivity2 extends AppCompatActivity {

    private TextView nickNameInput;
    private TextView passwordInput;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Button loginBtn = findViewById(R.id.loginButtonLogin);
        this.loadingProgressBar = findViewById(R.id.loginLoading);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity2.this.loadingProgressBar.setVisibility(v.VISIBLE);
                LoginActivity2.this.nickNameInput = findViewById(R.id.loginUsername);
                LoginActivity2.this.passwordInput = findViewById(R.id.loginPassword);
                String nickName = "" + LoginActivity2.this.nickNameInput.getText();
                String password = "" + LoginActivity2.this.passwordInput.getText();
                UserController.getInstance().loginUser(nickName, password, LoginActivity2.this, v);
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