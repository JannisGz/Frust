package com.example.frust;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Jannis Gumz
 * An activity that gets displayed on startup and when the back button is pressed in other
 * activities. Displays a play button to start the GameActivity, a gear button to start the
 * SettingsActivity and a medal button to display the HighscoresActivity
 */
public class MainActivity extends AppCompatActivity {

    private ImageView playBtn;
    private ImageView settingBtn;
    private ImageView highscoresBtn;


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

    /**
     * Binds the activities buttons to variables and sets up OnClickListeners for them.
     */
    private void initialize() {
        playBtn = (ImageView) findViewById(R.id.playBTN);
        highscoresBtn = (ImageView) findViewById(R.id.highscoreBTN);
        settingBtn = (ImageView) findViewById(R.id.settingsBTN);

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

    /**
     * Runs when the back button of the smart phone is pressed. Starts the mainActivity.
     */
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
