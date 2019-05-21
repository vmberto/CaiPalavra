package game;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import utils.AssetsPath;
import utils.GameSong;
import utils.MenuSong;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private AnchorPane gamePane;

    private Text gameCountdown;
    private AudioClip beep;
    private AudioClip beepStart;


    private HBox hearts;
    private HBox score;
    private List<Word> words = new ArrayList<>();
    AnimationTimer gameTimer;

    private int elementNumber = 1;
    private int playerScore = GameStatus.getPlayerScore();
    private Text playerScoreDisplay;

    @Override
    public void initialize(URL url, ResourceBundle rb) { gameStartCountdown(); }

    private void gameStartCountdown() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        String beepPath = this.getClass().getResource(AssetsPath.COUNTDOWN_BEEP_SOUND).toString();
        String beepStartPath = this.getClass().getResource(AssetsPath.COUNTDOWN_BEEP_START_SOUND).toString();

        beep = new AudioClip(beepPath);
        beepStart = new AudioClip(beepStartPath);


        final IntegerProperty i = new SimpleIntegerProperty(3);
        gameCountdown = new Text(Integer.toString(i.get()));
        gameCountdown.setFill(Color.WHITE);
        gameCountdown.setStyle("-fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 70;");
        gameCountdown.setLayoutX(d.getWidth() / 2);
        gameCountdown.setLayoutY(d.getHeight() / 2);
        gamePane.getChildren().add(gameCountdown);

        beep.play();
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            if (i.get() == 1) {
                                beepStart.play();
                                gamePane.getChildren().remove(gameCountdown);

                                GameSong.play();
                                gameInitialized();

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

    /** Game Engine */
    private void gameInitialized() {
        gamePane.getScene().setOnKeyTyped(e -> sendKeyboardInputs(e.getCharacter().charAt(0)));
        createPlayerLives();
        createPlayerScore();

        createGameWords(elementNumber);
        createGameLoop();
    }

    /**
     * Creates and Display player's score
     */
    private void createPlayerScore() {
        score = new HBox();
        Image pointsIcon = new Image(AssetsPath.POINTS_ICON_IMAGE);

        playerScoreDisplay = new Text(Integer.toString(0));
        playerScoreDisplay.setFill(Color.WHITE);
        playerScoreDisplay.setStyle("-fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 40;");

        score.setSpacing(10.0);

        score.getChildren().addAll(new ImageView(pointsIcon), playerScoreDisplay);

        score.setLayoutX(50);
        score.setLayoutY(40);

        score.setAlignment(Pos.CENTER);

        gamePane.getChildren().add(score);
    }

    /**
     * Creates and Display player's lives
     */
    private void createPlayerLives() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        hearts = new HBox();
        Image heart = new Image(AssetsPath.HEART_CONTAINER_IMAGE);

        hearts.getChildren().addAll(new ImageView(heart), new ImageView(heart), new ImageView(heart));

        hearts.setLayoutX(d.getWidth() - 250);
        hearts.setLayoutY(40);

        hearts.setAlignment(Pos.CENTER);

        gamePane.getChildren().add(hearts);

    }

    /**
     * Sends keyboard input chars for each words in the screen
     * @param key
     */
    private void sendKeyboardInputs(char key) {
        for (int i = 0; i < words.size(); i++) {
            words.get(i).handleKey(key);
        }
    }


    private void createGameWords(int elementNumber) {
        Random r = new Random();

        for (int i = 0; i < elementNumber; i++) {
            Word newWord = new Word(GameStatus.wordsList[r.nextInt(GameStatus.wordsList.length)]);
            words.add(newWord);
            setNewElementPosition(newWord);
            gamePane.getChildren().add(newWord);
        }
    }

    /**
     * Sets a position based on the user's screen
     * @param word
     */
    private void setNewElementPosition(Word word) {
        Random r = new Random();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        int minX = (int) d.getWidth() / 4;
        int maxX = (int) d.getWidth() - ( (int) d.getWidth() / 4);
        word.setLayoutX(r.nextInt(maxX - minX) + minX);

        word.setLayoutY(0);
    }


    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveGameElement();

                checkPlayerScore();
                checkPlayerLives();
                checkIfElementHitTheGround();
                checkDestroyedWords();
            }
        };
        gameTimer.start();
    }



    private void moveGameElement() {
        for (int i = 0; i < words.size(); i++) {
            words.get(i).setLayoutY(words.get(i).getLayoutY() + GameStatus.getFallingSpeed());
//            words[i].setRotate(words[i].getRotate() + 4);
        }
    }



    /** Game Listeners Start */
    private void checkPlayerScore() {
        if (playerScore != GameStatus.getPlayerScore()) {
            playerScore = GameStatus.getPlayerScore();
            playerScoreDisplay.setText(Integer.toString(playerScore));
        }
    }

    private void checkPlayerLives() {
        if (GameStatus.getPlayerLives() < 3 && hearts.getChildren().size() == 3) {
            hearts.getChildren().remove(hearts.getChildren().size() - 1);
        } else if (GameStatus.getPlayerLives() < 2 && hearts.getChildren().size() == 2) {
            hearts.getChildren().remove(hearts.getChildren().size() - 1);
        } else if (GameStatus.getPlayerLives() < 1 && hearts.getChildren().size() == 1) {
            gameTimer.stop();
            gamePane.getChildren().remove(hearts);
        }
    }

    private void checkDestroyedWords() {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).isDestroyed) {
                String songPath = this.getClass().getResource(AssetsPath.POINT_SOUND).toString();
                new AudioClip(songPath).play();
                GameStatus.addScore(words.get(i).getScoreValue());
                gamePane.getChildren().remove(words.get(i));
                words.remove(words.get(i));
                /** @todo ver possibilidade de resetar todas as palavras após uma ser destruída, para evitar que a ultima letra de uma palavra seja a primeira de outra e causar confusão*/
            }

            if (words.size() <= 0) {
                if (elementNumber < 2) elementNumber += 1;
                createGameWords(elementNumber);
            }
        }
    }

    private void checkIfElementHitTheGround() {
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getLayoutY() > x.getHeight()) {
                GameStatus.subtractOneLife();
                setNewElementPosition(words.get(i));
            }
        }
    }
    /** Game Listeners End */
}