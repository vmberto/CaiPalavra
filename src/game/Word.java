package game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

        setFill(Color.WHITE);

        this.wordText = text;
        this.currentWordPosition = 0;
        this.isRotating = rotating;
        this.fallingSpeedBonus = rotating ? 1.8 : 0;

        setStyle("-fx-font-size: 35;");

        this.scoreValue = calculateWordScore();

    }

    private int calculateWordScore() {
        return 10;
    }


    public void handleKey(char k) {

        if (k == this.wordText.charAt(this.currentWordPosition)) {
            this.currentWordPosition += 1;
            this.mirrorWordValue = this.mirrorWordValue + k;
            setText(this.wordText.substring(this.currentWordPosition));
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

        this.fallingSpeedBonus = 0;
        this.isRotating = false;
        this.scoreValue = 10;
    }


}
