package utils.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ScoreRowModel {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty score;

    public ScoreRowModel(String name, Integer score) {
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