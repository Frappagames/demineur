package com.frappagames.demineur.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Settings class provide an interface for manage preferences
 *
 * Created by jmoreau on 19/08/15.
 */
public class Settings {
    private static Preferences settings;

    private static int    bestEasyTime;
    private static int    easyGamePlayed;
    private static int    easyGameWin;

    private static int    bestNormalTime;
    private static int    normalGamePlayed;
    private static int    normalGameWin;

    private static int    bestHardTime;
    private static int    hardGamePlayed;
    private static int    hardGameWin;

    public static void load() {
        settings = Gdx.app.getPreferences("com.frappagames.demineur.settings");
        bestEasyTime = settings.getInteger("bestEasyTime", 0);
        easyGamePlayed = settings.getInteger("easyGamePlayed", 0);
        easyGameWin = settings.getInteger("easyGameWin", 0);
    }

    public static int getBestTime(int difficulty) {
        switch (difficulty) {
            case 1 : return bestEasyTime;
            case 2 : return bestNormalTime;
            case 3 : return bestHardTime;
            default : return 0;
        }
    }

    public static void setBestTime(int difficulty, int bestTime) {
        switch (difficulty) {
            case 1 :
                Settings.bestEasyTime = bestTime;
                settings.putInteger("bestEasyTime", bestTime);
                break;
            case 2 :
                Settings.bestNormalTime = bestTime;
                settings.putInteger("bestNormalTime", bestTime);
                break;
            case 3 :
                Settings.bestHardTime = bestTime;
                settings.putInteger("bestHardTime", bestTime);
                break;
        }
        settings.flush();
    }

    public static int getGamePlayed(int difficulty) {
        switch (difficulty) {
            case 1 : return easyGamePlayed;
            case 2 : return normalGamePlayed;
            case 3 : return hardGamePlayed;
            default : return 0;
        }
    }

    public static void setGamePlayed(int difficulty, int gamePlayed) {
        switch (difficulty) {
            case 1 :
                Settings.easyGamePlayed = gamePlayed;
                settings.putInteger("easyGamePlayed", gamePlayed);
                break;
            case 2 :
                Settings.normalGamePlayed = gamePlayed;
                settings.putInteger("normalGamePlayed", gamePlayed);
                break;
            case 3 :
                Settings.hardGamePlayed = gamePlayed;
                settings.putInteger("hardGamePlayed", gamePlayed);
                break;
        }
        settings.flush();
    }

    public static void increaseGamePlayed(int difficulty) {
        int gamePlayed = getGamePlayed(difficulty);
        setGamePlayed(difficulty, ++gamePlayed);
    }

    public static int getGameWin(int difficulty) {
        switch (difficulty) {
            case 1 : return easyGameWin;
            case 2 : return normalGameWin;
            case 3 : return hardGameWin;
            default : return 0;
        }
    }

    public static void setGameWin(int difficulty, int gameWin) {
        switch (difficulty) {
            case 1 :
                Settings.easyGameWin = gameWin;
                settings.putInteger("easyGameWin", gameWin);
                break;
            case 2 :
                Settings.normalGameWin = gameWin;
                settings.putInteger("normalGameWin", gameWin);
                break;
            case 3 :
                Settings.hardGameWin = gameWin;
                settings.putInteger("hardGameWin", gameWin);
                break;
        }
        settings.flush();
    }

    public static void increaseGameWin(int difficulty) {
        int gameWined = getGameWin(difficulty);
        setGameWin(difficulty, ++gameWined);
    }
}
