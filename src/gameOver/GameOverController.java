package gameOver;

import game.DificultyManager;
import game.GameStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import score.ScoreRepository;
import utils.AssetsPath;
import utils.SoundHandler;
import utils.models.PlayerScore;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {


    @FXML
    private BorderPane gameOverPane;

    @FXML
    private VBox verticalBox;

    @FXML
    private TextField playerNameInput;

    @FXML
    private PasswordField playerPasswordInput;

    @FXML
    private Button skipScoreButton;

    @FXML
    private Button saveScoreButton;

    @FXML
    private Text playerScoreDisplay;

    @FXML
    private Text notificationBox;

    @FXML
    private Text newRecordTitle;

    @FXML
    private Text newRecordSubtitle;

    ScoreRepository scoreRepository = new ScoreRepository();


    @FXML
    private void skipScoreButtonAction(ActionEvent event) throws Exception {
        returnToMenu();
    }

    @FXML
    private void saveScoreButtonAction(ActionEvent event) throws Exception {

        String name = playerNameInput.getText();
        String password = playerPasswordInput.getText();

        if (name.length() > 0 && password.length() > 0) {

            PlayerScore playerExists = scoreRepository.findPlayerScore(name);

            if (playerExists != null) {

                if (playerExists.getPassword().equals(password)) {

                    playerExists.setScore(GameStatus.getPlayerScore());

                } else {
                    notificationBox.setText("O usuário já existe e a senha informada está incorreta");
                    return;
                }


            } else {
                PlayerScore playerScore = new PlayerScore(name, GameStatus.getPlayerScore(), password);

                scoreRepository.add(playerScore);

            }

            scoreRepository.writeList();
            returnToMenu();

        } else {
            notificationBox.setText("Preencha os campos NOME e SENHA");
        }

    }

    @FXML
    private void mouseHoverSound() {
        SoundHandler.playSound(AssetsPath.BUTTON_HOVER_SOUND);
    }


    private void returnToMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../menu/menu.fxml"));
        Parent menu = loader.load();

        Stage stage = (Stage) gameOverPane.getScene().getWindow();

        stage.getScene().setRoot(menu);

        GameStatus.reset();
        DificultyManager.reset();
        stage.show();
    }

    public void initialize(URL url, ResourceBundle rb) {


        if (GameStatus.getPlayerScore() > scoreRepository.getList().get(0).getScore()) {

            gameOverPane.getStyleClass().add("new-record");
            SoundHandler.playSound(AssetsPath.NEW_RECORD);
            newRecordTitle.setVisible(true);
            newRecordSubtitle.setText("Você superou " + scoreRepository.getList().get(0).getName());
            newRecordSubtitle.setVisible(true);

        }



        this.playerScoreDisplay.setText(Integer.toString(GameStatus.getPlayerScore()));

    }

}
