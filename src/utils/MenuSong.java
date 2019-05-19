package utils;

import javafx.scene.media.AudioClip;

public class MenuSong {

    private static AudioClip menuSong;

    public static void play() {
        String songPath = MenuSong.class.getResource("menu-song.mp3").toString();
        menuSong = new AudioClip(songPath);
        menuSong.play();
    }

    public static void stop() {
        menuSong.stop();
    }

    public static boolean isPlaying() {
        return menuSong != null && menuSong.isPlaying();
    }

}
