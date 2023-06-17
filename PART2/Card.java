import java.io.Serializable;

class Card implements Serializable
{
     String suit;
     String rank;
     //int cardValue;

     Card() {}

    public Card(String suit, String rank)
    {
        this.suit = suit;
        this.rank = rank;
        //this.cardValue = calculateValue(rank);
    }

    public String getSuit()
    {
        return this.suit;
    }

    public String getRank()
    {
        return this.rank;
    }

    public int getValue()
    {
        switch (rank)
        {
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
            case "Q":
            case "K":
                return 10;

            default:
                return 0;
        }
    }

     public boolean isEqual(Card card) 
     {
        if (this.suit.equals(card.getSuit()) && this.rank.equals(card.getRank()))
        return true;
        else 
        return false;
     }

     /* 
     private int calculateValue(String rank)
     {
        switch(rank)
        {
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
                    return 10;
                case "J":
                    return 11;
                case "Q":
                    return 12;
                case "K":
                    return 13;
                default:
                    return 0;
            }
     }
     */

     public String getCard()
    {
        return this.suit+this.rank;
    }

            @Override
            public String toString() 
            {
            return suit + rank;
            }


}