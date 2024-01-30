public class HighScore {
    private String playerName;
    private int score;

    public HighScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    // Getter for playerName
    public String getPlayerName() {
        return playerName;
    }

    // Setter for playerName
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Getter for score
    public int getScore() {
        return score;
    }

    // Setter for score
    public void setScore(int score) {
        this.score = score;
    }
}
