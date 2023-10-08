/** Card.java
*   Author: David Kuang dk3260
*   
*   
*   Models a typical playing card
*
*/
import java.util.Scanner;

class Card{
    
    private char suit;
    private int rank;
    private boolean validInput;
    private Scanner input;

    // Initializes a card instance
    public Card(char suit, int rank){
        this.suit = suit;
        this.rank = rank;
        validInput = false;
        input = new Scanner(System.in);
    }

    // Accessor for suit
    public char getSuit(){
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        String suitWord;
        String[] rankWord = 
        {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", 
        "Queen", "King"};

        suitWord = suitToWord(suit);

        return rankWord[rank - 1] + " of " + suitWord;
    }

    //prompts user to change suit for the crazy 8
    public void changeSuit(){
        System.out.println("What suit would you like to play?" + 
        " Enter 'c' for clubs, 'd' for diamonds, 'h' for hearts, & " +
        "'s' for spades.");

        suit = this.inputChecker(); //verifies entry is correct
        System.out.println("**New suit is: " + this.suitToWord(suit) + "**");
    }

    //for computer to change suit for the crazy 8
    public void changeSuit(char entry){
        suit = entry;
        System.out.println("**New suit is: " + this.suitToWord(suit) + "**");
    }

    private String suitToWord(char entry){
        String suitWord;
        if (entry == 'c')
            suitWord = "Clubs";
        else if (entry == 'd')
            suitWord = "Diamonds";
        else if (entry == 'h')
            suitWord = "Hearts";
        else
            suitWord = "Spades";

        return suitWord;
    }

    //checks if implicit card is compatible w/ argument card
    public boolean playable(Card top){
        char suitTop = top.getSuit();
        int rankTop = top.getRank();

        if (rank == 8)
            return true;
        else if (suit == suitTop || rank == rankTop)
            return true;
        else
            return false;
    }

    //checks if suit choice for the crazy 8 is valid
    private char inputChecker(){
        char suitChoice = '0';

        while (!validInput){
            suitChoice = input.next().charAt(0);
            if (suitChoice == 'c' || suitChoice == 'd' || suitChoice == 'h' ||
            suitChoice == 's')
                validInput = true;
            else{
                System.out.println("Invalid input." +
                " Please enter 'c', 'd', 'h', or 's'.");  
            }
        }
        return suitChoice;
    }
}