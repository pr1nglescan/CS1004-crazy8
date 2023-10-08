/** Player.java
*   Author: David Kuang dk3260
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;
    private Card cardChoice;
    private boolean cont;
    private boolean nullDraw; //if player draws but pile is gone

    public Player(){
        hand = new ArrayList<Card>();   
        input = new Scanner(System.in);
        cont = true;
        nullDraw = false;
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Card faceCard, Deck cards){
        cardChoice = faceCard;
        cont = true;
        
        this.turnInput(cards);

        if (!nullDraw){
            System.out.println("~~You played: " + cardChoice.toString() + "~~");
            if (cardChoice.getRank() == 8)
                cardChoice.changeSuit();
            return cardChoice;
        }
        else
            return null;
    }

    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        String handString = "";
        int option;
        for (int i = 0; i < hand.size(); i++){
            option = i + 1;
            handString += Integer.toString(option) + ". " + 
            hand.get(i).toString() + " \n";
        }
        
        return handString;
    }

    //takes player's input and calls appropriate methods
    private void turnInput(Deck drawPile){
        int entry;
        System.out.println("Which card would you like to play?" +
        "\nEnter the number next to the card. Enter '0' to draw.");
        do{
            System.out.print("Your hand: \n" + this.handToString());
            entry = input.nextInt();
            
            if (entry == 0 && drawPile.canDeal())
                hand.add(drawPile.deal());
            else if (entry == 0 && !drawPile.canDeal())
                nullDraw = true;
            else
                cont = this.cardChecker(entry);
        } while (cont && !nullDraw);
    }

    //checks if player's card choice is playable
    private boolean cardChecker(int cardEntry){
        boolean repeat = true;
        int cardNumber = cardEntry - 1;
        Card cardPick = hand.get(cardNumber);

        if (!cardPick.playable(cardChoice))
            System.out.println("This card is not playable.");
        else{
            hand.remove(cardNumber);
            cardChoice = cardPick;
            repeat = false;
        }

        return repeat;
    }

} // end
