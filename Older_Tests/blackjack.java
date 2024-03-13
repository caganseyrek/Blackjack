import java.util.*;

public class blackjack {
    private static String[] ranks = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    private static String[] suits = new String[] { "Hearts", "Diamonds", "Clubs", "Spades" };

    private static int aceAmount = 0;
    private static int playerChips = 0;
    private static int playerBet = 0;
    private static boolean playerBust = false;
    private static boolean dealerReveal = true;

    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, Integer> values = new HashMap<String, Integer>();

    static {
        values.put("2", 2);
        values.put("3", 3);
        values.put("4", 4);
        values.put("5", 5);
        values.put("6", 6);
        values.put("7", 7);
        values.put("8", 8);
        values.put("9", 9);
        values.put("10", 10);
        values.put("Jack", 10);
        values.put("Queen", 10);
        values.put("King", 10);
        values.put("Ace", 11);
    }

    private static ArrayList<String> generateDeck() {
        ArrayList<String> newDeck = new ArrayList<String>();
        for (String rank : ranks) {
            for (String suit : suits) {
                newDeck.add(rank + " of " + suit);
            }
        }
        Collections.shuffle(newDeck);
        return newDeck;
    }

    private static String draw(ArrayList<String> deck) {
        return deck.remove(0);
    }

    private static int getCardValue(String card) {
        String cardRank = card.split(" ")[0];
        int value = values.get(cardRank);
        if (cardRank.equals("Ace")) {
            aceAmount += 1;
        }
        return value;
    }

    private static int getHandValue(ArrayList<String> deck) {
        int handValue = 0;
        for (String string : deck) {
            handValue += getCardValue(string);
        }
        if (handValue > 21 && aceAmount > 0) {
            System.out.println("ace amount: " + aceAmount);
            handValue -= (10 * aceAmount);
            aceAmount--;
        }
        return handValue;
    }

    private static void addChips() {
        int amount = getInput("How many chips to add: ");
        playerChips += amount;
    }

    private static void newBet() {
        int amount = getInput("How many chips to bet: ");
        if (amount < playerBet ||  amount < 0) {
            System.out.println("Invalid input. Please enter a valid number.");
        } else {
            playerBet = amount;
        }
    }

    private static void getStatus(ArrayList<String> dealer, ArrayList<String> player, String hiddenCard, boolean reveal){
        for(int i=0;i<1000;i++){System.out.println("\n");}; // Clear command prompt

        if (reveal == false){
            System.out.println("\n---------------------------");
            System.out.println("Dealer's Hand > " + "[Hidden] " + dealer + " (" + getHandValue(dealer) + "pts)");
            System.out.println("Player's Hand > " + player + " (" + getHandValue(player) + "pts)");
            System.out.println("---------------------------");
        } else if(reveal == true){
            dealer.add(0, hiddenCard);
            System.out.println("\n---------------------------");
            System.out.println("Dealer's Hand > " + dealer + " (" + getHandValue(dealer) + "pts)");
            System.out.println("Player's Hand > " + player + " (" + getHandValue(player) + "pts)");
            System.out.println("---------------------------");
        }
    }

    public static void startGame() {
        newBet();
        ArrayList<String> playDeck = generateDeck();
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();

        playerHand.add(draw(playDeck));
        dealerHand.add(draw(playDeck)); // hidden card
        playerHand.add(draw(playDeck));
        dealerHand.add(draw(playDeck));

        String hiddenCard = dealerHand.remove(0);

        while (true) {
            getStatus(dealerHand, playerHand, hiddenCard, dealerReveal);

            if (getHandValue(playerHand) > 21) {
                System.out.println("Your hand went over 21. You lost.");
                playerBust = true;
                dealerReveal = true;
                break;
            } else if (getHandValue(playerHand) == 21) {
                System.out.println("Blackjack! You win.");
                playerChips += playerBet * (1.5);
                dealerReveal = true;
                break;
            }

            int choice = getInput("1 - Hit   |   2 - Stay   > ");
            if (choice == 1) {
                playerHand.add(draw(playDeck));
            } else if (choice == 2) {
                dealerReveal = true;
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        if (playerBust==false && getHandValue(dealerHand) < 17) {
            dealerHand.add(draw(playDeck));
            dealerReveal = true;
        }

        System.out.println("\n\n");
        getStatus(dealerHand, playerHand, hiddenCard, dealerReveal);

        if(!playerBust){
            if (getHandValue(dealerHand) > 21) {
                System.out.println("Dealer's hand went over 21. You win.");
                playerChips += playerBet;
            } else if (getHandValue(playerHand) > getHandValue(dealerHand)) {
                System.out.println("You win.");
                playerChips += playerBet;
            } else if (getHandValue(playerHand) == getHandValue(dealerHand)) {
                System.out.println("It's a tie.");
            } else {
                System.out.println("Dealer wins.");
                playerChips -= playerBet;
            }
        }
        
        System.out.println("\nGame over.");

        while(true){
            int replay = getInput("1 - Return to main menu   |   2 - Exit the game   > ");
            if (replay != 1 && replay != 2) {
                System.out.println("Invalid input. Please enter a valid number.");
            } else if (replay == 2) {
                System.out.println("\nThank you for playing!\n");
                System.exit(0);
                break;
            } else if (replay == 1) {
                return;
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

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\n");
            System.out.println("Chips: " + playerChips);
            System.out.println("\n1. Start a new blackjack game");
            System.out.println("2. Add chips to balance");
            System.out.println("3. Quit the game\n");
            int choice = getInput("Your choice: ");
            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    addChips();
                    break;
                case 3:
                    System.out.println("\nThank you for playing!\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}