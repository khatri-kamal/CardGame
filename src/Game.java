import java.io.*;
import java.util.*;

public class Game {
    private Deck deck;
    private Player player;
    private Computer computer;
    private int score;
    private int roundNumber;
    LinkedList<HighScore> highScores;
    List<RoundDetails> roundDetailsList;
    private boolean replayGame;

    public Game() {
        this.deck = new Deck();
        this.player = new Player(deck);
        this.computer = new Computer(deck);
        this.score = 0;
        this.highScores = new LinkedList<>();
        this.roundNumber = 1; // Initialize round number
        this.roundDetailsList = new ArrayList<>();
        this.replayGame = false;
    }



    public void play() {
        System.out.println("---------------");
        System.out.println("Make 11 ");
        System.out.println("---------------");
        loadHighScores();
        displayHighScores();
        System.out.println("---------------");
        System.out.println("Make 11 to Score a point. Match suit to continue playing.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display round number
            System.out.println("---------------");
            System.out.println("Round " + roundNumber);
            System.out.println("---------------");

            // Display player's hand and computer's card
            computer.displayComputerCard();
            player.displayHand();

            // Replace a card in the player's hand
            System.out.println("Choose a card to make 11 (1-5): ");
            int cardIndex;

            try {
                cardIndex = scanner.nextInt();
                // Validate the input range
                if (cardIndex < 1 || cardIndex > 5) {
                    System.out.println("Invalid input. Please choose a card between 1 and 5.");
                    continue;
                }

                player.setSelectedCardIndex(cardIndex - 1);

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
                continue;
            }

            // Check if the player can make 11 with the computer's card
            Card computerCard = computer.getComputerCard();
            boolean canMakeEleven = player.makeEleven(player.getCard(player.getSelectedCardIndex()), computerCard);

            // Check if the card matches the suit
            boolean hasMatchingSuit = player.matchSuit(computerCard, player.getSelectedCardIndex());

            // Determine the outcome using the determineOutcome method


            // String to save outCome as well
            String outCome = "";
            // Scoring logic
            if (canMakeEleven && hasMatchingSuit) {
                System.out.println("You made 11 and matched the suit! Score + 2");
                score += 2;
                outCome = "Player made 11 and matched the suit! Sore 2 point";
                saveRoundDetails(computerCard, player.getHandAsString(), player.getCard(player.getSelectedCardIndex()), outCome, score);
                player.replaceFaceCards(deck);
            } else if (canMakeEleven) {
                System.out.println("You made 11! Score + 1");
                score++;
                outCome = "Player made 11! Scored 1 point ";
                saveRoundDetails(computerCard, player.getHandAsString(), player.getCard(player.getSelectedCardIndex()), outCome, score);
                player.replaceFaceCards(deck);
            } else if (hasMatchingSuit) {
                System.out.println("Suit Matched. No point Given");
                outCome = "Player matched suit! Scored 0 point";
                saveRoundDetails(computerCard, player.getHandAsString(), player.getCard(player.getSelectedCardIndex()), outCome, score);
            } else {
                System.out.println("Player couldn't make 11 or match suit.");
                System.out.println("Game Over!");
                outCome = "Player couldn't make 11 or match suit. Game Over";
                saveRoundDetails(computerCard, player.getHandAsString(), player.getCard(player.getSelectedCardIndex()), outCome, score);
                askForReplay();
                break;  // End the game if conditions are not met

            }
            // Increment round number
            roundNumber++;

            // Display score and remaining cards
            System.out.println("Score: " + score);
            deck.countCard();

            // Reset player and computer hands
            computer.resetComputerCard(deck);
            // Replace player's card.
            player.replaceCard(deck);

            // Save score to file
            saveHighScores();

            // Check if the game should continue
            if (!deck.hasCards()) {
                System.out.println("No more cards in the deck. Game over. Score: " + score);
                replayGame = askForReplay();

                if (replayGame) {
                    // If the player wants to replay, reset the game state
                    resetGameState();
                } else {
                    break; // Exit the loop if the player doesn't want to replay
                }
            }
        }
        // Update high scores after the game ends
        updateHighScores();
        displayHighScores();

        scanner.close();
    }

    private void addNewHighScore() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Congratulations! You've scored a new high score!");
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();

            HighScore newHighScore = new HighScore(playerName, score);
            highScores.add(newHighScore);
            System.out.println("High score added!");
            saveHighScores();
        } catch (InputMismatchException e) {
            // Handle the exception (e.g., print an error message)
            System.out.println("Invalid input. Please enter a valid name.");
        } finally {
            // Close the scanner in the finally block to avoid resource leaks
            scanner.close();
        }
    }
    /*
         Method to check, store and remove new high score
         */
    private void updateHighScores() {
        // Check if player has a new high score
        if (score > 0) {
            if (highScores.size() < 5) {
                // If there are fewer than 5 high scores, add the new score
                addNewHighScore();
            } else {
                // Check if the new score is higher than or equal to the lowest current high score
                HighScore lowestScore = findLowestScore();

                if (score >= lowestScore.getScore()) {
                    // Remove the lowest score and add the new score
                    highScores.remove(lowestScore);
                    addNewHighScore();
                }

            }
        }
    }

    private void loadHighScores() {
        highScores = new LinkedList<>();
        try (Scanner fileScanner = new Scanner(new File("highscore.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":");
                String playerName = parts[0].trim();
                int playerScore = Integer.parseInt(parts[1].trim());
                highScores.add(new HighScore(playerName, playerScore));
            }
        } catch (FileNotFoundException e) {
            System.out.println("High score file not found. Creating a new one.");
        }

        // Sort high scores after loading
        sortHighScores();
    }

    // Add this method to save high scores to file
    private void saveHighScores() {
        // Sort high scores before saving
        sortHighScores();

        try (PrintWriter writer = new PrintWriter(new FileWriter("highscore.txt"))) {
            for (HighScore score : highScores) {
                writer.println(score.getPlayerName() + ": " + score.getScore());
            }
        } catch (IOException e) {
            System.out.println("Error saving high scores: " + e.getMessage());
        }
    }


    // Method to sort the highscore.
    private void sortHighScores() {
        highScores.sort(Comparator.comparingInt(HighScore::getScore).reversed());
    }

    private HighScore findLowestScore() {
        HighScore lowestScore = highScores.get(0);

        for (HighScore score : highScores) {
            if (score.getScore() < lowestScore.getScore()) {
                lowestScore = score;
            }
        }
        return lowestScore;
    }


    public void displayHighScores() {
        System.out.println("High Scores:");
        System.out.println("--------------");
        for (HighScore highScore : highScores) {
            System.out.println(highScore.getPlayerName() + ": " + highScore.getScore());
        }
    }

    /*
    printLowestScorePlayer method was used to debug the code and check when sorting that the player who has
    lowest score is displayed and compare when sorting feature was begin developed.
     */

    private void saveRoundDetails(Card computerCard, String playerHand, Card selectedCard, String outCome, int score) {
        // Create a new RoundDetails object with the provided parameters
        RoundDetails roundDetails = new RoundDetails(roundNumber, computerCard, playerHand, selectedCard, outCome, score);

        // Set the outcome for the round
        roundDetails.setOutCome(outCome);

        // Add the RoundDetails object to the roundDetailsList
        roundDetailsList.add(roundDetails);
    }
    private void printRoundDetails(RoundDetails roundDetails) {
        // Print the details to the console using getter methods
//        System.out.println("--------------");
        System.out.println("Round " + roundDetails.getRoundNumber());
        System.out.println("--------------");
        System.out.println("Computer's Card:");
        System.out.println("--------------");
        System.out.println(roundDetails.getComputerCard());
        System.out.println("--------------");
        System.out.println("Player's Hand:");
        System.out.println("--------------");
        System.out.print(roundDetails.getPlayerHand());
        System.out.println("--------------");
        System.out.println("Selected Card:");
        System.out.println("--------------");
        System.out.println(roundDetails.getSelectedCard());
        System.out.println("--------------");
        System.out.println("Outcome:");
        System.out.println("---------------");
        System.out.println(roundDetails.getOutCome());
        System.out.println("---------------");
        System.out.println("Total Score Player Scored:");
        System.out.println("---------------");
        System.out.println(roundDetails.getScore());
        System.out.println("---------------");
    }
    private void printAllRoundDetails(List<RoundDetails> roundDetailsList) {
        for (RoundDetails roundDetails : roundDetailsList) {
            printRoundDetails(roundDetails);
        }
    }
    private boolean askForReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to see round details? (Y/N): ");

        String response = scanner.nextLine().trim();

        if (response.equalsIgnoreCase("Y")) {
            // If the user wants to see round details and there are rounds played, print details for all rounds
            if (!roundDetailsList.isEmpty()) {
                // Sort it based on highscore
                System.out.println("---------------");
                System.out.println("Round Replay");
                System.out.println("---------------");
                printAllRoundDetails(roundDetailsList);
            } else {
                System.out.println("No rounds played yet.");
            }
        } else if (response.equalsIgnoreCase("N")) {
            return false; // User does not want to see round details
        } else {
            System.out.println("Invalid input. Please enter Y or N.");
            return askForReplay(); // Recursive call for invalid input
        }
        return true; // User wants to see round details
    }

    private void resetGameState() {
        // Reset game state for a new game
        deck = new Deck();
        player = new Player(deck);
        computer = new Computer(deck);
        score = 0;
        roundNumber = 1;
        roundDetailsList.clear();
    }
}
