public class RoundDetails {
    private int roundNumber;
    private Card computerCard;
    private String playerHand;
    private Card selectedCard;
    private int score;
    private String outCome;

    public RoundDetails(int roundNumber, Card computerCard, String playerHand, Card selectedCard, String outCome, int score) {
        this.roundNumber = roundNumber;
        this.computerCard = computerCard;
        this.playerHand = playerHand;
        this.selectedCard = selectedCard;
        this.outCome = outCome;
        this.score = score;
    }

    public int getRoundNumber(){
        return  this.roundNumber;
    }

    public void setRoundNumber(int countRoundNumber){
        this.roundNumber = countRoundNumber;
    }

    public Card getComputerCard(){
        return this.computerCard;
    }

    public void setComputerCard(Card computerCard){
        this.computerCard = computerCard;

    }

    public Card getSelectedCard(){
        return this.selectedCard;
    }

    public void setSelectedCard(Card selectedCard){
        this.selectedCard = selectedCard;
    }

    public String getPlayerHand(){
        return this.playerHand;
    }

    public void setPlayerHand(String playerHand){
        this.playerHand = playerHand;
    }

    public String getOutCome(){
        return this.outCome;
    }

    public void setOutCome(String outCome){
        this.outCome = outCome;
    }
    public int getScore() {
        return score;
    }
}
