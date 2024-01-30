import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {
//
@Test
void getComputerCard() {
    // Create a real deck and a computer instance
    Deck deck = new Deck();
    Computer computer = new Computer(deck);

    // Test if the computer card is not null
    Card computerCard = computer.getComputerCard();
    assertNotNull(computerCard);
}

    @Test
    void resetComputerCard() {
        // Create a real deck and a computer instance
        Deck deck = new Deck();
        Computer computer = new Computer(deck);

        // Save the current computer card
        Card currentCard = computer.getComputerCard();

        // Call resetComputerCard, which should replace the computer card
        computer.resetComputerCard(deck);

        // Verify that the new computer card is different from the previous one
        assertNotEquals(currentCard, computer.getComputerCard());
    }

    @Test
    void replaceCard() {
        // Create a real deck and a computer instance
        Deck deck = new Deck();
        Computer computer = new Computer(deck);

        // Save the current computer card
        Card currentCard = computer.getComputerCard();

        // Call replaceCard and check if the computer card is replaced
        computer.replaceCard(deck);
        assertNotEquals(currentCard, computer.getComputerCard());
    }

    @Test
    void displayComputerCard() {
        // Create a real deck and a computer instance
        Deck deck = new Deck();
        Computer computer = new Computer(deck);

        // Capture the system output for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call displayComputerCard and check if the output contains the expected string
        computer.displayComputerCard();
        assertTrue(outputStream.toString().contains(computer.getComputerCard().toString()));

        // Reset System.out to the original System.out
        System.setOut(System.out);
    }
}
