import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public static boolean gameStarted = false;
    public static boolean playerBust = false;
    public static boolean dealerReveal = false;

    public static ArrayList<String> playerHand = new ArrayList<>();
    public static ArrayList<String> dealerHand = new ArrayList<>();

    public static void startGame() {
        Chips.newBet();
        gameStarted = true;

        ArrayList<String> playDeck = Deck.generateDeck();
        playerHand.add(Deck.drawCard(playDeck));
        dealerHand.add(Deck.drawCard(playDeck)); // hidden card
        playerHand.add(Deck.drawCard(playDeck));
        dealerHand.add(Deck.drawCard(playDeck));

        String hiddenCard = dealerHand.get(0);

        while (true) {
            if (Deck.getHandValue(playerHand) > 21) {
                playerBust = true;
                dealerReveal = true;
                Deck.getStatus(dealerHand, playerHand, hiddenCard, dealerReveal);
                System.out.println("Your hand went over 21. You lost.");
                Chips.playerChips -= Chips.playerBet;
                gameStarted = false;
                break;
            } else if (Deck.getHandValue(playerHand) == 21) {
                dealerReveal = true;
                Deck.getStatus(dealerHand, playerHand, hiddenCard, dealerReveal);
                System.out.println("Blackjack! You win.");
                Chips.playerChips += (Chips.playerBet * (1.5));
                gameStarted = false;
                break;
            }

            Deck.getStatus(dealerHand, playerHand, hiddenCard, dealerReveal);
            Frame.updateFrame();
            int choice = Main.getInput("1 - Hit   |   2 - Stay   > ", new ArrayList<Integer>(Arrays.asList(1, 2)));
            if (choice == 1) {
                playerHand.add(Deck.drawCard(playDeck));
            } else if (choice == 2) {
                dealerReveal = true;
                break;
            }
        }

        dealerReveal = true;

        if (!playerBust && Deck.getHandValue(dealerHand) < 17 && gameStarted) {
            while (true) {
                if (Deck.getHandValue(dealerHand) >= 17) {
                    break;
                }
                dealerHand.add(Deck.drawCard(playDeck));
            }
        }

        System.out.println("\n\n");
        Deck.getStatus(dealerHand, playerHand, hiddenCard, dealerReveal);
        System.out.println("\n");

        if (!playerBust && gameStarted) {
            if (Deck.getHandValue(dealerHand) > 21) {
                System.out.println("Dealer's hand went over 21. You win.");
                Chips.playerChips += Chips.playerBet;
            } else if (Deck.getHandValue(playerHand) > Deck.getHandValue(dealerHand)) {
                System.out.println("You win.");
                Chips.playerChips += Chips.playerBet;
            } else if (Deck.getHandValue(playerHand) == Deck.getHandValue(dealerHand)) {
                System.out.println("It's a tie.");
            } else {
                System.out.println("Dealer wins.");
                Chips.playerChips -= Chips.playerBet;
            }
        }

        System.out.println("Game over.");

        playerHand.removeAll(playerHand);
        dealerHand.removeAll(dealerHand);

        playerBust = false;
        dealerReveal = false;
        gameStarted = false;

        while (true) {
            int replay = Main.getInput("1 - Return to main menu   |   2 - Exit the game   > ", new ArrayList<Integer>(Arrays.asList(1, 2)));
            if (replay == 2) {
                System.out.println("\nThank you for playing!\n");
                System.exit(0);
                break;
            } else if (replay == 1) {
                return;
            }
        }
    }
}
