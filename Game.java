/** Game.java
*   Author: David Kuang
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    private Card compCard; //computer's card choice
    private boolean nullDraw; //if players try to draw from empty deck
    
    // sets up the Game object for play
    public Game(){
        p1 = new Player();
        cards = new Deck();
        input = new Scanner(System.in);
        compHand = new ArrayList<>();
        this.gameSetup();
        nullDraw = false;
    }

   

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        System.out.println("Welcome to Crazy 8's!");
        System.out.println("\n~~First Card: " + faceup.toString() + "~~");

        this.gamePlay();

        this.playerWinner();

        boolean repeat = this.playAgain();
        return repeat;
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
    private Card computerTurn(){
        int count = this.compDraw(); //counts how many cards computer draws 
        if (count >0){
            System.out.println("Computer draws " + count + 
            " times.");
        }
        if (!nullDraw){
            System.out.println("~~Computer Played: " 
            + compCard.toString() + "~~");
                if (compCard.getRank() == 8)
                    compCard.changeSuit(this.compEight());
            System.out.println("Computer hand: " + compHand.size() + " cards");
        }
        return compCard;
    }

    //draws cards for compHand until 1st playable card is added
    private int compDraw(){
        boolean draw = this.handScan(); //checks hand for playable cards
        int drawCount = 0; //counts # of draws
        while (!draw && cards.canDeal()){
            compCard = cards.deal();
            compHand.add(compCard);
            if (compCard.playable(faceup)){ //plays the playable card
                compHand.remove(compHand.size() - 1);
                draw = true;
            }
            drawCount++;
        }
        if (!draw && !cards.canDeal())
            nullDraw = true;
        return drawCount;
    }

    //random suit selection method for computer's crazy 8
    private char compEight(){
        char[] suits = {'c', 'd', 'h', 's'};
        int suitPick = (int) (Math.random() * 4);
        char suitChoice = suits[suitPick];
        return suitChoice;
    }
    
    //deals cards & selects 1st faceup card
    private void gameSetup(){
        cards.shuffle();
        for (int i = 0; i < 7; i++){
            p1.addCard(cards.deal());
            compHand.add(cards.deal());
        }
        faceup = cards.deal();
    }

    //loop that alternates between player and computer turns
    private void gamePlay(){
        int counter = 0;
        while ((p1.getHand().size() * compHand.size()) != 0 && !nullDraw){
            if (counter % 2 == 0){
                faceup = p1.playsTurn(faceup, cards);
                if (faceup == null)
                    nullDraw = true;
            }
            else
                faceup = this.computerTurn();
            counter++;
        }
    }

    //returns whether compHand has a playable card or not
    private boolean handScan(){
        boolean noCard = this.handRemove();
        
        if (noCard)
            return false;
        else
            return true;
    }

    //scans through compHand, plays 1st playable card
    private boolean handRemove(){
        int i = 0;
        boolean cont = true;

        while (i < compHand.size() && cont == true){
            if (compHand.get(i).playable(faceup)){
                compCard = compHand.get(i);
                compHand.remove(i);
                cont = false;
            }
            i++;
        }

        return cont;
    }

    //displays winner and score
    private void playerWinner(){
        if (nullDraw)
            System.out.println("Draw pile is exhausted. Game over!");
        if (compHand.size() == p1.getHand().size())
            System.out.println("You are at a draw.");
        else if (p1.getHand().size() < compHand.size())
            System.out.println("Congratulations! You win!");
        else
            System.out.println("Sorry, you lose.");
        
        System.out.println("You: " + p1.getHand().size() + " card(s)" + 
        "\nComputer: " + compHand.size() + " card(s)");
        
        if (p1.getHand().size() < compHand.size())
            easterEgg();
    }

    //prompts user for another round
    private boolean playAgain(){
        System.out.println("Would you like to play another round?" + 
        "\nPress 1 for yes, 2 for no.");
        int answer = input.nextInt();
        if (answer == 1)
            return true;
        else
            return false;
    }

    public void easterEgg(){
        System.out.println("Reward: Would you like to hear a joke? y/n");
        String answer = input.next();

        if (!answer.equals("y"))
            System.out.println("Too bad. Here goes: \n ");
        System.out.println("Why did the two fours skip lunch?");
        answer = input.next();
        System.out.println("Because they already eight!!! LOL");
    }


}