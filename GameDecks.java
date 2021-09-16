/*

Description: This class holds the deck, discard pile and the methods used alongside them. It plays a huge part in
the game and has many functions that are vital to this game.

Difficulties: I initially created the instance variable deck as an array instead of an ArrayList because I
thought I could easily filter through using the inDeck instance variable of the class Card. I was so
wrong. Although it could be done it would make things more complicated for me. I changed it to ArrayList
because using that I could give the cards out and it wouldn't be in the ArrayList meaning there
would be less trouble when drawing cards because it won't choose a card I don't want it to since it won't
even be accessible.

What I Learned: I learned through trial and error that there are situations where ArrayLists are just superior to
regular arrays and when to use them.
 */
import java.util.*;
public class GameDecks
{
    public static ArrayList<Card> deck;
    public static ArrayList<Card> discardPile;
    
    public GameDecks()
    {
        deck = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
    }
    
    
    /************************** Accessors **************************/
    public static Card getTopCard()
    {
        return discardPile.get(discardPile.size()-1);
    }

    /************************** Mutators **************************/
    public void setDeck()
    {
        int i = 0;
        String count = "1";
        while(i<19)
        {
            if(Integer.valueOf(count) == 10)
            {
                count = "0";
            }
            deck.add(new Card("Blue", count));
            i++;
            count = 1 + Integer.valueOf(count) + "";
        }
        count = "1";
        while(i<38)
        {
            if(Integer.valueOf(count) == 10)
            {
                count = "0";
            }
            deck.add(new Card("Green", count));
            i++;
            count = 1 + Integer.valueOf(count) + "";
        }
        count = "1";
        while(i<57)
        {
            if(Integer.valueOf(count) == 10)
            {
                count = "0";
            }
            deck.add(new Card("Red", count));
            i++;
            count = 1 + Integer.valueOf(count) + "";
        }
        count = "1";
        while(i<76)
        {
            if(Integer.valueOf(count) == 10)
            {
                count = "0";
            }
            deck.add(new Card("Yellow", count));
            i++;
            count = 1 + Integer.valueOf(count) + "";
        }
        while(i<78)
        {
            deck.add(new Card("Blue", "Skip"));
            i++;
        }
        while(i<80)
        {
            deck.add(new Card("Green", "Skip"));
            i++;
        }
        while(i<82)
        {
            deck.add(new Card("Red", "Skip"));
            i++;
        }
        while(i<84)
        {
            deck.add(new Card("Yellow", "Skip"));
            i++;
        }
        while(i<86)
        {
            deck.add(new Card("Blue", "Reverse"));
            i++;
        }
        while(i<88)
        {
            deck.add(new Card("Green", "Reverse"));
            i++;
        }
        while(i<90)
        {
            deck.add(new Card("Red", "Reverse"));
            i++;
        }
        while(i<92)
        {
            deck.add(new Card("Yellow", "Reverse"));
            i++;
        }
        while(i<94)
        {
            deck.add(new Card("Blue", "Draw 2"));
            i++;
        }
        while(i<96)
        {
            deck.add(new Card("Green", "Draw 2"));
            i++;
        }
        while(i<98)
        {
            deck.add(new Card("Red", "Draw 2"));
            i++;
        }
        while(i<100)
        {
            deck.add(new Card("Yellow", "Draw 2"));
            i++;
        }
        /*
        while(i<104)
        {
            deck.add(new Card("Black", "Wild"));
            i++;
        }
        while(i<108)
        {
            deck.add(new Card("Black", "Draw 4"));
            i++;
        }
        couldn't find a way to get this to work
        */
    }
    
    public void drawCards(int num, PlayerHand hand)
    {
        for(int i = 0; i<num; i++)
        {
            hand.addCard(deck.get((int)(Math.random()*(deck.size()-1))));
        }
    }
    
    public void placeStartingCard()//draws random card to be the starting card
    {
        placeCard(deck.get((int)(Math.random()*(deck.size()))));
    }
    
    public void placeCard(Card c)
    {
        deck.remove(c);
        discardPile.add(c);
    }
    
    public void recoverDiscardPile()//puts the cards of the discard pile back into the deck except for the card on top.
    {
        setDeck();
        Card topCard = discardPile.get(discardPile.size()-1);
        discardPile.clear();
        for(int i = 0; i<deck.size(); i++)
        {
            if(deck.get(i)==topCard)
            {
                placeCard(deck.get(i));
            }
        }
    }
    /**************************** Other Methods ****************************/
    public boolean isReverse()//checks if last card placed is a reverse
    {
        if(getTopCard().getSymbol() == "Reverse")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isDraw2()//checks if last card placed is a draw 2
    {
        if(getTopCard().getSymbol() == "Draw 2")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isSkip()//checks if last card placed is a skip
    {
        if(getTopCard().getSymbol() == "Skip")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isWild()//checks if last card placed is a wild
    {
        if(getTopCard().getSymbol() == "Wild")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /*
    public boolean isDraw4()//checks if last card placed is a wild draw 4
    {
        if(getTopCard().getSymbol() == "Draw 4")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    */
    public boolean isPlaceable(Card card)
    {
        if(card.getColor().equals((getTopCard().getColor())))
        {
            return true;
        }
        else if(card.getSymbol().equals(getTopCard().getSymbol()))
        {
            return true;
        }
        /*
        else if(card.getColor() == "Black")
        {
            return true;
        }
        else if(getTopCard().getColor() == "Black")
        {
            return true;
        }
        */
        else
        {
            return false;
        }
    }
}
/*
 * No Output
 */