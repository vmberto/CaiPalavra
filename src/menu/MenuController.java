package menu;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private AudioClip menuSong;

    @FXML
    private ImageView gameLogo;

    @FXML
    private ImageView songToggleButton;

    @FXML
    private Button scoreButton;
    @FXML
    private Button quitButton;


    @FXML
    private void startButtonAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../game/game.fxml"));
        Parent game = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        this.menuSong.stop();

        stage.getScene().setRoot(game);

        stage.show();
    }

    @FXML
    private void scoreButtonAction(ActionEvent event) throws Exception  {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../score/score.fxml"));
        Parent game = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.getScene().setRoot(game);

        stage.show();
    }

    @FXML
    private void quitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void toggleSong() {

        if (this.menuSong.isPlaying()) {
            this.menuSong.stop();
            Image songOff = new Image("/resources/sound-off.png");
            this.songToggleButton.setImage(songOff);
        } else {
            this.menuSong.play();
            Image songOn = new Image("/resources/sound-on.png");
            this.songToggleButton.setImage(songOn);
        }

    }

    private void animateLogo() {
        Duration d = Duration.millis(2000);
        RotateTransition rt = new RotateTransition(d, gameLogo);
        rt.setFromAngle(-9);
        rt.setToAngle(9);
        rt.setAxis(Rotate.Z_AXIS);
        rt.setAutoReverse(true);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.menuSong = new AudioClip(this.getClass().getResource("/resources/menu-song.mp3").toString());
        this.animateLogo();
        this.menuSong.play();
    }

}
