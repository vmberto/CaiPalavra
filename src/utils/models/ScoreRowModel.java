package utils.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ScoreRowModel {
    private final SimpleIntegerProperty position;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty score;

    public ScoreRowModel(String name, Integer score, Integer position) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
        this.position = new SimpleIntegerProperty(position);
    }

    public String getName() {
        return name.get();
    }

    public int getScore() {
        return score.get();
    }

    public int getPosition() {
        return position.get();
    }


}