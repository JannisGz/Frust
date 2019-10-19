package com.example.frust;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Jannis Gumz
 * A class that manages the highscore-load/store functionality of the game using shared preferences.
 */
public final class HighscoreManager {

    private int highscore1st;
    private int highscore2nd;
    private int highscore3rd;
    private String name1st;
    private String name2nd;
    private String name3rd;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String HIGHSCORE_NAME_1 = "name1";
    private static final String HIGHSCORE_NAME_2 = "name2";
    private static final String HIGHSCORE_NAME_3 = "name3";
    private static final String HIGHSCORE_SCORE_1 = "highscore1";
    private static final String HIGHSCORE_SCORE_2 = "highscore2";
    private static final String HIGHSCORE_SCORE_3 = "highscore3";

    private Context context;

    private static HighscoreManager INSTANCE;

    /**
     * Creates a new HighscoreManager
     * @param context the application context
     */
    private HighscoreManager(Context context) {
        this.context = context;
        loadHighscore();
    }

    /**
     * Returns the HighscoreManager. This will always return the same instance. Only if no
     * HighscoreManager exists a new object will be created.
     * @param context the application context
     * @return the HighscoreManager
     */
    public static HighscoreManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new HighscoreManager(context);
        }
        return INSTANCE;
    }

    /**
     * @return the currently highest score
     */
    public int getHighscore1st() {
        return highscore1st;
    }

    /**
     * @return the currently second highest score
     */
    public int getHighscore2nd() {
        return highscore2nd;
    }

    /**
     * @return the currently lowest high score
     */
    public int getHighscore3rd() {
        return highscore3rd;
    }

    /**
     * @return the name of the player with the highest score
     */
    public String getName1st() {
        return name1st;
    }

    /**
     * @return the name of the player with the second highest score
     */
    public String getName2nd() {
        return name2nd;
    }

    /**
     * @return the name of the player with the lowest high score
     */
    public String getName3rd() {
        return name3rd;
    }

    /**
     * Checks if the new score is a new high score (i.e. the score is higher than the lowest saved
     * high score
     * @param score the checked score
     * @return true if score is a new high score, else false
     */
    public boolean isNewHighscore(int score) {
        loadHighscore();
        if (score > highscore3rd) {
            return true;
        }
        return false;
    }

    /**
     * Registers the name and score to the high score list, if the score is higher than at least
     * the third high score. Saves the new list to shared preferences.
     * @param name the players name
     * @param score the players score
     */
    public void enterNewHighscore(String name, int score) {
        if (name == null || name.equals("")) {
            name = "Unknown";
        }
        if (score < 0) {
            score = 0;
        }
        if (name.length() > 9) {
            name = name.substring(0,9);
        }
        if (isNewHighscore(score) && name != null) {
            if (score > highscore1st) {
                name3rd = name2nd;
                highscore3rd = highscore2nd;
                name2nd = name1st;
                highscore2nd = highscore1st;
                name1st = name;
                highscore1st = score;
            } else if (score > highscore2nd) {
                name3rd = name2nd;
                highscore3rd = highscore2nd;
                name2nd = name;
                highscore2nd = score;
            } else if (score > highscore3rd){
                name3rd = name;
                highscore3rd = score;
            }
            saveHighscore();
        }
    }

    /**
     * Updates the high scores stored in shared preferences to the in game values
     */
    public void saveHighscore() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(HIGHSCORE_SCORE_1, highscore1st);
        editor.putString(HIGHSCORE_NAME_1, name1st);
        editor.putInt(HIGHSCORE_SCORE_2, highscore2nd);
        editor.putString(HIGHSCORE_NAME_2, name2nd);
        editor.putInt(HIGHSCORE_SCORE_3, highscore3rd);
        editor.putString(HIGHSCORE_NAME_3, name3rd);
        editor.apply();

    }

    /**
     * Updates the in game high scores to the values stored in shared preferences
     */
    public void loadHighscore() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore1st = sharedPreferences.getInt(HIGHSCORE_SCORE_1, 300);
        name1st = sharedPreferences.getString(HIGHSCORE_NAME_1, "Gold");
        highscore2nd = sharedPreferences.getInt(HIGHSCORE_SCORE_2, 100);
        name2nd = sharedPreferences.getString(HIGHSCORE_NAME_2, "Silver");
        highscore3rd = sharedPreferences.getInt(HIGHSCORE_SCORE_3, 25);
        name3rd = sharedPreferences.getString(HIGHSCORE_NAME_3, "Bronze");
    }
}
