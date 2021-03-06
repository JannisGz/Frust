package com.example.frust;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
/**
 * @author Jannis Gumz
 * An activity that manages sound and music settings
 */
public class SettingsActivity extends AppCompatActivity {

    private Button backBtn;
    private Switch musicSwitch;
    private Switch soundSwitch;

    private SettingsManager settingsManager;

    /**
     * Runs when the activity is created
     * @param savedInstanceState the current activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();

        backBtn = findViewById(R.id.mainBTN);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        musicSwitch = findViewById(R.id.musicSwitch);
        soundSwitch = findViewById(R.id.soundSwitch);
        settingsManager = SettingsManager.getInstance(this);

        musicSwitch.setChecked(settingsManager.musicIsOn());
        soundSwitch.setChecked(settingsManager.soundIsOn());

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsManager.setMusicSetting(isChecked);
            }
        });

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingsManager.setSoundSetting(isChecked);
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
