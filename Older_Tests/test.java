import java.util.*;

public class test{

    private static final String[] SUITS = {"Hearts","Diamonds","Clubs","Spades"};
    private static final String[] RANKS = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

    private static final Map<String, Integer> CARD_VALUES = new HashMap<>();

    static {
        CARD_VALUES.put("2", 2);
        CARD_VALUES.put("3", 3);
        CARD_VALUES.put("4", 4);
        CARD_VALUES.put("5", 5);
        CARD_VALUES.put("6", 6);
        CARD_VALUES.put("7", 7);
        CARD_VALUES.put("8", 8);
        CARD_VALUES.put("9", 9);
        CARD_VALUES.put("10", 10);
        CARD_VALUES.put("Jack", 10);
        CARD_VALUES.put("Queen", 10);
        CARD_VALUES.put("King", 10);
        CARD_VALUES.put("Ace", 11);
    }

    private static final Scanner scanner = new Scanner(System.in);
    //private static Random random = new Random();
    private static int playerChips = 100; // Starting chips for the player

    private static void loadChips() {
        System.out.println("Yeni chips yükleniyor...");
        playerChips += 50; // 
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("1. Quit the game");
            System.out.println("2. See the rules");
            System.out.println("3. Play a new game");
            System.out.println("4. Adding chips ");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("Thanks for playing. Goodbye!");
                    System.exit(0);
                    break;
                case 2:
                    System.out.println("Welcome to Enhanced Blackjack!");
                    System.out.println("Each round a player has the option to hit (get a new card) or stay.\nIf they stay the rounds are over.\n\nAn Ace can be treated as a one or eleven in blackjack, if they draw an Ace ask them if they want it to be considered as a one or eleven.\n\nThe dealer should receive a random card each round, try to make sure the dealer plays in an intelligent way and does not go over 21 themselves too often.\n\nThe player is trying to get a higher hand than the dealer without going bust. E.g. if at the end of the rounds the player score is 18 and dealers is 14 , the player wins.\nIf the dealer and player have equal points the dealer wins.\nIf a players total card points goes over 21 they are bust and immediately lose.\nIf a dealers total card points goes over 21 they are bust and immediately lose.\nIf the player gets a blackjack (21 points) they automatically win.\n");
                    break;
                case 3:
                    playGame();
                    break;
                case 4:
                    loadChips();
                    System.out.println("Yeni chips miktarınız: " + playerChips);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void playGame() {
        System.out.println("Let's play Enhanced Blackjack!");

        // Initialize deck
        List<String> deck = generateDeck();
        Collections.shuffle(deck);

        // Deal initial cards
        List<String> playerHand = new ArrayList<> ();
        List<String> dealerHand = new ArrayList<> ();

        playerHand.add(drawCard(deck));
        dealerHand.add(drawCard(deck));
        playerHand.add(drawCard(deck));
        dealerHand.add(drawCard(deck));

        int playerBet = getBet();

        boolean playerBust = false;
        // boolean dealerBust = false;

        // Player's turn
        while (true) {
            displayGameStatus(playerHand, dealerHand, false);
            int playerScore = calculateHandValue(playerHand);

            if (playerScore == 21) {
                System.out.println("Blackjack! You win!");
                playerChips += 1.5 * playerBet; // Blackjack pays 1.5 times the bet
                break;
            }

            if (playerScore > 21) {
                System.out.println("Bust! You lose.");
                playerChips -= playerBet;
                playerBust = true;
                break;
            }

            int choice = getIntInput("1. Hit\n2. Stay\nEnter your choice: ");

            if (choice == 1) {
                playerHand.add(drawCard(deck));
            } else {
                break;
            }
        }

        // Dealer's turn
        while (!playerBust && calculateHandValue(dealerHand) < 17) {
            dealerHand.add(drawCard(deck));
        }

        // Determine winner
        displayGameStatus(playerHand, dealerHand, true);

        if (calculateHandValue(dealerHand) > 21) {
            System.out.println("Dealer busts! You win!");
            playerChips += playerBet;
        } else if (calculateHandValue(playerHand) > calculateHandValue(dealerHand)) {
            System.out.println("You win!");
            playerChips += playerBet;
        } else if (calculateHandValue(playerHand) == calculateHandValue(dealerHand)) {
            System.out.println("It's a tie. Dealer wins.");
        } else {
            System.out.println("You lose!");
            playerChips -= playerBet;
        }
        System.out.println("Your total chips: " + playerChips);

        // Ask if the player wants to play again
        String playAgain = getStringInput("Do you want to play again? (y/n): ");
        if ("n".equalsIgnoreCase(playAgain)) {
            System.out.println("Thanks for playing. Goodbye!");
            System.exit(0);
        }
    }

    private static List < String > generateDeck() {
        List < String > deck = new ArrayList < > ();
        for (String suit: SUITS) {
            for (String rank: RANKS) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }

    private static String drawCard(List < String > deck) {
        return deck.remove(0);
    }

    private static int calculateHandValue(List < String > hand) {
        int value = 0;
        int numAces = 0;

        for (String card: hand) {
            String rank = card.split(" ")[0];
            value += CARD_VALUES.get(rank);
            if (rank.equals("Ace")) {
                numAces++;
            }
        }

        // Adjust for Aces
        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }
        return value;
    }

    private static void displayGameStatus(List < String > playerHand, List < String > dealerHand, boolean revealDealer) {
        System.out.println("Your hand: " + playerHand);
        System.out.println("Your score: " + calculateHandValue(playerHand));
        if (revealDealer) {
            System.out.println("Dealer's hand: " + dealerHand);
            System.out.println("Dealer's score: " + calculateHandValue(dealerHand));
        } else {
            System.out.println("Dealer's hand: [???, " + dealerHand.get(1) + "]");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getBet() {
        while (true) {
            int bet = getIntInput("Enter your bet (current chips: " + playerChips + "): ");
            if (bet > 0 && bet <= playerChips) {
                return bet;
            } else {
                System.out.println("Invalid bet. Please enter a valid bet.");
            }
        }
    }
}