import java.util.*;

public class test2{
    public static String[] ranks = new String[]{"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    public static String[] suits = new String[]{"Hearts", "Diamonds", "Clubs", "Spades"};
    
    public static String[] deck = new String[52];
    private static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static int playerChips = 100;
    public static int playerBet = 0;
    public static int moveCount = 0;
    public static int aceAmount = 0;

    // Generate a new deck at new game
    public static String[] generateDeck(){
        int a=0;
        // Create a ordered deck
        for(int i=0; i<ranks.length; i++){
            for(int j=0; j<suits.length; j++){
                deck[a] = ranks[i] + " of " + suits[j];
                a++;
            }
        }
        // Randomize previously created deck
        for(int t=0; t<random.nextInt(deck.length); t++){
            for(int i=0; i<deck.length; i++){
                String[] randomized = deck;
                int randomIndex = random.nextInt(deck.length);
                String _temp = randomized[randomIndex];
                randomized[randomIndex] = randomized[i];
                randomized[i] = _temp;
            } 
        }
        return deck;
    }

    // Draw a card from shuffled deck
    public static String draw(String[] deckInput){
        int randomIndex = random.nextInt(deckInput.length);
        System.out.println(randomIndex);
        System.out.println(deckInput[randomIndex]);
        // Check if selected index is already drawn
        if(deckInput[randomIndex]==null){
            randomIndex = random.nextInt(deckInput.length);
        }
        // Return selected index and set it's value to null
        String _temp = deckInput[randomIndex];
        deck[randomIndex] = null;
        return _temp;
    }

    public static int getCardValue(String card){
        System.out.println(card);
        int cardValue = 0;
        if(card.contains("Jack") && card.contains("Queen") && card.contains("King")){
            // Jack, Queen and King is 10 points
            cardValue = 10;
        }else if(card.contains("Ace")){
            // Ace is 1 or 11, but it's calculated in getHandValue() function
            cardValue = 11;
            aceAmount += 1;
        }else{
            // Other cards have their exact face value
            cardValue =  Integer.parseInt(card.replaceAll("[^0-9]", ""));
        }
        return cardValue;
    }

    public static int getHandValue(String[] hand){
        int handValue = 0;
        // Calculate for each card
        for(int i=0; i<hand.length; i++){
            handValue += getCardValue(hand[i]);
        }
        // Calculate Aces' values
        if(handValue>21 && aceAmount>0){
            handValue -= (aceAmount*10);
        }
        return handValue;
    }

    // Add 50 chips to player's balance
    public static void addChips(){
        int amount = getInput("Enter chip amount: ");
        playerChips += amount;
        System.out.println(amount + " chips added to your balance.");
    }

    public static void displayStatus(String[] player, String[] dealer, boolean dealerReveal){
        System.out.println("---");
        System.out.println("Dealer's Hand:");
        String dealerCards = "";
        String playerCards = "";
        if(dealerReveal == false){
            for(int i=1; i<dealer.length; i++) {
                dealerCards.concat(" " + dealer[i] + " (" + getCardValue(dealer[i]) + "pts)" + " |");
            }
        }else if(dealerReveal == true){
            for(int i=0; i<dealer.length; i++) {
                dealerCards.concat(" " + dealer[i] + " (" + getCardValue(dealer[i]) + "pts)" + " |");
            }
        }
        System.out.println("|"  + "hidden (_pts)" + dealerCards);
        System.out.println("");
        System.out.println("Your Hand:");
        for(int i=1; i<player.length; i++) {
                playerCards.concat(" " + player[i] + " (" + getCardValue(player[i]) + "pts)" + " |");
        }
        System.out.println("|"  + playerCards);
        System.out.println("---");
    }

    public static void startGame(){
        for(int i=0;i<1000;i++){System.out.println("\n");}; // Clear command prompt

        String[] playingDeck = generateDeck();
        String[] playerHand = new String[52];
        String[] dealerHand = new String[52];

        aceAmount = 0;
        playerBet = 0;
        moveCount = 0;

        // Ask player bet amount
        while(true){
            playerBet = getInput("How much do you want to bet: ");
            if(playerBet>playerChips){
                System.out.println("Not enough chips.");
            }else if(playerBet <= 0){
                System.out.println("Invalid chip amount.");
            }else{
                playerChips -= playerBet;
                break;
            }
        }

        // Draw starting cards
        playerHand[moveCount] = draw(playingDeck);
        dealerHand[moveCount] = draw(playingDeck); // This card is hidden
        moveCount++;
        playerHand[moveCount] = draw(playingDeck);
        dealerHand[moveCount] = draw(playingDeck);
        
        System.out.println("---");
        System.out.println("Dealer's Hand:");
        System.out.println("| " + "hidden (_pts)" + " | " + dealerHand[0] + " (" + getCardValue(dealerHand[0]) + "pts)" + " |");
        System.out.println("");
        System.out.println("Your Hand:");
        System.out.println("| " + playerHand[0] + " (" + getCardValue(playerHand[0]) + "pts)" + " | " + playerHand[1] + " (" + getCardValue(playerHand[1]) + "pts)" + " |");
        System.out.println("---");
        //if{}

        /* Check for instant blackjack
        int _playerHandValue = getCardValue(playerHand[0]) + getCardValue(playerHand[1]);
        int _dealerHandValue = getCardValue(dealerHand[0]) + getCardValue(dealerHand[1]);
        if(_playerHandValue == 21 || _dealerHandValue != 21){
            System.out.println("You have a blackjack! You won!");
            return;
        }*/

        while(true){
            int playerChoice = getInput("1 - Hit   |   2 - Stay   > ");
            switch (playerChoice) {
                case 1:
                    moveCount++;
                    playerHand[moveCount] = draw(playingDeck);
                    displayStatus(playerHand, dealerHand, false);
                    break;
                case 2:
                    
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        for(int i=0;i<1000;i++){System.out.println("\n");}; // Clear command prompt

        while(true) {
            System.out.println("1. Play a new game");
            System.out.println("2. Add chips to your balance");
            System.out.println("3. See the rules");
            System.out.println("4. Quit the game");
            int choice = getInput("Enter your choice: ");

            switch(choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    addChips();
                    System.out.println("New chip amount: " + playerChips);
                    break;
                case 3:
                    System.out.println("-----");
                    System.out.println(" ");
                    System.out.println("How to play blackjack?");
                    System.out.println(" ");
                    System.out.println("    rules and stuff");
                    System.out.println(" ");
                    System.out.println("-----");
                    break;
                case 4:
                    System.out.println("Thanks for playing. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}