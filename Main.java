import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Input related variables
    public static Scanner scanner = new Scanner(System.in);

    // Preferences
    public static Boolean enableBets = false;
    public static Boolean autoReplay = false;

    // Bet related variables
    public static int playerChips = 250;
    public static int playerBet = 0;
    
    // Input validation
    public static int getInput(String prompt, ArrayList<Integer> choices) {
        while (true) {
            try {
                System.out.print(prompt);
                int choice = Integer.parseInt(scanner.nextLine());
                if (choices.contains(choice)) {
                    return choice;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    public static int getInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Bet function
    public static void newBet() {
        while (true) {
            int amount = getInput("How many chips you want to bet? " + "(Balance:" + playerChips + "): ");
            if (playerChips < amount) {
                System.out.println("You don't have enough chips. Add some chips to your balance first.\n");
                adjustChips("add");
            } else if (playerChips == 0) {
                System.out.println("You don't have any chips. Add some chips to your balance first.\n");
                adjustChips("add");
            } else {
                playerBet = amount;
                break;
            }
        }
    }

    // Chip function
    public static void adjustChips(String operation) {
        switch (operation) {
            case "add":
                int amount = getInput("How many chips you want to add: ");
                playerChips += amount;
                break;
            case "blackjack":
                playerChips += (playerBet * (1.5));
                break;
            case "won":
                playerChips += playerBet;
                break;
            case "lost":
                playerChips -= playerBet;
                break;
        }
    }

    /*
    // Replay
    public static void replay() {
        Game.startGame();
    }
    */

    // Main    
    public static void main(String[] args) {
        while (true) {
            String betState = enableBets ? "enabled" : "disabled";
            //String autoReplayState = autoReplay ? "enabled" : "disabled";
            System.out.println("---\nWelcome to Blackjack!\n---");
            System.out.println("Enter 1 to start a new game!\n");
            System.out.println("Game Preferences\n  - Betting     > " + betState + " (Enter 2 to toggle)"); //\n  - Auto Replay > " + autoReplayState + " (Enter 3 to toggle)
            int playerChoice = Main.getInput("\nEnter: ", new ArrayList<Integer>(Arrays.asList(1, 2))); // 3
            switch (playerChoice) {
                case 1:
                    Game.startGame();
                    break;
                case 2:
                    enableBets = true;
                    break;
                /*
                case 3:
                    autoReplay = true;
                    break;
                */
            }
            System.out.println("\n\n");
        }
    }
}
