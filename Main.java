import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.ColorUIResource;

public class Main{

    public static JFrame frame = new JFrame();
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

        // FRAME
        frame.setLayout(new BorderLayout(10, 5));
        frame.setSize(1200, 750);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Blackjack - Main Menu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel background = new JPanel();
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        background.setBackground(new ColorUIResource(53, 101, 77));

        frame.add(background);
        frame.setVisible(true);
        Deck.getCardImage(Deck.generateDeck().get(0));
        // FRAME

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
