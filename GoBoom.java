import java.util.*;

public class GoBoom {
    private String card;
    private int currentPlayer; // to keep track of current player
    private ArrayList<String> player1;
    private ArrayList<String> player2;
    private ArrayList<String> player3;
    private ArrayList<String> player4;
    private int trickNumber = 1;

    public static void main(String[] args) {
        GoBoom game = new GoBoom();
        game.playGame();
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    public void playGame() {
        ArrayList<String> deck = new ArrayList<String>();
        player1 = new ArrayList<String>();
        player2 = new ArrayList<String>();
        player3 = new ArrayList<String>();
        player4 = new ArrayList<String>();
        ArrayList<String> center = new ArrayList<String>();

        initializeDeck(deck);
        dealCards(deck);

        String firstCard = initializeCenter(deck, center);

        currentPlayer = determineFirstPlayer(firstCard);

        Scanner scanner = new Scanner(System.in);
        while (true) {

            for (int i = 0; i < 4; i++) {
                ArrayList<String> currentPlayerCards = getPlayer(currentPlayer);
                displayGameState(center, deck);
                playTurn(currentPlayer, center.get(0), currentPlayerCards, center, scanner);

                if (isTrickEnd(center)) {
                    trickNumber++;
                    center.clear();

                    if (deck.isEmpty()) {
                        System.out.println("Game Over");
                        return;
                    }

                    firstCard = startNewTrick(deck, center);
                }

                currentPlayer = nextPlayer(currentPlayer);
            }
            System.out.println("Thw winner for Trick" + trickNumber + " is Player" + determineTrickWinner(center));

        }

    }

    public void initializeDeck(ArrayList<String> deck) {
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
    }

    // Deal cards to each player
    public void dealCards(ArrayList<String> deck) {
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
    }

    // first card in deck is the first lead card and placed at center
    public String initializeCenter(ArrayList<String> deck, ArrayList<String> center) {
        String firstCard = deck.get(0);
        deck.remove(0);
        center.add(firstCard);
        return firstCard;
    }

    // display game state
    public void displayGameState(ArrayList<String> center, ArrayList<String> deck) {
        System.out.println("#Trick" + trickNumber);
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
        System.out.println("Player 3: " + player3);
        System.out.println("Player 4: " + player4);
        System.out.println("Center: " + center);
        System.out.println("Deck: " + deck);
    }

    public void playTurn(int currentPlayer, String currentCard, ArrayList<String> currentPlayerCards,
            ArrayList<String> center, Scanner scanner) {
        String card;
        System.out.println("Turn: Player " + currentPlayer);
        System.out.println("Enter card to play: ");
        card = scanner.nextLine();

        if (currentPlayerCards.contains(card)) {

            // Check suits or ranks
            String firstCard = center.get(0);

            if (card.substring(0, 1).equals(firstCard.substring(0, 1)) || card.substring(1, card.length()).equals(firstCard.substring(1, firstCard.length()))) 
            {
                currentPlayerCards.remove(card);
                center.add(card);
            } else if (card.substring(0, 1).equals(firstCard.substring(0, 1)) && card.contains("10")) {
                currentPlayerCards.remove(card);
                center.add(card);
            } else {
                System.out.println("Invalid card! Please follow the suit or rank of the first leading card.");
                playTurn(currentPlayer, currentCard, currentPlayerCards, center, scanner);// Recursive call to re-attempt the turn
            }
        } else {
            System.out.println("Invalid card! Please play a card from your own deck.");
            playTurn(currentPlayer, currentCard, currentPlayerCards, center, scanner); // Recursive call to re-attempt the turn
        }

        // add the who win the first trick
        determineTrickWinner(center);
    }

    public boolean isTrickEnd(ArrayList<String> center) {
        return center.size() == 5;
    }

    public int nextPlayer(int currentPlayer) {
        return currentPlayer % 4 + 1;
    }

    public int getValue(String rank) {
        switch (rank) {
            case "A":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
            case "J":
            case "K":
            case "Q":
                return 10;
            default:
                return 0;

        }
    }

    public int determineTrickWinner(ArrayList<String> center) {
        ArrayList<String> checkerRanks = new ArrayList<>();
        int index = 0;

        // to seperate rank out from the card
        for (String card : center) {
            checkerRanks.add(card.substring(1)); // check with jl what card is for?
        }

        // compare ranks
        int highestRank = getValue(checkerRanks.get(0)); // assume is the highest

        for (int i = 0; i < checkerRanks.size(); i++) {
            int currentRank = getValue(checkerRanks.get(i));
            if (currentRank > highestRank) {
                highestRank = currentRank;
                index= i;
            }
        }

        
        return index; // Return the index of the winning player
    }

    public ArrayList<String> getPlayer(int currentPlayer) {
        switch (currentPlayer) {
            case 1:
                return player1;
            case 2:
                return player2;
            case 3:
                return player3;
            case 4:
                return player4;
            default:
                return null;
        }
    }

    // Determine the first player based on the first card
    public int determineFirstPlayer(String firstCard) {
        if (firstCard.endsWith("A") || firstCard.endsWith("5") || firstCard.endsWith("9") || firstCard.endsWith("K")) {
            return 1;
        } else if (firstCard.endsWith("2") || firstCard.endsWith("6") || firstCard.endsWith("10")) {
            return 2;
        } else if (firstCard.endsWith("3") || firstCard.endsWith("7") || firstCard.endsWith("J")) {
            return 3;
        } else if (firstCard.endsWith("4") || firstCard.endsWith("8") || firstCard.endsWith("Q")) {
            return 4;
        }
        return -1; // Invalid first card
    }

    public String startNewTrick(ArrayList<String> deck, ArrayList<String> center) {
        String firstCard = deck.get(0);
        deck.remove(0);
        center.add(firstCard);
        return firstCard;
    }
}
