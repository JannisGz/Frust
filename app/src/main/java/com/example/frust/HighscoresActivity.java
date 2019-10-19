package com.example.frust;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HighscoresActivity extends AppCompatActivity {

    Button backBtn;

    private TextView textViewName1st;
    private TextView textViewName2nd;
    private TextView textViewName3rd;
    private TextView textViewScore1st;
    private TextView textViewScore2nd;
    private TextView textViewScore3rd;

    private HighscoreManager highscoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highscores);
        getSupportActionBar().hide();

        backBtn = findViewById(R.id.mainBTN);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(view.getContext(), MainActivity.class));
                startActivity(new Intent(view.getContext(), GameActivity.class));
            }
        });

        textViewName1st = findViewById(R.id.name1);
        textViewScore1st = findViewById(R.id.score1);
        textViewName2nd = findViewById(R.id.name2);
        textViewScore2nd = findViewById(R.id.score2);
        textViewName3rd = findViewById(R.id.name3);
        textViewScore3rd = findViewById(R.id.score3);

        highscoreManager = HighscoreManager.getInstance(this);
        updateLeaderbord();
    }

    @Override
    public void onBackPressed(){
        //startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, GameActivity.class));
    }

    private void updateLeaderbord() {
        textViewName1st.setText(highscoreManager.getName1st());
        textViewScore1st.setText(String.valueOf(highscoreManager.getHighscore1st()));
        textViewName2nd.setText(highscoreManager.getName2nd());
        textViewScore2nd.setText(String.valueOf(highscoreManager.getHighscore2nd()));
        textViewName3rd.setText(highscoreManager.getName3rd());
        textViewScore3rd.setText(String.valueOf(highscoreManager.getHighscore3rd()));
    }

}
