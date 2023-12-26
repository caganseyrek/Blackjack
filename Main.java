import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

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

    public static void main(String[] args) throws Exception {
        // Frame.setFrame();
        while (true) {
            System.out.println("\n\n");
            System.out.println("Chips: " + Chips.playerChips);
            System.out.println("\n1. Start a new blackjack game");
            System.out.println("2. Add chips to balance");
            System.out.println("3. Quit the game\n");
            int choice = getInput("Your choice: ");
            switch (choice) {
                case 1:
                    Game.startGame();
                    break;
                case 2:
                    Chips.addChips();
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
