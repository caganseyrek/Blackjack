import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    // Game variables
    public static String gameWinner = new String();
    public static boolean gameEnded = false;
    public static boolean dealerReveal = false;
    public static boolean playerBust = false;

    // Deck variables
    public static ArrayList<String> playerHand = new ArrayList<>();
    public static ArrayList<String> dealerHand = new ArrayList<>();
    public static String dealerHiddenCard = new String();

    public static void startGame() {
        if (Main.enableBets)
            Main.newBet();

        gameEnded = false;

        ArrayList<String> playDeck = Deck.generateNewDeck();

        dealerHiddenCard = Deck.drawCard(playDeck);
        dealerHand.add(dealerHiddenCard);
        playerHand.add(Deck.drawCard(playDeck));

        dealerHand.add(Deck.drawCard(playDeck));
        playerHand.add(Deck.drawCard(playDeck));

        // Repeat the game steps until a function ends the loop
        while (true) {
            // Check if player got a blackjack
            if (Deck.getHandValue(playerHand) == 21) {
                gameEnded = true;
                dealerReveal = true;
                gameWinner = "Player";
                break;
            }

            // Check if player is busted
            if (Deck.getHandValue(playerHand) > 21) {
                gameEnded = true;
                dealerReveal = true;
                gameWinner = "Dealer";
                // This variable is used to skip some functions if player is already lost
                // and so game is already ended
                playerBust = true;
                break;
            }

            // Display current state of hands
            Deck.getStatus();
            
            // Get the player's choice
            int playerChoice = Main.getInput("\nWhat is your choice? > (1-Hit) or (2-Stand) > ", new ArrayList<Integer>(Arrays.asList(1, 2)));
            if (playerChoice == 1) {
                playerHand.add(Deck.drawCard(playDeck));
            } else {
                dealerReveal = true;
                gameEnded = true;
                break;
            }
        }

        // Game turns are ended now

        // Draw cards until dealer's hand is above 17 if it is below
        if (!playerBust && gameEnded && Deck.getHandValue(dealerHand) < 17) {
            while (true) {
                // Break the loop if dealer's hand is >= 17
                if (Deck.getHandValue(dealerHand) >= 17) {
                    break;
                }
                // Else draw a card
                dealerHand.add(Deck.drawCard(playDeck));
            }
        }

        // Display current state of hands
        Deck.getStatus();

        // Decide the game's conclusion
        if (!playerBust) {
            int dealerHandValue = Deck.getHandValue(dealerHand);
            int playerHandValue = Deck.getHandValue(playerHand);
            if (dealerHandValue > 21) {
                gameWinner = "Player";
            } else {
                if (dealerHandValue == 21) {
                    if (playerHandValue == 21) {
                        gameWinner = "Tie";
                    } else {
                        gameWinner = "Dealer";
                    }
                }
                if (dealerHandValue > playerHandValue) {
                    gameWinner = "Dealer";
                } else if (playerHandValue > dealerHandValue) {
                    gameWinner = "Player";
                } else if (playerHandValue == dealerHandValue) {
                    gameWinner = "Tie";
                }
            }
        }

        // Display the winner
        switch (gameWinner) {
            case "Player":
                System.out.println("You won!");
                break;
            case "Dealer":
                System.out.println("Dealer won!");
                break;
            case "Tie":
                System.out.println("It's a tie!");
                break;
        }

        // Reset variables
        dealerReveal = false;
        playerBust = false;
        gameEnded = true;
        
        // End the game
        System.exit(0);
    }
}