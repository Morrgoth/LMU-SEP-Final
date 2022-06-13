package bb.roborally.game.cards;


import java.util.ArrayList;
import java.util.Collections;


/**
 * creates each CardDeck and sets the limitnumber of Cards in each Deck
 * @param <Card>
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class  Deck <Card>{

    //maximale Anzahl an Karten in einem Deck, bei -1 (Default) --> kein Limit für Deck
    private int limit = -1;
    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<>();
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card draw(){
        return deck.remove(0);
    }

    public Deck<Card> drawHand(int count){
        Deck<Card> handCards = new Deck<>();
        for(int i = 0; i < count; i++){
            handCards.addCard(draw());
        }
        return handCards;
    }

    public void addCard(Card card){

    }
}
