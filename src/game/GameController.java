package game;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import java.util.*;
import java.util.List;

public class GameController implements Initializable {

    @FXML
    private AnchorPane gamePane;

    static Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();


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
    public void initialize(URL url, ResourceBundle rb) {
        gameStartCountdown();
    }

    private void gameStartCountdown() {
        gamePane.setCursor(Cursor.NONE);

        String beepPath = this.getClass().getResource(AssetsPath.COUNTDOWN_BEEP_SOUND).toString();
        String beepStartPath = this.getClass().getResource(AssetsPath.COUNTDOWN_BEEP_START_SOUND).toString();

        beep = new AudioClip(beepPath);
        beepStart = new AudioClip(beepStartPath);


        final IntegerProperty i = new SimpleIntegerProperty(3);
        gameCountdown = new Text(Integer.toString(i.get()));
        gameCountdown.setFill(Color.WHITE);
        gameCountdown.setStyle("-fx-font-family: 'Segoe UI Semibold'; -fx-font-size: 70;");
        gameCountdown.setLayoutX(dimensions.getWidth() / 2);
        gameCountdown.setLayoutY(dimensions.getHeight() / 2);
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

    /**
     * Game Engine
     */
    private void gameInitialized() {
        gamePane.getScene().setOnKeyTyped(e -> sendKeyboardInputs(e.getCharacter().charAt(0)));
        createPlayerLives();
        createPlayerScore();

        createWordSpawn();
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
        hearts = new HBox();
        Image heart = new Image(AssetsPath.HEART_CONTAINER_IMAGE);

        hearts.getChildren().addAll(new ImageView(heart), new ImageView(heart), new ImageView(heart));

        hearts.setLayoutX(dimensions.getWidth() - 250);
        hearts.setLayoutY(40);

        hearts.setAlignment(Pos.CENTER);

        gamePane.getChildren().add(hearts);

    }

    /**
     * Sends keyboard input chars for each words in the screen
     *
     * @param key
     */
    private void sendKeyboardInputs(char key) {
        for (int i = 0; i < words.size(); i++) {
            words.get(i).handleKey(key);
        }
    }


    private void createGameWords(int wordsNumber) {
        Random r = new Random();

        for (int i = 0; i < wordsNumber; i++) {
            Word newWord = new Word(GameStatus.wordsList[r.nextInt(GameStatus.wordsList.length)], DificultyManager.isWordRotating());
            words.add(newWord);
            setNewElementPosition(newWord, i);
            gamePane.getChildren().add(newWord);
        }
    }

    private void createWordSpawn() {
        Timeline spawn = new Timeline(
                new KeyFrame(
                        Duration.seconds(6.2),
                        event -> {
                            createGameWords(DificultyManager.getWordsSpawnNumber());
                        }
                )
        );
        spawn.setCycleCount(Animation.INDEFINITE);
        spawn.play();
    }

    /**
     * Sets a position based on the user's screen
     *
     * @param word
     */
    private void setNewElementPosition(Word word, int i) {
        Random r = new Random();

        int minX = (int) dimensions.getWidth() / 4;
        int maxX = (int) dimensions.getWidth() - ((int) dimensions.getWidth() / 4);
        word.setLayoutX(r.nextInt(maxX - minX) + minX);

        word.setLayoutY(-(i * 90));
    }


    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveGameElement();
                if (!GameStatus.isGameOver()) checkIfElementHitTheGround();
                if (!GameStatus.isGameOver()) checkPlayerScore();
                if (!GameStatus.isGameOver()) checkPlayerLives();
                if (!GameStatus.isGameOver()) checkDestroyedWords();
            }
        };
        gameTimer.start();
    }


    private void moveGameElement() {
        words.forEach(word -> {
            word.setLayoutY(word.getLayoutY() + DificultyManager.getFallingSpeed() + word.getFallingSpeedBonus());
            if (word.isRotating()) word.setRotate(word.getRotate() + 4);
        });
    }


    /**
     * Game Listeners Start
     */
    private void checkPlayerScore() {
        if (playerScore != GameStatus.getPlayerScore()) {
            playerScore = GameStatus.getPlayerScore();
            playerScoreDisplay.setText(Integer.toString(playerScore));
        }

        if (GameStatus.getPlayerScore() >= DificultyManager.getNextScoreFlag()) {
            DificultyManager.increaseFallingSpeed(0.23);
            DificultyManager.setNextScoreFlag();
        }


    }

    private void checkPlayerLives() {
        if (GameStatus.getPlayerLives() < 3 && hearts.getChildren().size() == 3) {
            hearts.getChildren().remove(hearts.getChildren().size() - 1);
        } else if (GameStatus.getPlayerLives() < 2 && hearts.getChildren().size() == 2) {
            hearts.getChildren().remove(hearts.getChildren().size() - 1);
        } else if (GameStatus.getPlayerLives() < 1 && hearts.getChildren().size() == 1) {
            GameStatus.setGameOver();
            gameOverHandler();
            gamePane.getChildren().remove(hearts);
        }
    }

    private void checkDestroyedWords() {
        words.forEach(word -> {

            if (word.isDestroyed) {

                String songPath = this.getClass().getResource(AssetsPath.POINT_SOUND).toString();
                new AudioClip(songPath).play();
                GameStatus.addScore(word.getScoreValue());

                gamePane.getChildren().remove(word);
                words.remove(word);
            }

            if (words.size() <= 0) {
                if (elementNumber < 2) elementNumber += 1;
                createGameWords(elementNumber);
            }
        });
    }

    /***
     * If word hits the ground, the player losts one life and the word return to the top
     * with a normal form and another TextWord
     */
    private void checkIfElementHitTheGround() {
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();

        words.forEach(word -> {
            if (word.getLayoutY() > x.getHeight()) {
                GameStatus.subtractOneLife();

                MenuSong.playSound(AssetsPath.WORD_DAMAGE_EXPLOSION_SOUND);

                Image explosion = new Image("/resources/explosion.gif");
                ImageView explosionGif = new ImageView(explosion);
                explosionGif.setLayoutX(word.getLayoutX());
                explosionGif.setLayoutY(word.getLayoutY() - 75);
                PauseTransition pause = new PauseTransition(Duration.seconds(.9));
                pause.setOnFinished(event -> gamePane.getChildren().remove(explosionGif));
                gamePane.getChildren().add(explosionGif);
                pause.play();


                word.setNormalPattern();
                setNewElementPosition(word, 0);
            }
        });

    }

    /**
     * Game Listeners End
     */

    public void gameOverHandler() {
        gamePane.getScene().setOnKeyTyped(null);
        GameSong.stop();
        Image gameOverImage = new Image("/resources/game-over-message.png");
        ImageView gameOver = new ImageView(gameOverImage);

        gameOver.setLayoutY(dimensions.getHeight() + 250);
        gameOver.setLayoutX(dimensions.getWidth() / 2 - 310);

        gamePane.getChildren().add(gameOver);

        AnimationTimer x = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameOver.setLayoutY(gameOver.getLayoutY() - 4.9);
                if (gameOver.getLayoutY() <= (dimensions.getHeight() / 2) - 250) stop();
            }
        };
        x.start();
    }

}