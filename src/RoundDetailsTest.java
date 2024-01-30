import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoundDetailsTest {

    // Testing the getRoundNumber method
    @Test
    void getRoundNumber() {
        // Create a RoundDetails instance with a specific round number
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        // Ensure that the getRoundNumber method returns the correct round number
        assertEquals(1, roundDetails.getRoundNumber());
    }

    // Testing the setRoundNumber method
    @Test
    void setRoundNumber() {
        // Create a RoundDetails instance with an initial round number
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        // Set a new round number using setRoundNumber method
        roundDetails.setRoundNumber(2);
        // Ensure that the getRoundNumber method returns the updated round number
        assertEquals(2, roundDetails.getRoundNumber());
    }

    // Testing the getComputerCard method
    @Test
    void getComputerCard() {
        // Create a RoundDetails instance with a specific computer card
        Card computerCard = new Card(2, 1);
        RoundDetails roundDetails = new RoundDetails(1, computerCard, "PlayerHand", null, "Outcome", 0);
        // Ensure that the getComputerCard method returns the correct computer card
        assertEquals(computerCard, roundDetails.getComputerCard());
    }

    // Testing the setComputerCard method
    @Test
    void setComputerCard() {
        // Create a RoundDetails instance with an initial computer card
        Card computerCard = new Card(10, 2);
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        // Set a new computer card using setComputerCard method
        roundDetails.setComputerCard(computerCard);
        // Ensure that the getComputerCard method returns the updated computer card
        assertEquals(computerCard, roundDetails.getComputerCard());
    }

    // Testing the getSelectedCard method
    @Test
    void getSelectedCard() {
        Card selectedCard = new Card(7, 3);
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", selectedCard, "Outcome", 0);
        assertEquals(selectedCard, roundDetails.getSelectedCard());
    }

    // Testing the setSelectedCard method
    @Test
    void setSelectedCard() {
        Card selectedCard = new Card(7, 3);
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        roundDetails.setSelectedCard(selectedCard);
        assertEquals(selectedCard, roundDetails.getSelectedCard());
    }

    // Testing the getPlayerHand method
    @Test
    void getPlayerHand() {
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        assertEquals("PlayerHand", roundDetails.getPlayerHand());
    }

    // Testing the setPlayerHand method
    @Test
    void setPlayerHand() {
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        roundDetails.setPlayerHand("NewPlayerHand");
        assertEquals("NewPlayerHand", roundDetails.getPlayerHand());
    }

    // Testing the getOutCome method
    @Test
    void getOutCome() {
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        assertEquals("Outcome", roundDetails.getOutCome());
    }

    // Testing the setOutCome method
    @Test
    void setOutCome() {
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 0);
        roundDetails.setOutCome("NewOutcome");
        assertEquals("NewOutcome", roundDetails.getOutCome());
    }

    // Testing the getScore method
    @Test
    void getScore() {
        RoundDetails roundDetails = new RoundDetails(1, null, "PlayerHand", null, "Outcome", 42);
        assertEquals(42, roundDetails.getScore());
    }
}
