package com.example.frust;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * @author Jannis Gumz
 * A class that manages the sound and music functionality of the game.
 */
public class SoundManager {

    final private MediaPlayer gameOverPlayer;
    final private MediaPlayer highscorePlayer;
    final private MediaPlayer positiveSoundPlayer;
    final private MediaPlayer musicPlayer;

    private static SoundManager INSTANCE;
    private SettingsManager settingsManager;

    /**
     * Creates a new SoundManager
     * @param context the application context
     */
    private SoundManager(Context context) {
        gameOverPlayer = MediaPlayer.create(context, R.raw.game_over);
        highscorePlayer = MediaPlayer.create(context, R.raw.highscore);
        positiveSoundPlayer = MediaPlayer.create(context, R.raw.positive);
        musicPlayer = MediaPlayer.create(context, R.raw.music);
        musicPlayer.setLooping(true);
        settingsManager = SettingsManager.getInstance(context);
    }

    /**
     * Returns the SoundManager. This will always return the same instance. Only if no
     * SoundManager exists a new object will be created.
     * @param context the application context
     * @return the SoundManager
     */
    public static SoundManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SoundManager(context);
        }
        return INSTANCE;
    }

    /**
     * Plays the game over sound if sound is on
     */
    public void playGameOver() {
        if (settingsManager.soundIsOn()) {
            gameOverPlayer.start();
        }
    }

    /**
     * Plays a positive sound if sound is on
     */
    public void playPositiveSound() {
        if (settingsManager.soundIsOn()) {
            positiveSoundPlayer.start();
        }
    }

    /**
     * Plays a new high score jingle if sound is on
     */
    public void playHighscore() {
        if (settingsManager.soundIsOn()) {
            highscorePlayer.start();
        }
    }

    /**
     * Starts the soundtrack if music is set to on
     */
    public void playMusic() {
        if (settingsManager.musicIsOn()) {
            musicPlayer.start();
        }
    }

    /**
     * Pauses the soundtrack
     */
    public void pauseMusic() {
        musicPlayer.pause();
    }
}
