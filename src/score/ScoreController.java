package score;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    private TableView<Score> scoreTable;
    @FXML
    private TableColumn<Score, String> nameCol;
    @FXML
    private TableColumn<Score, Integer> scoreCol;

    private List<Score> scores = new ArrayList<>();

    private ObservableList<Score> obsScores;


    @FXML
    private ImageView backButton;

    @FXML
    private void backToMenuAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../menu/menu.fxml"));
        Parent game = loader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(game);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        this.loadScores();
    }

    public void loadScores() {

        Score s1 = new Score("Alberto", 480);
        Score s2 = new Score("João", 320);

        scores.add(s1);
        scores.add(s2);

        obsScores = FXCollections.observableArrayList(scores);

        this.scoreTable.setItems(obsScores);

    }

    public static class Score {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty score;

        public Score(String name, Integer score) {
            this.name = new SimpleStringProperty(name);
            this.score = new SimpleIntegerProperty(score);
        }

        public String getName() {
            return name.get();
        }

        public int getScore() {
            return score.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public void setScore(int score) {
            this.score.set(score);
        }

    }

}
