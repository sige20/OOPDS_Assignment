import java.io.Serializable;
import java.util.*;

class Player implements Serializable{
     ArrayList<Card> hand;
     int score;

    public Player(ArrayList<Card> hand, int score)
    {
        this.hand = hand;
        this.score = score;
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public int getScore()
    {
        return score;
    }

    public void addCard(Card card)
    {
        hand.add(card);
    }

    public void removeCard(Card card)
    {
        hand.remove(card);
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}