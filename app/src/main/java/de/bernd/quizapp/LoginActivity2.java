package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity2 extends AppCompatActivity {

    private TextView nickNameInput;
    private TextView passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Button loginBtn = findViewById(R.id.loginButtonLogin);
        final ProgressBar loadingProgressBar = findViewById(R.id.loginLoading);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(v.VISIBLE);
                boolean callDone = false;

                LoginActivity2.this.nickNameInput = findViewById(R.id.loginUsername);
                LoginActivity2.this.passwordInput = findViewById(R.id.loginPassword);
                String nickName = "" + LoginActivity2.this.nickNameInput.getText();
                String password = "" + LoginActivity2.this.passwordInput.getText();
                Globals.getInstance().setOnCall(true);
                boolean loggedIn = UserController.getInstance().loginUser(nickName, password);

                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        while (Globals.getInstance().isOnCall()) {
                        }
                        if (Globals.getInstance().getUser().isLoggedIn()) {
                            Intent goToMenu = new Intent(LoginActivity2.this, MenuActivity.class);
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