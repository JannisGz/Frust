package com.example.frust;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Jannis Gumz
 * A class that manages the settings-load/store functionality of the game using shared preferences.
 * Currently it only manages sound and music settings.
 */
public final class SettingsManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String MUSIC_SETTING = "musicSetting";
    private static final String SOUND_SETTING = "soundSetting";

    private boolean musicIsOn;
    private boolean soundIsOn;

    private static SettingsManager INSTANCE;

    /**
     * Creates a new SettingsManager
     * @param context the application context
     */
    private SettingsManager(Context context) {
        this.context = context;
        loadSettings();
    }

    /**
     * Returns the SettingsManager. This will always return the same instance. Only if no
     * SettingsManager exists a new object will be created.
     * @param context the application context
     * @return the SettingsManager
     */
    public static SettingsManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SettingsManager(context);
        }
        return INSTANCE;
    }

    /**
     * Returns whether the music is set to on (true) or off (false)
     * @return the current music setting
     */
    public boolean musicIsOn() {
        loadSettings();
        return musicIsOn;
    }

    /**
     * Sets the music to on (true) or off (false)
     * @param musicIsOn desired setting
     */
    public void setMusicSetting(boolean musicIsOn) {
        this.musicIsOn = musicIsOn;
        saveSettings();
    }

    /**
     * Returns whether the sound is set to on (true) or off (false)
     * @return the current sound setting
     */
    public boolean soundIsOn() {
        loadSettings();
        return soundIsOn;
    }

    /**
     * Sets the sound to on (true) or off (false)
     * @param soundIsOn desired setting
     */
    public void setSoundSetting(boolean soundIsOn) {
        this.soundIsOn = soundIsOn;
        saveSettings();
    }

    /**
     * Looks up the settings for sound and music stored in shared preferences and updates the
     * values returned by this class.
     */
    private void loadSettings() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        musicIsOn = sharedPreferences.getBoolean(MUSIC_SETTING, true);
        soundIsOn = sharedPreferences.getBoolean(SOUND_SETTING, true);
    }

    /**
     * Updates the values for sound and music settings in shared preferences.
     */
    private void saveSettings() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(MUSIC_SETTING, musicIsOn);
        editor.putBoolean(SOUND_SETTING, soundIsOn);
        editor.apply();
    }


}
