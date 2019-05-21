package game;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private AnchorPane gamePane;
    @FXML
    private Text gameCountdown;

    private TextField keyboardInput;

    /**
     * Game Start Countdown Properties
     */
    private int counter;
    private Timer startCountdown;
    private AudioClip beep;
    private AudioClip beepStart;
    /**
     * ------------------------------
     */

    private Word[] words;
    Random randomPositionGenerator = new Random();
    AnimationTimer gameTimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameStartCountdown();
    }

    private void gameStartCountdown() {
        String beepPath = this.getClass().getResource("../resources/audio/beep.mp3").toString();
        String beepStartPath = this.getClass().getResource("../resources/audio/beep-start.mp3").toString();

        beep = new AudioClip(beepPath);
        beepStart = new AudioClip(beepStartPath);

        final IntegerProperty i = new SimpleIntegerProperty(3);
        beep.play();
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            if (i.get() == 1) {
                                beepStart.play();
                                gamePane.getChildren().remove(gameCountdown);
                                gamePane.getScene().setOnKeyTyped(e -> sendKeyboardInputs(e.getCharacter().charAt(0)));

                                createGameElements();
                                createGameLoop();
                            } else {
                                beep.play();
                            }

                            i.set(i.get() - 1);

                            gameCountdown.setText(Integer.toString(i.get()));
                        }
                )
        );
        timeline.setCycleCount(3);
        timeline.play();

    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveGameElement();
                checkIfElementAndRelocate();
                checkDestroyedWords();
            }
        };
        gameTimer.start();
    }

    private void createGameElements() {
        words = new Word[2];
        for (int i = 0; i < words.length; i++) {
            words[i] = new Word(i == 0 ? "palavra" : "jogada");
            setNewElementPosition(words[i]);
            gamePane.getChildren().add(words[i]);
        }
    }

    private void setNewElementPosition(Word word) {
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();

        word.setLayoutX(randomPositionGenerator.nextInt((int) x.getWidth()));
        word.setLayoutY(0);
    }

    private void moveGameElement() {
        for (int i = 0; i < words.length; i++) {
            words[i].setLayoutY(words[i].getLayoutY() + 5);
//            words[i].setRotate(words[i].getRotate() + 4);
        }
    }

    private void checkIfElementAndRelocate() {
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();

        for (int i = 0; i < words.length; i++) {
            if (words[i].getLayoutY() > x.getHeight()) {
                setNewElementPosition(words[i]);
            }
        }

    }

    private void sendKeyboardInputs(char key) {
        for (int i = 0; i < words.length; i++) {
            words[i].handleKey(key);
        }
    }

    private void checkDestroyedWords() {
        for (int i = 0; i < words.length; i++) {
            if (words[i].isDestroyed) {
                gamePane.getChildren().remove(words[i]);
            }
        }
    }
}