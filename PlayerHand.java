
/* Description: This class contains the hand for the players as well as its methods.

Difficulties: Initially I had a Player class as well that had an instance variable of a hand but I realized after a while
that it wasn't necessary and that I only needed this class to manage the hand while the player would be
created and controlled in the TextUno class.
What I Learned: creating different methods based on the needs at the time during developer testing.
*/

import java.util.*;
public class PlayerHand
{
    private ArrayList<Card> hand;
    
    public PlayerHand()
    {
        hand = new ArrayList<Card>();
    }
    
    /************************** Accessors **************************/
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    /************************** Mutators **************************/
    public void setStartingHand()//gives the player 7 random cards from the deck to start the round with
    {
        for(int i = 0; i<7; i++)
        {
            drawCard();
        }
    }
    
    public void drawCard()//draws a card from the deck and adds to the player's hand
    {
        Card c = GameDecks.deck.get((int)(Math.random()*(GameDecks.deck.size())));
        addCard(c);
        GameDecks.deck.remove(c);
    }
    
    // removeCard and addCard are used to make writing code for other methods easier
    public void removeCard(int i)//removes card from hand
    {
        hand.remove(i);
    }
    
    public void addCard(Card c)//adds card to hand
    {
        hand.add(c);
    }
    
    public Card matchCardInHand(String str)
    {
        for(Card card:hand)
        {
            if(str.toLowerCase().contains(card.getColor().toLowerCase()) && str.toLowerCase().contains(card.getSymbol().toLowerCase()))
            {
                return card;
            }
        }
        return GameDecks.getTopCard();
    }
    
    public String toString()
    {
        String str = "\n";
        for(int i = 0; i<hand.size(); i++)
        {
            if(i < hand.size()-1)
            {
                str += " * " + hand.get(i) + "\n";
            }
            else
            {
                str += " * " +  hand.get(i);
            }
        }
        return str;
    }
}
/*
 * No Output
 */