package game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private BorderPane gamePane;
    @FXML
    private Text gameCountdown;

    private int counter;
    private Timer startCountdown;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        counter = Integer.parseInt(gameCountdown.getText());

        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counter -= 1;
                gameCountdown.setText(Integer.toString(counter));
                if (counter == 0) {
                    startCountdown.stop();
                }
            }
        };

        startCountdown = new Timer(1000, action);

        startCountdown.start();






    }
}