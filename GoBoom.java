import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GoBoom {
    public static void main(String[] args) {
        ArrayList<String> deck = new ArrayList<String>();
        ArrayList<String> player1 = new ArrayList<String>();
        ArrayList<String> player2 = new ArrayList<String>();
        ArrayList<String> player3 = new ArrayList<String>();
        ArrayList<String> player4 = new ArrayList<String>();
        ArrayList<String> center = new ArrayList<String>();

        // Suits and Ranks in the deck
        String[] suits = { "c", "d", "h", "s" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(suit + rank);
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Deal cards to each player
        for (int i = 0; i < 7; i++) {
            player1.add(deck.get(i * 4));
            player2.add(deck.get(i * 4 + 1));
            player3.add(deck.get(i * 4 + 2));
            player4.add(deck.get(i * 4 + 3));
        }

        // Removing the distributed cards from the deck after dealing cards
        deck.removeAll(player1);
        deck.removeAll(player2);
        deck.removeAll(player3);
        deck.removeAll(player4);

        // first card in deck is the first lead card and placed at center
        String firstCard = deck.get(0);

        // determine first player
        int firstPlayer = 0;
        center.add(firstCard);
        if (firstCard.endsWith("A") || firstCard.endsWith("5") || firstCard.endsWith("9") || firstCard.endsWith("K")) {
            System.out.println("Player 1 leads the trick");
            firstPlayer = 1;
        } else if (firstCard.endsWith("2") || firstCard.endsWith("6") || firstCard.endsWith("10")) {
            System.out.println("Player 2 leads the trick");
            firstPlayer = 2;
        } else if (firstCard.endsWith("3") || firstCard.endsWith("7") || firstCard.endsWith("J")) {
            System.out.println("Player 3 leads the trick");
            firstPlayer = 3;
        } else if (firstCard.endsWith("4") || firstCard.endsWith("8") || firstCard.endsWith("Q")) {
            System.out.println("Player 4 leads the trick");
            firstPlayer = 4;
        }

        // Test the cards at each player
        Scanner scanner = new Scanner(System.in);
        String card;

        System.out.println("#Trick");
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
        System.out.println("Player 3: " + player3);
        System.out.println("Player 4: " + player4);
        System.out.println("Deck: " + deck);
        System.out.println("Testing input");
        card = scanner.nextLine();
        center.add(card);

    }

}
