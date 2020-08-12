package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import de.bernd.quizapp.ui.login.LoginActivity;
import de.bernd.quizapp.ui.login.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

    public final int LOAD_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToStart = new Intent(MainActivity.this, StartActivity.class);
                startActivity(goToStart);
                finish();
            }
        }, this.LOAD_TIME);
    }
}