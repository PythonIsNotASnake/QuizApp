package de.bernd.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionView;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button forwardButton;
    private int index;
    private List<String> answers;
    private List<Button> answerButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.index = getIntent().getIntExtra("index", 0);

        this.questionView = findViewById(R.id.quizQuestionView);
        this.answerButton1 = findViewById(R.id.quizAnswerButton1);
        this.answerButton2 = findViewById(R.id.quizAnswerButton2);
        this.answerButton3 = findViewById(R.id.quizAnswerButton3);
        this.forwardButton = findViewById(R.id.quizForwardButton);

        this.forwardButton.setVisibility(View.INVISIBLE);

        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);

        this.answers = new ArrayList<>();
        this.answers.add(Globals.getInstance().getQuestions()[this.index].getRightAnswer());
        this.answers.add(Globals.getInstance().getQuestions()[this.index].getFalseAnswer1());
        this.answers.add(Globals.getInstance().getQuestions()[this.index].getFalseAnswer2());
        Collections.shuffle(this.answers);

        this.questionView.setText(Globals.getInstance().getQuestions()[this.index].getQuestion());
        this.answerButton1.setText(answers.get(0));
        this.answerButton2.setText(answers.get(1));
        this.answerButton3.setText(answers.get(2));

        this.answerButtons = new ArrayList<>();
        this.answerButtons.add(this.answerButton1);
        this.answerButtons.add(this.answerButton2);
        this.answerButtons.add(this.answerButton3);


        this.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.this.index++;
                if (QuizActivity.this.index >= Globals.getInstance().getQuestions().length) {
                    Intent goToMenu = new Intent(QuizActivity.this, MenuActivity.class);
                    startActivity(goToMenu);
                } else {
                    Intent nextQuestion = new Intent(QuizActivity.this, QuizActivity.class);
                    startActivity(nextQuestion.putExtra("index", QuizActivity.this.index));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {

        if(((Button) v).getText().toString() == Globals.getInstance().getQuestions()[this.index].getRightAnswer()) {
            UserController.getInstance().updateScore(Globals.getInstance().getUser().getNickName(), Globals.getInstance().getUser().getPassword(), 2);
            ((Button) v).setBackgroundColor(getResources().getColor(R.color.rightAnswer));
            ((Button) v).setTextColor(getResources().getColor(R.color.black));
            Toast toast = Toast.makeText(v.getContext(), "+2 Punkte", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            UserController.getInstance().updateScore(Globals.getInstance().getUser().getNickName(), Globals.getInstance().getUser().getPassword(), -1);
            for(int i=0; i < QuizActivity.this.answerButtons.size();i++) {
                if(QuizActivity.this.answerButtons.get(i).getText().toString() == Globals.getInstance().getQuestions()[this.index].getRightAnswer()) {
                    QuizActivity.this.answerButtons.get(i).setBackgroundColor(getResources().getColor(R.color.rightAnswer));
                    QuizActivity.this.answerButtons.get(i).setTextColor(getResources().getColor(R.color.black));
                    break;
                }
            }
            ((Button) v).setBackgroundColor(getResources().getColor(R.color.falseAnswer));
            ((Button) v).setTextColor(getResources().getColor(R.color.white));
            Toast toast = Toast.makeText(v.getContext(), "-1 Punkt", Toast.LENGTH_SHORT);
            toast.show();
        }
        this.forwardButton.setVisibility(View.VISIBLE);
    }
}