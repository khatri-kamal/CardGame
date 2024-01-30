public class Hand {
    private static final int HANDSIZE = 5;
    private Card[] hand = new Card[Hand.HANDSIZE];

    public Hand(Deck deck) {
        initializeHand(deck);
    }
    public static int getHandSize() {
        return HANDSIZE;
    }

    // setting up the player hand with 5 cards
    private void initializeHand(Deck deck) {
        for (int i = 0; i < HANDSIZE; i++) {
            hand[i] = deck.deal();
        }
    }

    // Method used to replace the card from player hand.
    public void replaceCard(Card cardToReplace, Deck deck) {
        for (int i = 0; i < HANDSIZE; i++) {
            if (hand[i].equals(cardToReplace)) {
                hand[i] = deck.deal();
                return; // Break out of the loop after replacing the selected card
            }
        }
        System.out.println("Selected card not found in the hand.");
    }

    /*
     Method used to check if player can make with his card and computer card.
     Returns ture if player can make 11
     */
    public boolean makeEleven(Card playerCard, Card computerCard) {
        int playerValue = calculateCardValue(playerCard);
        int computerValue = calculateCardValue(computerCard);

        // Check if the sum of values is equal to 11
        return (playerValue + computerValue) == 11;
    }

    /*
    Method to check if player can match suit with the card selected and returns true if
    player can match suits.
     */
    public boolean matchSuit(Card computerCard, int selectedCardIndex) {
        if (selectedCardIndex >= 0 && selectedCardIndex < HANDSIZE) {
            Card selectedCard = hand[selectedCardIndex];
            return selectedCard.getSuit().equals(computerCard.getSuit());
        } else {
            return false;
        }
    }

    /*
    Method to check if player has face card in his deck, this
    method is used when giving options to player to replace face card
     */
    private boolean isFaceCard(Card card) {
        String rank = card.getRank();
        return rank.equals("Jack") || rank.equals("Queen") || rank.equals("King");
    }

    /*
    Prints all the face card in player hand. When player wants to replace card then this will be used to
    show player which cards are face card.
     */
    public void printFaceCards() {
        System.out.println("Face cards in hand:");

        for (int i = 0; i < HANDSIZE; i++) {
            Card currentCard = hand[i];

            if (isFaceCard(currentCard)) {
                System.out.println("Index " + i + ": " + currentCard);
            }
        }
    }

    /*
    Gets the card in players hand.
     */
    public Card getCard(int index) {
        if (index >= 0 && index < HANDSIZE) {
            return hand[index];
        } else {
            return null;
        }
    }

    /*
    Method returns string of players hand.
     */
    public String getHandAsString() {
        StringBuilder result = new StringBuilder();
        for (Card card : hand) {
            result.append(card).append("\n");
        }
        return result.toString();
    }

    /*
    Method to calculate the face cards value which returns int.
    Used to determin if player can make 11 or not with face card
     */
    private int calculateCardValue(Card card) {
        String rank = card.getRank();
        if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
            return 10;
        } else if (rank.equals("Ace")) {
            return 1;
        } else {
            return Integer.parseInt(rank);
        }
    }

    /*
    Method to display player hand and number indecating which number to input for which card for player
     */
    public void displayHand() {
        int countCard = 1;
        System.out.println("Player's Hand");
        System.out.println("--------------");
        for (Card card : hand) {
            System.out.println( countCard + " : " + card);
            countCard++;
        }
    }
}
