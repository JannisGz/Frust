package com.example.frust;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button playBtn;
    private Button settingBtn;
    private Button highscoresBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initialize();
    }

    private void initialize() {
        playBtn = (Button) findViewById(R.id.playBTN);
        highscoresBtn = (Button) findViewById(R.id.highscoreBTN);
        settingBtn = (Button) findViewById(R.id.settingsBTN);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), GameActivity.class));
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SettingsActivity.class));
            }
        });

        highscoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), HighscoresActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
