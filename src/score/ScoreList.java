package score;

import utils.ScoreDatabase;
import utils.models.Score;
import utils.models.ScoreRowModel;

import java.util.ArrayList;
import java.util.List;

public class ScoreList {

    /** FALTA ORDENAR A LISTA DE ACORDO COM A MAIOR PONTUAÇÃO */
    private List<Score> scores = new ArrayList<>();

    public List<Score> getList() {
        return scores;
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public void writeList() {
        ScoreDatabase db = new ScoreDatabase();
        db.openToWrite("scoresArchive.dat");
        scores.forEach((score) -> db.gravarDados(score));
        db.closeWriteFile();

    }

    public void loadList() {
        ScoreDatabase db = new ScoreDatabase();
        Score score;
        boolean exists = db.openToRead("scoresArchive.dat");
        if (exists) {
            score = db.lerDados();
            while (score != null) {
                scores.add(score);
                score = db.lerDados();
            }
            db.closeReadFile();
        }
    }


}
