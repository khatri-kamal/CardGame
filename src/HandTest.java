import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void getHandSize() {
        // Create a deck and a hand
        Deck deck = new Deck();
        Hand hand = new Hand(deck);

        // Assert that the hand size matches the expected size
        assertEquals(Hand.getHandSize(), hand.getHandSize());
    }

    @Test
    void replaceCard() {
        // Create a deck and a hand
        Deck deck = new Deck();
        Hand hand = new Hand(deck);

        // Get the initial card at index 0
        Card initialCard = hand.getCard(0);

        // Replace the card at index 0
        hand.replaceCard(initialCard, deck);

        // Get the new card at index 0
        Card newCard = hand.getCard(0);

        // Assert that the new card is not equal to the initial card
        assertNotEquals(initialCard, newCard);
    }

    @Test
    void makeEleven() {
        // Create cards for testing index of rank and suit arrays
        Card playerCard = new Card(12, 1);  // Get Ace of Clubs
        Card computerCard = new Card(11, 2);  // Get King of Hearts

        // Create a hand and call makeEleven and check if the player card and computer make 11
        Hand hand = new Hand(new Deck());
        boolean result = hand.makeEleven(playerCard, computerCard);

        // Assert that the result matches the expected outcome
        assertTrue(result);
    }

    // There is issues with this test as it gets a random card meaning test are random as well.
    @Test
    void matchSuit() {
        // Create specific cards for testing
        Card aceOfHearts1 = new Card(12, 2); // Ace of Hearts
        Card aceOfHearts2 = new Card(12, 2); // Another Ace of Hearts

        // Create a hand with specific cards
        Hand hand = new Hand(new Deck());

        // Call matchSuit for the index 0
        boolean result = hand.matchSuit(aceOfHearts2, 0);

        // Assert that the result matches the expected outcome
        assertTrue(result);
    }


   public void printFaceCards() {
        // Testing printing, no direct assertions possible
        Hand hand = new Hand(new Deck());
        hand.printFaceCards();
    }

    @Test
    void getCard() {
        // Create a hand
        Hand hand = new Hand(new Deck());

        // Get the card at index 2
        Card card = hand.getCard(2);

        // Assert that the card is not null
        assertNotNull(card);
    }

    public static void main(String[] args) {
        HandTest handSet = new HandTest();
        handSet.printFaceCards();
    }
}
