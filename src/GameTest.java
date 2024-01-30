import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Deck deck;
    private HighScore highScore;

     GameTest(){
    this.deck = new Deck() ;
    }


    @Test
    void askForReplay() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Test askForReplay method using reflection

        // Create a game instance
        Game game = new Game();

        // Set up the game instance with a mock roundDetailsList
        game.roundDetailsList.add(new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0));

        // Create an InputStream with user input for replay (Y)
        InputStream inputStreamYes = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(inputStreamYes);

        // Use reflection to invoke the private method
        Method askForReplayMethod = Game.class.getDeclaredMethod("askForReplay");
        askForReplayMethod.setAccessible(true);

        assertTrue((boolean) askForReplayMethod.invoke(game)); // User wants to see round details

        // Create an InputStream with user input for no replay (N)
        InputStream inputStreamNo = new ByteArrayInputStream("N\n".getBytes());
        System.setIn(inputStreamNo);

        assertFalse((boolean) askForReplayMethod.invoke(game)); // User does not want to see round details
    }
    @Test
    void displayHighScores() {
        // Create a game instance
        Game game = new Game();

        // Add high scores to the game
        game.highScores.add(new HighScore("Player1", 15));
        game.highScores.add(new HighScore("Player2", 10));
        game.highScores.add(new HighScore("Player3", 5));


        // Redirect standard output to capture printed values
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the displayHighScores method
        game.displayHighScores();

        // Reset standard output
        System.setOut(originalOut);

        // Check if the output contains expected values
        String expectedOutput = "High Scores:\n" +
                "--------------\n" +
                "Player1: 15\n" +
                "Player2: 10\n" +
                "Player3: 5\n";

        String actualOutput = outputStream.toString().replaceAll("\r", ""); // Remove carriage return characters

        // Debugging purposes.
        System.out.println("Expected Output:");
        System.out.println(expectedOutput);
        System.out.println("Actual Output:");

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

}
