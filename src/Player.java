
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private Hand playerHand;
    private int selectedCardIndex;

    public Player(Deck deck) {
        this.playerHand = new Hand(deck);
        this.selectedCardIndex = -1;
    }


    public String getHandAsString() {
        return playerHand.getHandAsString();
    }

    public Card getCard(int index) {
        return playerHand.getCard(index);
    }

    public int getSelectedCardIndex() {
        return selectedCardIndex;
    }

    public void displayHand() {
        playerHand.displayHand();
    }
    /*
       Method to check which card player selected.
        */
    public void setSelectedCardIndex(int index) {
        this.selectedCardIndex = index;
    }
    /*
    MEthod to replace card with the selected card.
     */
    public void replaceCard(Deck deck) {
        Card selectedCard = playerHand.getCard(selectedCardIndex);
        playerHand.replaceCard(selectedCard, deck);
    }

    // Method that returns ture of player can make 11 with player card
    public boolean makeEleven(Card playerCard, Card computerCard) {
        return playerHand.makeEleven(playerCard, computerCard);
    }

    // Method that returns true of player can match suit.
    public boolean matchSuit(Card computerCard, int selectedCardIndex) {
        return playerHand.matchSuit(computerCard, selectedCardIndex);
    }

    // Method that returns if rank is face card or not.
    private boolean isFaceCard(Card card) {
        String rank = card.getRank();
        return rank.equals("Jack") || rank.equals("Queen") || rank.equals("King");
    }

    /*
    Method to change player face card if they have any otherwise just prints a message.
     */
    public void replaceFaceCards(Deck deck) {
        Scanner scanner = new Scanner(System.in);

        // Check if the player has any face cards in their hand.
        boolean hasFaceCard = false;

        for (int i = 0; i < Hand.getHandSize(); i++) {
            Card currentCard = playerHand.getCard(i);
            if (isFaceCard(currentCard)) {
                hasFaceCard = true;
                break;
            }
        }
        if (hasFaceCard) {
            boolean validInput = false;
            String response = "";

            while (!validInput) {
                try {
                    System.out.println("You have a face card in your hand. Do you want to replace it? (Y/N): ");
                    response = scanner.next();

                    if (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N")) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    // Clear the invalid input from the scanner
                    scanner.next();
                }
            }

            if (response.equalsIgnoreCase("Y")) {
                playerHand.printFaceCards();

                validInput = false;
                int selectedIndex = -1;
                while (!validInput) {
                    System.out.println("Select the index of the face card you want to replace: ");

                    try {
                        selectedIndex = scanner.nextInt();

                        if (selectedIndex >= 0 && selectedIndex < Hand.getHandSize()) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid index selected. Please choose a valid index.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer for the index.");
                        // Clear the invalid input from the scanner
                        scanner.next();
                    }
                }
                Card selectedCard = playerHand.getCard(selectedIndex);
                if (isFaceCard(selectedCard)) {
                    System.out.println("You selected a face card: " + selectedCard);

                    // Replace the face card with the new card.
                    playerHand.replaceCard(selectedCard, deck);

                    // Print the face card begin replace and with the card begin replaced one from the deck.
                    System.out.println("Face card: " + selectedCard + " replaced with: " + getCard(0));
                } else {
                    System.out.println("Selected card is not a face card. No replacement needed.");
                }
            } else {
                System.out.println("No face card replacement chosen.");
            }
        } else {
            System.out.println("You don't have any face cards in your hand.");
        }
    }
}
