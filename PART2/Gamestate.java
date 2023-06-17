import java.io.Serializable;
import java.util.*;

// Serializable class to represent the game state
class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer trick;
    ArrayList<Card> deck;
    ArrayList<Card> center;
    ArrayList<Player> players;
    

    public GameState(Integer trick, ArrayList<Card> deck, ArrayList<Card> center, ArrayList<Player> players) {
        this.trick = trick;
        this.deck = deck;
        this.center = center;
        this.players = players;
    }
}