package game;

import java.util.Random;

public class DificultyManager {

    private static double fallingSpeed = 3.8;

    public static double getFallingSpeed() {
        return fallingSpeed;
    }

    public static void increaseFallingSpeed(double accelerate) {
        fallingSpeed += accelerate;
    }

    /**
     * Player score must be above 100, if true, 1 in 15 words will be rotating
     * If Player score is above 500, 1 in 9 words will be rotating
     * @return
     */
    public static boolean isWordRotating() {
        Random r = new Random();
        boolean isRotating;

        if (GameStatus.getPlayerScore() > 100) {
            isRotating = r.nextInt(15) == 1 ? true : false;
        }
        else if (GameStatus.getPlayerScore() > 500) {
            isRotating = r.nextInt(9) == 1 ? true : false;
        } else {
            isRotating = false;
        }

        return isRotating;
    }

    public static int getWordsSpawnNumber() {
        int spawnNumber = 1;
        if (GameStatus.getPlayerScore() >= 100) {
            spawnNumber = 2;
        } else if (GameStatus.getPlayerScore() >= 350) {
            spawnNumber = 3;
        } else if (GameStatus.getPlayerScore() >= 700) {
            spawnNumber = 4;
        } else if (GameStatus.getPlayerScore() >= 1000) {
            spawnNumber = 5;
        }

        return spawnNumber;
    }

    public static void reset() {
        fallingSpeed = 3.8;
    }

}
