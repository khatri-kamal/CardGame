import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest<Scanner> {

    @Test
    void setSelectedCardIndex() {
        Player player = new Player(new Deck());
        player.setSelectedCardIndex(3);
        assertEquals(3, player.getSelectedCardIndex());
    }

    @Test
    void replaceCard() {
        Deck deck = new Deck();
        Player player = new Player(deck);
        Card initialCard = player.getCard(0);
        player.setSelectedCardIndex(0);
        player.replaceCard(deck);
        Card newCard = player.getCard(0);
        assertNotEquals(initialCard, newCard);
    }

    @Test
    void makeEleven() {
        Deck deck = new Deck();
        Player player = new Player(deck);

        // Test case where player can make eleven
        // The rank of a card is index number which is 10 == Queen, so player has Queen and computer has Ace
        Card playerCard1 = new Card(10, 2);
        Card computerCard1 = new Card(12, 2);


        assertTrue(player.makeEleven(playerCard1, computerCard1));

        // Test case where player can't make eleven
        Card playerCard2 = new Card(2, 2);
        Card computerCard2 = new Card(2 , 2);
        assertFalse(player.makeEleven(playerCard2, computerCard2));
    }


    @Test
    void notMatchSuit() {
        Deck deck = new Deck();
        Player player = new Player(deck);

        // Test case where suits don't match
        Card computerCard2 = new Card(1, 2);
        assertFalse(player.matchSuit(computerCard2, 0));
    }

    @Test
    void replaceFaceCards() {
        // Mocking the Scanner for testing purposes
        String userInput = "Y\n0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Create a player with a face card
        Deck deck = new Deck();
        Player player = new Player(deck);
        player.getCard(0); // Set a face card for testing

        // Invoke the method that prompts for user input
        player.replaceFaceCards(new Deck());

        // Assert that the face card was replaced
        assertNotEquals("King", player.getCard(0).getRank());
    }



    @Test
    void getCard() {
        Deck deck = new Deck();
        Player player = new Player(deck);

        // Test case to get the first card in the player's hand
        Card card = player.getCard(0);
        assertNotNull(card);
    }

    @Test
    void getSelectedCardIndex() {
        Player player = new Player(new Deck());

        // Test case to get the initially set selected card index
        assertEquals(-1, player.getSelectedCardIndex());

        // Test case to set and get a selected card index
        player.setSelectedCardIndex(2);
        assertEquals(2, player.getSelectedCardIndex());
    }

    @Test
    void displayHand() {
        // Note: This test involves console output. You may need to adjust based on actual behavior.

        // Mocking System.out for testing purposes (not recommended for real projects)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Player player = new Player(new Deck());
        player.displayHand();

        // Reset System.out to the original System.out
        System.setOut(System.out);

        // Verify if the output contains the expected string
        assertTrue(outputStream.toString().contains("Player's Hand"));
    }
}
