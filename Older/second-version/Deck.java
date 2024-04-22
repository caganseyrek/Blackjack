package version2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Deck {
    public static String[] ranks = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    public static String[] suits = new String[] { "Hearts", "Diamonds", "Clubs", "Spades" };
    public static HashMap<String, Integer> values = new HashMap<String, Integer>();

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

    public static ArrayList<String> generateDeck() {
        ArrayList<String> newDeck = new ArrayList<String>();
        for (String rank : ranks) {
            for (String suit : suits) {
                newDeck.add(rank + " of " + suit);
            }
        }
        Collections.shuffle(newDeck);
        return newDeck;
    }

    public static String drawCard(ArrayList<String> deck) {
        return deck.remove(0);
    }

    public static int getCardValue(String card) {
        String cardRank = card.split(" ")[0];
        int value = 0;
        if ("JackQueenKing".contains(cardRank)) {
            value = 10;
        } else if (cardRank.equals("Ace")) {
            value = 11;
        } else {
            value = values.get(cardRank);
        }
        return value;
    }

    public static int getHandValue(ArrayList<String> deck) {
        int handValue = 0;
        int aceAmount = 0;
        for (String card : deck) {
            handValue += getCardValue(card);
            if (card.contains("Ace")) {
                aceAmount++;
            }
        }
        if (handValue > 21) {
            while (aceAmount > 0) {
                handValue -= 10;
                if (handValue == 21)
                    break;
                aceAmount--;
            }
        }
        return handValue;
    }

    public static void getStatus() {
        System.out.println("\n");

        //Frame.updateFrame();

        ArrayList<String> dealer = Game.dealerHand;
        ArrayList<String> player = Game.playerHand;
        String hiddenCard = dealer.get(0);

        if (Game.dealerReveal == false) {
            dealer.remove(hiddenCard);
            System.out.println("Dealer's Hand > " + "[Hidden] " + dealer + " (" + getHandValue(dealer) + "pts)");
            System.out.println("Player's Hand > " + player + " (" + getHandValue(player) + "pts)");
            dealer.add(0, hiddenCard);
        } else if (Game.dealerReveal == true) {
            System.out.println("Dealer's Hand > " + dealer + " (" + getHandValue(dealer) + "pts)");
            System.out.println("Player's Hand > " + player + " (" + getHandValue(player) + "pts)");
        }
    }

    public static String getCardImage(String card) {
        String cardRank = card.toLowerCase().split(" ")[0];
        String fileName = card.toLowerCase().replaceAll(" ", "_");
        String path = "Blackjack/cards/" + cardRank + "/" + fileName + ".png";
        return path;
    }
}