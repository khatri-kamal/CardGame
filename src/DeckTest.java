import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @org.junit.jupiter.api.Test
    void deal() {
        // Testing the deal method to ensure it returns a card and decreases the deck size.
        Deck deck = new Deck();
        int initialSize = deck.countCard();
        Card dealtCard = deck.deal();
        assertNotNull(dealtCard);
        assertEquals(initialSize - 1, deck.countCard());
    }

    @org.junit.jupiter.api.Test
    void hasCards() {
        // Testing the hasCards method for a new deck.
        Deck deck = new Deck();
        assertTrue(deck.hasCards());
        // Deplete the deck and check again.
        while (deck.hasCards()) {
            deck.deal();
        }
        assertFalse(deck.hasCards());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        // Testing the toString method to ensure it returns a non-null string.
        Deck deck = new Deck();
        assertNotNull(deck.toString());
    }

    @org.junit.jupiter.api.Test
    void countCard() {
        // Testing the countCard method to ensure it returns the correct number of cards in the deck.
        Deck deck = new Deck();
        int initialSize = deck.countCard();
        assertEquals(initialSize, deck.countCard());
        // Deplete the deck and check again.
        while (deck.hasCards()) {
            deck.deal();
        }
        assertEquals(0, deck.countCard());
    }

}
