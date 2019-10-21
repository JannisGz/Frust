package com.example.frust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Jannis Gumz
 * An activity that runs the game
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GameView(this));
        getSupportActionBar().hide();
    }

    /**
     * Runs when the back button of the smart phone is pressed. Starts the mainActivity.
     */
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
