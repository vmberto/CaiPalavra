package game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Word extends Text {

    private String wordValue;
    private String mirrorWordValue = "";
    private int currentWordPosition;
    public boolean isDestroyed;

    Word(String text) {
        super(text);

        this.wordValue = text;
        this.currentWordPosition = 0;

        setFill(Color.WHITE);
        setStyle("-fx-font-size: 50;");


    }

    public void handleKey(char k) {

        if (k == this.wordValue.charAt(this.currentWordPosition)) {
            this.currentWordPosition += 1;
            this.mirrorWordValue = this.mirrorWordValue + k;
            setText(this.wordValue.substring(this.currentWordPosition, this.wordValue.length()));
        } else {
            this.mirrorWordValue = "";
            this.currentWordPosition = 0;
            setText(this.wordValue);
        }

        if (this.wordValue.equals(this.mirrorWordValue)) {
            this.isDestroyed = true;
            this.currentWordPosition = 0;
        }

    }



}
