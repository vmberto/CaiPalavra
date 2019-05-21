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
import utils.AssetsPath;
import utils.MenuSong;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private ImageView gameLogo;

    @FXML
    private ImageView songToggleButton;

    @FXML
    private Button quitButton;

    @FXML
    private void startButtonAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../game/game.fxml"));
        Parent game = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        MenuSong.stop();

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

        if (MenuSong.isMenuSongDisabled()) {
            MenuSong.enableSong();
            Image songOn = new Image(AssetsPath.SOUND_ON_TOGGLE_IMAGE);
            this.songToggleButton.setImage(songOn);
        } else {
            MenuSong.disableSong();
            Image songOff = new Image(AssetsPath.SOUND_OFF_TOGGLE_IMAGE);
            this.songToggleButton.setImage(songOff);
        }

    }

    @FXML
    private void mouseHoverSound() {
        String songPath = this.getClass().getResource(AssetsPath.BUTTON_HOVER_SOUND).toString();
        AudioClip mouseHoverSound = new AudioClip(songPath);
        mouseHoverSound.play();
    }

    private void initSongToggleState() {

        if (MenuSong.isMenuSongDisabled()) {
            Image soundOff = new Image(AssetsPath.SOUND_OFF_TOGGLE_IMAGE);
            this.songToggleButton.setImage(soundOff);
        } else {
            Image soundOn = new Image(AssetsPath.SOUND_ON_TOGGLE_IMAGE);
            this.songToggleButton.setImage(soundOn);
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
        this.initSongToggleState();
        this.animateLogo();

        if (!MenuSong.isPlaying() && !MenuSong.isMenuSongDisabled()) {
            MenuSong.play();
        }

    }

}
