package version2;
public class Chips {
    public static int playerChips = 250;
    public static int playerBet = 0;

    public static void addChips() {
        int amount = Main.getInput("How many chips to add: ");
        playerChips += amount;
    }

    public static void newBet() {
        while (true) {
            int amount = Main.getInput("How many chips to bet: ");
            if (playerChips < amount) {
                System.out.println("You don't have enough chips.\n");
                addChips();
            } else if (playerChips == 0) {
                System.out.println("You don't have any chips.\n");
                addChips();
            } else {
                playerBet = amount;
                break;
            }
        }
    }
}