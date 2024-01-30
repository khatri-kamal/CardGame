public class Computer {
    private Card computerCard;

    public Computer(Deck deck) {
        this.computerCard = deck.deal();
    }
    public Card getComputerCard() {
        return computerCard;
    }
    public void resetComputerCard(Deck deck) {
        // Remove the computer's card
        computerCard = null;
        // Draw a new card from the deck to replace the removed card
        replaceCard(deck);
    }

    public void replaceCard(Deck deck){
        computerCard = deck.deal();
    }

    public void displayComputerCard() {
        System.out.println("Computer's Card");
        System.out.println("---------------");
        System.out.println(computerCard);
        System.out.println("---------------");
    }
}
