package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView greetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.greetings = findViewById(R.id.menuTextGreeting);

        UserController.getInstance().getScore(Globals.getInstance().getUser().getNickName(), Globals.getInstance().getUser().getPassword(), greetings);

        Button logoutBtn = findViewById(R.id.menuLogoutButton);
        Button playBtn = findViewById(R.id.menuButtonPlay);
        Button leaderBoardBtn = findViewById(R.id.menuButtonLeaderboard);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(MenuActivity.this, StartActivity.class);
                startActivity(logout);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionController.getInstance().getQuestions(5, MenuActivity.this);
            }
        });

        leaderBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLeaderBoard = new Intent(MenuActivity.this, LeaderboardActivity.class);
                startActivity(goToLeaderBoard);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}