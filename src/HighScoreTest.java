import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {

    @Test
    void getPlayerName() {
        // Arrange
        String playerName = "Josh";
        int score = 100;
        HighScore highScore = new HighScore(playerName, score);

        String retrievedName = highScore.getPlayerName();

        assertEquals(playerName, retrievedName, "Player name should match");
    }

    @Test
    void getScore() {
        String playerName = "Jake";
        int score = 150;
        HighScore highScore = new HighScore(playerName, score);

        int retrievedScore = highScore.getScore();

        assertEquals(score, retrievedScore, "Player score should match");
    }

    @Test
    void setPlayerName() {
        HighScore highScore = new HighScore("James", 200);

        highScore.setPlayerName("Joe");
        String updatedName = highScore.getPlayerName();

        assertEquals("Joe", updatedName, "Player name should be updated");
    }

    @Test
    void setScore() {
        // Arrange
        HighScore highScore = new HighScore("Jasmine", 250);

        highScore.setScore(300);
        int updatedScore = highScore.getScore();

        assertEquals(300, updatedScore, "Player score should be updated");
    }
}
