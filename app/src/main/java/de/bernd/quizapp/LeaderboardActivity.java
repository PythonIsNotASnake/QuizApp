package de.bernd.quizapp;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView firstPlace;
    private TextView secondPlace;
    private TextView thirdPlace;
    private TextView fourthPlace;
    private TextView fifthPlace;
    private TextView sixthPlace;
    private TextView seventhPlace;
    private TextView eighthPlace;
    private TextView ninthPlace;
    private TextView tenthPlace;
    private List<TextView> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        this.firstPlace = findViewById(R.id.leaderBoardTextView1);
        this.secondPlace = findViewById(R.id.leaderBoardTextView2);
        this.thirdPlace = findViewById(R.id.leaderBoardTextView3);
        this.fourthPlace = findViewById(R.id.leaderBoardTextView4);
        this.fifthPlace = findViewById(R.id.leaderBoardTextView5);
        this.sixthPlace = findViewById(R.id.leaderBoardTextView6);
        this.seventhPlace = findViewById(R.id.leaderBoardTextView7);
        this.eighthPlace = findViewById(R.id.leaderBoardTextView8);
        this.ninthPlace = findViewById(R.id.leaderBoardTextView9);
        this.tenthPlace = findViewById(R.id.leaderBoardTextView10);

        this.places = new ArrayList<>();

        this.places.add(this.firstPlace);
        this.places.add(this.secondPlace);
        this.places.add(this.thirdPlace);
        this.places.add(this.fourthPlace);
        this.places.add(this.fifthPlace);
        this.places.add(this.sixthPlace);
        this.places.add(this.seventhPlace);
        this.places.add(this.eighthPlace);
        this.places.add(this.ninthPlace);
        this.places.add(this.tenthPlace);

        UserController.getInstance().getLeaderBoard(Globals.getInstance().getUser().getNickName(), Globals.getInstance().getUser().getPassword(), LeaderboardActivity.this);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        //toolBarLayout.setTitle(getTitle());

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public List<TextView> getPlaces() {
        return places;
    }
}