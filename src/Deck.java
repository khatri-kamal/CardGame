import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    // ArrayList to store the cards in the deck.
    private ArrayList<Card> deck = new ArrayList<Card>();

    /*
    Constructor to initialize the deck by adding 52 cards and shuffling them.
     */
    public Deck() {
        for (int i = 0; i < 52; i++) {
            // Create a new Card and add it to the deck based on the current index.
            this.deck.add(new Card(i % 13, i / 13));
        }
        // Shuffle the deck to randomize the order of cards.
        Collections.shuffle(this.deck);
    }

    /*
    Method to deal a card from the deck.
    Returns null if the deck is empty.
     */
    public Card deal() {
        if (this.deck.size() > 0) {
            // Remove and return the first card from the deck.
            return this.deck.remove(0);
        } else {
            // Return null if the deck is empty.
            return null;
        }
    }

    /*
    Method to check if the deck has any cards left.
     */
    public boolean hasCards() {
        // Returns true if the deck is not empty.
        return !this.deck.isEmpty();
    }

    /*
    Method to generate a string representation of the deck.
    Each card is printed on a new line.
     */
    public String toString() {
        String resultStr = "\n";
        for (Card card : deck) {
            // Concatenate each card's string representation to the result string.
            resultStr += card + "\n";
        }
        return resultStr;
    }

    /*
    Method to count the total number of cards in the deck.
    Prints the count to the console and returns the count.
     */
    public int countCard() {
        int totalCard = 0;
        for (Card card : deck) {
            // Increment the count for each card in the deck.
            totalCard++;
        }
        // Print the count to the console.
        System.out.println(totalCard);
        // Return the total count.
        return totalCard;
    }
}
