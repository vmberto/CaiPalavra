package utils;

import javafx.scene.media.AudioClip;

public class SoundHandler {

    private static AudioClip menuSong;

    private static AudioClip gameSong;

    private static boolean isGameSoundDisabled = false;

    public static void playMenuSong() {
        String songPath = SoundHandler.class.getResource(AssetsPath.MENU_SONG).toString();
        menuSong = new AudioClip(songPath);
        menuSong.setCycleCount(AudioClip.INDEFINITE);
        menuSong.setVolume(0.6);
        menuSong.play();
    }

    public static void playGameSong() {
        if (!isGameSoundDisabled) {
            String songPath = SoundHandler.class.getResource(AssetsPath.GAME_SONG).toString();
            gameSong = new AudioClip(songPath);
            gameSong.setCycleCount(AudioClip.INDEFINITE);
            gameSong.setVolume(0.6);
            gameSong.play();
        }
    }

    public static void stopGameSong() {
        if (gameSong != null) {
            gameSong.stop();
        }
    }

    public static void stop() {
        menuSong.stop();
    }

    public static void disableSound() {
        isGameSoundDisabled = true;
        stop();
    }

    /**
     * The only place where is possible to enable the sound is in the menu.
     */
    public static void enableSound() {
        isGameSoundDisabled = false;
        playMenuSong();
    }

    public static boolean isPlaying() {
        return menuSong != null && menuSong.isPlaying();
    }

    public static boolean isGameSoundDisabled() { return isGameSoundDisabled; }

    public static void playSound(String soundPath) {
        if (!isGameSoundDisabled()) {
            String sp = SoundHandler.class.getResource(soundPath).toString();
            new AudioClip(sp).play();
        }

    }



}
