package game;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.AssetsPath;
import utils.SoundHandler;

import java.util.Random;


public class Word extends Text {

    private String wordText;
    private String mirrorWordValue = "";
    private int scoreValue;

    private double fallingSpeedBonus;
    private boolean isRotating;

    private int currentWordPosition;
    public boolean isDestroyed;

    Word(String text, boolean rotating) {
        super(text);

        this.wordText = text;
        this.currentWordPosition = 0;
        this.isRotating = rotating;
        this.fallingSpeedBonus = rotating ? 1.8 : 0;

        setFill(Color.WHITE);
        setStyle("-fx-font-size: 35;");

        this.scoreValue = calculateWordScore();

    }

    private int calculateWordScore() {
        int score = 10;
        if (this.isRotating) score += 10;
        if (this.wordText.length() > 10) score += 5;
        return score;
    }


    public void handleKey(char k) {

        if (k == this.wordText.charAt(this.currentWordPosition)) {
            this.currentWordPosition += 1;
            this.mirrorWordValue = this.mirrorWordValue + k;
            setText(this.wordText.substring(this.currentWordPosition));
            SoundHandler.playSound(AssetsPath.TYPING_SOUND);
        } else {
            this.mirrorWordValue = "";
            this.currentWordPosition = 0;
            setText(this.wordText);
        }

        if (this.wordText.equals(this.mirrorWordValue)) {
            this.isDestroyed = true;
            this.currentWordPosition = 0;
        }

    }

    public int getScoreValue() {
        return this.scoreValue;
    }

    public boolean isRotating() { return this.isRotating; }

    public double getFallingSpeedBonus() { return this.fallingSpeedBonus; }


    public void setNormalPattern() {
        Random r = new Random();

        this.wordText = GameStatus.wordsList[r.nextInt(GameStatus.wordsList.length)];
        this.mirrorWordValue = "";
        setText(this.wordText);

        setRotate(0.0);

        this.currentWordPosition = 0;
        this.fallingSpeedBonus = 0;
        this.isRotating = false;
        this.scoreValue = 10;
    }


}
