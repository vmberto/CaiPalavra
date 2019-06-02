package score;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import utils.AssetsPath;
import utils.SoundHandler;
import utils.models.ScoreRowModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    private TableView<ScoreRowModel> scoreTable;
    @FXML
    private TableColumn<ScoreRowModel, String> positionCol;
    @FXML
    private TableColumn<ScoreRowModel, String> nameCol;
    @FXML
    private TableColumn<ScoreRowModel, Integer> scoreCol;

    private List<ScoreRowModel> scores = new ArrayList<>();

    private ObservableList<ScoreRowModel> obsScores;

    @FXML
    private Button backButton;

    @FXML
    private void backToMenuAction() throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/menu/menu.fxml"));
        Parent menuView = loader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(menuView);
        stage.show();

    }

    @FXML
    private void mouseHoverSound() {
        SoundHandler.playSound(AssetsPath.BUTTON_HOVER_SOUND);
    }


    public void loadScores() {
        ScoreRepository scoreRepository = new ScoreRepository();

        scoreRepository.getList().forEach((score) -> scores.add(new ScoreRowModel(score.getName(), score.getScore(), scoreRepository.getList().indexOf(score) + 1)));

        obsScores = FXCollections.observableArrayList(scores);

        this.scoreTable.setItems(obsScores);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        this.positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        this.loadScores();
    }


}
