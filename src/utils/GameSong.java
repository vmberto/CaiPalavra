package utils;

import javafx.scene.media.AudioClip;

public class GameSong {

    private static AudioClip gameSong;

    private static boolean isGameSongDisabled = false;

    public static void play() {
        String songPath = GameSong.class.getResource("../resources/audio/game-song.mp3").toString();
        gameSong = new AudioClip(songPath);
        gameSong.setCycleCount(AudioClip.INDEFINITE);
        gameSong.play();
    }
    public static void stop() {
        gameSong.stop();
    }

    public static void disableSong() {
        isGameSongDisabled = true;
        stop();
    }

    public static void enableSong() {
        isGameSongDisabled = false;
        play();
    }

    public static boolean isPlaying() {
        return gameSong != null && gameSong.isPlaying();
    }

    public static boolean isGameSongDisabled() { return isGameSongDisabled; }



}
