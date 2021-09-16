/**
 * Joshua Kim
 * AP CS A Final Project
 * 05/02/21
 * 
 * Description: This class holds the main method which is what actually incorporates the other classes to play the game. It 
 *              controls what is printed, which actions are run, and controls the bot.
 * 
 * Difficulties: Had trouble implementing the points concept so I simplified it so that each game would just result in a winner.
 *              I also made it 1v1 because making the order of the game would be incredibly difficult and time consuming
 *              if I were to do 4 players as I originally planned.
 *              
 * What I Learned: Creating a Text UNO game was a lot more difficult than I expected and that it can require a lot of patience 
 *              when coding a large project.
 */
import java.util.*;
public class TextUno
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a display name of your choosing: ");
        String username = scan.nextLine();
        System.out.println();
        //conditions for the turns of the user and the bot
        int userWin;
        int botWin;
        
        
        GameDecks game = new GameDecks();//creates the deck and discard pile
        
        PlayerHand hand1 = new PlayerHand();
        PlayerHand hand2 = new PlayerHand();
        
        String topColor = "";
        
        boolean cont = true;
        while(cont)
        {
            userWin = 0;
            botWin = 0;
            //sets the previously created deck of 108 cards
            game.setDeck();
            
            //deals the starting 7 cards to each player
            game.drawCards(7, hand1);
            game.drawCards(7, hand2);

            game.placeStartingCard();
            Card botCard = new Card("", "");
            
            String str = "";
            while(userWin == 0 && botWin == 0)
            {
                //user's turn
                if((!botCard.getSymbol().toLowerCase().equals("skip")) && !(botCard.getSymbol().toLowerCase().equals("reverse")) && !(botCard.getSymbol().toLowerCase().equals("draw 2")))
                {
                    System.out.println("Top Card: " + game.getTopCard());
                    System.out.println("Your options are: " + hand2 + "\n * Draw\n * or Quit");
                    
                    System.out.print("Enter the option of your choosing: \n");
                    
                    str = scan.nextLine();
                    if(game.isPlaceable(hand2.matchCardInHand(str)))
                    {
                        if(str.contains("quit"))
                        {
                            System.out.println("The game is now being exited...");
                            System.exit(-1);
                        }
                        
                        else if(str.toLowerCase().equals("draw"))
                        {
                            game.drawCards(1, hand2);
                            Card cardDrawn = hand2.getHand().get(hand2.getHand().size()-1);
                            System.out.println("\nCard Drawn: " + cardDrawn);
                            if(game.isPlaceable(cardDrawn))
                            {
                                System.out.println("Would you like to place the card you drew?");
                                String answer = scan.nextLine();
                                System.out.println("");
                                if(answer.toLowerCase().contains("y"))
                                {
                                    game.placeCard(hand2.getHand().get(hand2.getHand().size()-1));
                                    hand2.getHand().remove(hand2.getHand().get(hand2.getHand().size()-1));
                                    if(cardDrawn.getSymbol().equals("Reverse"))
                                    {
                                        System.out.println("The bot has been skipped\n");
                                        str = "reverse";
                                    }
                                    else if(cardDrawn.getSymbol().equals("Skip"))
                                    {
                                        System.out.println("The bot has been skipped\n");
                                        str = "skip";
                                    }
                                    else if(cardDrawn.getSymbol().equals("Draw 2"))
                                    {
                                        System.out.println();
                                        str = "draw 2";
                                        game.drawCards(2, hand1);
                                    }
                                    /*
                                    else if(cardDrawn.getSymbol().equals("Wild"))
                                    {
                                        str = "Wild";
                                        game.placeCard(hand2.matchCardInHand(str));
                                        hand2.getHand().remove(hand2.matchCardInHand(str));
                                        System.out.println("What color would you like to change it to?");
                                        String col = scan.next();//color
                                        if(col.toLowerCase().equals("blue"))
                                        {
                                            game.getTopCard().setColor("Blue");
                                        }
                                        if(col.toLowerCase().equals("green"))
                                        {
                                            game.getTopCard().setColor("Green");
                                        }
                                        if(col.toLowerCase().equals("red"))
                                        {
                                            game.getTopCard().setColor("Red");
                                        }
                                        if(col.toLowerCase().equals("yellow"))
                                        {
                                            game.getTopCard().setColor("Yellow");
                                        }
                                    }
                                    if(cardDrawn.getSymbol().equals("Draw 4"))
                                    {
                                        str = "Draw 4";
                                    }
                                    */
                                }
                            }
                        }
                        else if(str.toLowerCase().contains("draw 2"))
                        {
                            game.placeCard(hand2.matchCardInHand(str));
                            hand2.getHand().remove(hand2.matchCardInHand(str));
                            game.drawCards(2, hand1);
                            System.out.println("The bot has drawn 2 cards\n");
                        }
                        else if(str.toLowerCase().contains("skip"))
                        {
                            game.placeCard(hand2.matchCardInHand(str));
                            hand2.getHand().remove(hand2.matchCardInHand(str));
                            System.out.println("The bot has been skipped\n");
                        }
                        else if(str.toLowerCase().contains("reverse"))
                        {
                            game.placeCard(hand2.matchCardInHand(str));
                            hand2.getHand().remove(hand2.matchCardInHand(str));
                            System.out.println("The bot has been skipped\n");
                        }
                        /*
                        else if(str.toLowerCase().contains("black draw 4"))
                        {
                            game.placeCard(hand2.matchCardInHand(str));
                            hand2.getHand().remove(hand2.matchCardInHand(str));
                            game.drawCards(4, hand1);
                            System.out.println("The bot has drawn 4 cards");
                            System.out.println("What color would you like to change it to?");
                            String col = scan.next();//color
                            if(col.toLowerCase().equals("blue"))
                            {
                                game.getTopCard().setColor("Blue");
                            }
                            if(col.toLowerCase().equals("green"))
                            {
                                game.getTopCard().setColor("Green");
                            }
                            if(col.toLowerCase().equals("red"))
                            {
                                game.getTopCard().setColor("Red");
                            }
                            if(col.toLowerCase().equals("yellow"))
                            {
                                game.getTopCard().setColor("Yellow");
                            }
                        }
                        else if(str.toLowerCase().contains("wild"))
                        {
                            game.placeCard(hand2.matchCardInHand(str));
                            hand2.getHand().remove(hand2.matchCardInHand(str));
                            System.out.println("What color would you like to change it to?");
                            String col = scan.next();//color
                            if(col.toLowerCase().equals("blue"))
                            {
                                game.getTopCard().setColor("Blue");
                            }
                            if(col.toLowerCase().equals("green"))
                            {
                                game.getTopCard().setColor("Green");
                            }
                            if(col.toLowerCase().equals("red"))
                            {
                                game.getTopCard().setColor("Red");
                            }
                            if(col.toLowerCase().equals("yellow"))
                            {
                                game.getTopCard().setColor("Yellow");
                            }
                        }
                        */
                        if(hand2.matchCardInHand(str) == new Card("No", "Card"))
                        {
                            game.getTopCard();
                        }
                        else
                        {
                            game.placeCard(hand2.matchCardInHand(str));
                            hand2.getHand().remove(hand2.matchCardInHand(str));
                        }
                    }
                    else
                    {
                        System.out.println("Due to your card not matching the color or symbol of the top card your turn will be skipped.");
                    }
                    if(hand2.getHand().size()==0)
                    {
                            System.out.println("Congratulations, " + username + ", you have won the game!!!");
                            userWin = 1;
                    }
                }    
                //bot's turn
                if(!(str.contains("skip")) && !(str.contains("reverse")) && !(str.contains("draw 2")) && userWin != 1)
                {
                    System.out.println("\nTop Card: " + game.getTopCard());
                    ArrayList<Card> botsPlayable = new ArrayList<Card>();
                    for(int i = 0;i<hand1.getHand().size(); i++)
                    {
                        if(game.isPlaceable(hand1.getHand().get(i)))
                        {
                            botsPlayable.add(hand1.getHand().get(i));
                        }
                    }
                    if(botsPlayable.size()== 0)
                    {
                        game.drawCards(1, hand1);
                        System.out.println("The bot has drawn a card\n");
                        botCard = hand1.getHand().get(hand1.getHand().size()-1);
                        if(hand1.getHand().get(hand1.getHand().size()-1).getSymbol().toLowerCase().equals("draw 2"))
                        {
                            game.placeCard(botCard);
                            game.drawCards(2, hand1);
                            System.out.println("The bot has placed the card it drew");
                            System.out.println(game.getTopCard());
                        }
                        else if(hand1.getHand().get(hand1.getHand().size()-1).getSymbol().toLowerCase().equals("skip"))
                        {
                            game.placeCard(botCard);
                            System.out.println("The bot has placed the card it drew");
                            System.out.println("Top Card: " + game.getTopCard());
                        }
                        else if(hand1.getHand().get(hand1.getHand().size()-1).getSymbol().toLowerCase().equals("reverse"))
                        {
                            game.placeCard(botCard);
                        }
                        
                    }
                    else
                    {
                        botCard = botsPlayable.get((int)(Math.random()*(botsPlayable.size())));
                        game.placeCard(botCard);
                        hand1.getHand().remove(botCard);
                        System.out.println("The bot placed " + game.getTopCard() + "\n");
                        if(game.getTopCard().getSymbol().toLowerCase().equals("draw 2"))
                        {
                            game.drawCards(2, hand2);
                            System.out.println("You have drawn 2 cards");
                        }
                        else if(game.getTopCard().getSymbol().toLowerCase().equals("skip"))
                        {
                            System.out.println("You have been skipped");
                        }
                        else if(game.getTopCard().getSymbol().toLowerCase().equals("reverse"))
                        {
                            System.out.println("You have been skipped");
                        }   
                        else if(game.getTopCard().getSymbol().toLowerCase().equals("wild"))
                        {
                            //really wanted to create method to find color that is most present in the bot's hand but wasn't able to
                            int rand = (int)(Math.random()*4+1);
                            if(rand ==1)
                            {
                                game.getTopCard().setColor("Blue");
                            }
                            if(rand == 2)
                            {
                                game.getTopCard().setColor("Green");
                            }
                            if(rand ==3)
                            {
                                game.getTopCard().setColor("Red");
                            }
                            if(rand ==4)
                            {
                                game.getTopCard().setColor("Yellow");
                            }
                        }
                        /*
                        else if(game.getTopCard().getSymbol().toLowerCase().equals("draw 4"))
                        {
                            game.drawCards(4, hand2);
                            System.out.println("You drew 4 cards");
                            int rand = (int)(Math.random()*4+1);
                            if(rand ==1)
                            {
                                game.getTopCard().setColor("Blue");
                            }
                            if(rand == 2)
                            {
                                game.getTopCard().setColor("Green");
                            }
                            if(rand ==3)
                            {
                                game.getTopCard().setColor("Red");
                            }
                            if(rand ==4)
                            {
                                game.getTopCard().setColor("Yellow");
                        }
                        */
                    }
                }
                    if(hand1.getHand().size()==0)
                    {
                        System.out.println("Sorry " + username + ", the bot has won the game... Better luck next time!");
                        botWin = 1;
                    }
            }
            break;
        }
    }
}
/*
 * Sample Output:
Please enter a display name of your choosing: Josh

Top Card: Yellow 4
Your options are: 
 * Green 2
 * Green 8
 * Blue 2
 * Blue 3
 * Green 4
 * Green 9
 * Blue 0
 * Draw
 * or Quit
Enter the option of your choosing: 
green 4

Top Card: Green 4
The bot placed Green 6

Top Card: Green 6
Your options are: 
 * Green 2
 * Green 8
 * Blue 2
 * Blue 3
 * Green 9
 * Blue 0
 * Draw
 * or Quit
Enter the option of your choosing: 
green 8

Top Card: Green 8
The bot placed Green 7

Top Card: Green 7
Your options are: 
 * Green 2
 * Blue 2
 * Blue 3
 * Green 9
 * Blue 0
 * Draw
 * or Quit
Enter the option of your choosing: 
green 2

Top Card: Green 2
The bot placed Green 1

Top Card: Green 1
Your options are: 
 * Blue 2
 * Blue 3
 * Green 9
 * Blue 0
 * Draw
 * or Quit
Enter the option of your choosing: 
green 9

Top Card: Green 9
The bot has drawn a card

The bot has placed the card it drew
Top Card: Blue Skip

Top Card: Blue Skip
The bot placed Blue Skip

You have been skipped

Top Card: Blue Skip
The bot placed Blue Skip

You have been skipped

Top Card: Blue Skip
The bot has drawn a card

Top Card: Blue Skip
Your options are: 
 * Blue 2
 * Blue 3
 * Blue 0
 * Draw
 * or Quit
Enter the option of your choosing: 
blue 0

Top Card: Blue 0
The bot placed Blue 1

Top Card: Blue 1
Your options are: 
 * Blue 2
 * Blue 3
 * Draw
 * or Quit
Enter the option of your choosing: 
blue 2

Top Card: Blue 2
The bot has drawn a card

Top Card: Blue 2
Your options are: 
 * Blue 3
 * Draw
 * or Quit
Enter the option of your choosing: 
blue 3
Congratulations, Josh, you have won the game!!!
 */