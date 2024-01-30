import java.util.Random;

public class Card {
    // Instance variables to store the rank and suit of the card.
    private int rank;
    private int suit;

    // Static arrays representing possible ranks and suits.
    private static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"};
    private static String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

    /*
    Default constructor that creates a random card by generating random indices for rank and suit.
     */
    public Card() {
        Random random = new Random();
        this.rank = random.nextInt(Card.ranks.length);
        this.suit = random.nextInt(Card.suits.length);
    }

    /*
    Parameterized constructor that creates a card with specified rank and suit.
     */
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /*
    Getter method to retrieve the rank of the card.
    Returns the rank as a string.
     */
    public String getRank() {
        return Card.ranks[this.rank];
    }

    /*
    Getter method to retrieve the suit of the card.
    Returns the suit as a string.
     */
    public String getSuit() {
        return Card.suits[this.suit];
    }

    /*
    Method to generate a string representation of the card.
    Returns a string in the format "Rank of Suit".
     */
    public String toString() {
        return getRank() + " of " + getSuit();
    }

}
