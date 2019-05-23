package utils.models;

import java.io.Serializable;

public class PlayerScore implements Serializable {

    private String name;
    private String password;
    private int score;

    public PlayerScore(String name, int score, String password) {
        this.name = name;
        this.score = score;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() { return password; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) { this.score = score; }

}