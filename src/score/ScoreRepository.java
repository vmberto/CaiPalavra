package score;

import utils.ScoreDatabase;
import utils.models.PlayerScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreRepository {


    /** FALTA ORDENAR A LISTA DE ACORDO COM A MAIOR PONTUAÇÃO */
    private List<PlayerScore> playerScores = new ArrayList<>();

    public ScoreRepository() { this.loadList(); }

    public List<PlayerScore> getList() {
        return playerScores;
    }

    public void add(PlayerScore playerScore) {
        this.playerScores.add(playerScore);
    }

    public void writeList() {

        Collections.sort (playerScores, new Comparator<PlayerScore>() {

            @Override
            public int compare(PlayerScore ps1, PlayerScore ps2) {
                return ps1.getScore() > ps2.getScore() ? -1 : (ps1.getScore() < ps2.getScore() ? +1 : 0);
            }

        });

        ScoreDatabase db = new ScoreDatabase();
        db.openToWrite("scoresArchive.dat");
        playerScores.forEach((playerScore) -> db.gravarDados(playerScore));
        db.closeWriteFile();

    }

    private void loadList() {
        ScoreDatabase db = new ScoreDatabase();
        PlayerScore playerScore;
        boolean exists = db.openToRead("scoresArchive.dat");
        if (exists) {
            playerScore = db.lerDados();
            while (playerScore != null) {
                playerScores.add(playerScore);
                playerScore = db.lerDados();
            }
            db.closeReadFile();
        }
    }

    public PlayerScore findPlayerScore(String name) {
        for(PlayerScore ps : this.playerScores) {
            if(ps.getName().equals(name)) {
                return ps;
            }
        }
        return null;
    }


}
