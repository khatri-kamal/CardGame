import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void getRank() {
        // Testing the getRank method to ensure it returns a non-null value.
        Card card = new Card();
        assertNotNull(card.getRank());
    }

    @Test
    public void getSuit() {
        // Testing the getSuit method to ensure it returns a non-null value.
        Card card = new Card();
        assertNotNull(card.getSuit());
    }

    @Test
    public void testToString1() {
        // Testing the toString method for default constructor.
        // The toString method should return a non-null string containing the rank and suit.
        Card card = new Card();
        // Use assertEquals to check the content of the returned string.
        assertEquals(card.getRank() + " of " + card.getSuit(), card.toString());
    }
}
