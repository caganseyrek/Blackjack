import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Deck {
    public static String[] ranks = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    public static String[] suits = new String[] { "Hearts", "Diamonds", "Clubs", "Spades" };
    public static HashMap<String, Integer> cardValues = new HashMap<String, Integer>() {{
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("10", 10);
        put("Jack", 10);
        put("Queen", 10);
        put("King", 10);
        put("Ace", 11);
    }};

    public static ArrayList<String> generateNewDeck() {
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
        int cardValue = 0;
        if ("JackQueenKing".contains(cardRank)) {
            cardValue = 10;
        } else if (cardRank.equals("Ace")) {
            cardValue = 11;
        } else {
            cardValue = cardValues.get(cardRank);
        }
        return cardValue;
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
                aceAmount--;
                if (handValue == 21)
                    break;
            }
        }
        return handValue;
    }

    public static void getStatus() {
        System.out.println("\n");
        if (Game.dealerReveal) {
            System.out.println("Dealer's hand > " + Game.dealerHand + " (Value: " + getHandValue(Game.dealerHand) + ")");
        } else {
            Game.dealerHand.remove(Game.dealerHiddenCard);
            System.out.println("Dealer's hand > [Hidden Card], " + Game.dealerHand + " (Value: " + getHandValue(Game.dealerHand) + ")");
            Game.dealerHand.add(0, Game.dealerHiddenCard);
        }
        System.out.println("Your's hand > " + Game.playerHand + " (Value: " + getHandValue(Game.playerHand) + ")");
    }
}