/** Deck.java
*   Author: David Kuang dk3260
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/

class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from

    // constructs a default Deck
    public Deck(){
        char[] suits = {'c', 'd', 'h', 's'};
        deck = new Card[52];
        top = 51;

        int j = 0;
        for (char suit : suits){
            for (int i = 1; i <= 13; i++){
                deck[j] = new Card(suit, i);
                j++;
            }
        }
    }

    // Deals the top card off the deck
    public Card deal(){
        Card topCard = this.deck[top];
        top--; 
        return topCard;
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        if (top >= 0)
            return true;
        else
            return false;
    }

    // Shuffles the deck
    public void shuffle(){
        int spot1, spot2;
        Card card1, card2;

        for (int i = 0; i < 300; i++){
            spot1 = (int) (Math.random()*52);
            spot2 = (int) (Math.random()*52);
            card1 = deck[spot1];
            card2 = deck[spot2];

            Card temp = card1;
            deck[spot1] = card2;
            deck[spot2] = temp;
        }
    }

    // Returns a string representation of the whole deck
    public String toString(){
        String deckString = "";
        for (Card card : deck){
           deckString += card.toString() + " \n";
        }

        return deckString;
    }

}