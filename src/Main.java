import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import score.ScoreList;
import utils.ScoreDatabase;
import utils.models.Score;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("menu/menu.fxml"));
        Parent root = loader.load();

        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

//        ------- CHANGE CURSOR -----------
//        Image image = new Image("resources/cursor.png");
//        Scene scene = new Scene(root);
//        scene.setCursor(new ImageCursor(image));


        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    /** WRITE ON LIST METHOD */
    public void writeList() {

        Score s1 = new Score("Alberto", 480);
        Score s2 = new Score("Flavio", 1234);


        ScoreList sl = new ScoreList();

        sl.loadList();

        sl.add(s1);
        sl.add(s2);

        sl.writeList();


    }

}
