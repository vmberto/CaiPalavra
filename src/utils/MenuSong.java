package utils;

import javafx.scene.media.AudioClip;

public class MenuSong {

    private static AudioClip menuSong;

    private static boolean isMenuSongDisabled = false;

    public static void play() {
        String songPath = MenuSong.class.getResource("../resources/audio/menu-song.mp3").toString();
        menuSong = new AudioClip(songPath);
        menuSong.setCycleCount(AudioClip.INDEFINITE);
        menuSong.setVolume(0.6);
        menuSong.play();
    }
    public static void stop() {
        menuSong.stop();
    }

    public static void disableSong() {
        isMenuSongDisabled = true;
        stop();
    }

    public static void enableSong() {
        isMenuSongDisabled = false;
        play();
    }

    public static boolean isPlaying() {
        return menuSong != null && menuSong.isPlaying();
    }

    public static boolean isMenuSongDisabled() { return isMenuSongDisabled; }

    public static void playSound(String soundPath) {
        if (!isMenuSongDisabled()) {
            String sp = MenuSong.class.getResource(".." + soundPath).toString();
            new AudioClip(sp).play();
        }

    }



}
