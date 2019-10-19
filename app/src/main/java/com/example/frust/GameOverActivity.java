package com.example.frust;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private ImageButton retryBtn;
    private ImageButton submitBtn;
    private TextView eventText;
    private TextView scoreText;
    private EditText inputText;

    private String scoreString;
    private int score;

    private HighscoreManager highscoreManager;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over);
        getSupportActionBar().hide();

        highscoreManager = HighscoreManager.getInstance(this);
        soundManager = SoundManager.getInstance(this);

        initialize();
    }

    private void initialize() {
        retryBtn = findViewById(R.id.retryButton);
        submitBtn = findViewById(R.id.submitButton);
        eventText = findViewById(R.id.eventText);
        scoreText = findViewById(R.id.scoreText);
        inputText = findViewById(R.id.inputText);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), GameActivity.class));
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputText.getText().toString();
                //Intent intent = new Intent(view.getContext(), HighscoresActivity.class);
                Intent intent = new Intent(view.getContext(), GameActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("score", scoreString);
                highscoreManager.enterNewHighscore(name, score);
                startActivity(intent);
            }
        });

        scoreString = "0";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            scoreString = getIntent().getStringExtra("score");
        }
        score = Integer.valueOf(scoreString);

        scoreText.setText(scoreString);

        if (highscoreManager.isNewHighscore(score)) {
            eventText.setText("New Highscore");
            eventText.setTextColor(Color.RED);
            scoreText.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.INVISIBLE);
            inputText.setVisibility(View.VISIBLE);
            soundManager.playHighscore();
        } else {
            eventText.setText("Try again");
            eventText.setTextColor(Color.RED);
            scoreText.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.INVISIBLE);
            inputText.setVisibility(View.INVISIBLE);
            soundManager.playGameOver();
        }
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, GameActivity.class));
    }
}
