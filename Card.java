/*
Description: This class has the methods for the Card object which is the basis for the game itself.

Difficulty: I originally had another instance variable called inDeck, which was a boolean variable that indicates whether or
not it was in the deck. I had this in because I initially I made the deck an array instead of ArrayList

What I Learned: Some pieces of code become unnecessary after altering other pieces of codes.
*/
public class Card
{
    private String color;
    private String symbol;
    
    public Card(String color, String symbol)
    {
        this.color = color;
        this.symbol = symbol;
    }
    
    /************************** Accessors **************************/
    public String getColor()
    {
        return color;
    }
    
    public String getSymbol()
    {
        return symbol;
    }
    
    /************************** Mutators **************************/
    public void setColor(String color)
    {
        this.color = color;
    }
    
    public String toString()
    {
        return color+" "+ symbol;
    }
}
