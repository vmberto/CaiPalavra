import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import score.ScoreRepository;
import utils.models.PlayerScore;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/menu/menu.fxml"));
        Parent root = loader.load();

        primaryStage.setFullScreen(true);

        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

//        ------- CHANGE CURSOR -----------
//        Image image = new Image("resources/cursor.png");
          Scene scene = new Scene(root);
//        scene.setCursor(new ImageCursor(image));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
