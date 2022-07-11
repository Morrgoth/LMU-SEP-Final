package bb.roborally.server.game.deck;

import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.server.game.cards.Virus;

import java.util.ArrayList;

public class SpamDeck {

    ArrayList<PlayingCard> spamDeck = new ArrayList<>();

    public SpamDeck(){
        fillSpamDeck();
    }
    public void fillSpamDeck(){
        for(int i = 0; i < 37; i++){
            spamDeck.add(new Spam());
        }
    }

    public void drawSpamCard(){
        spamDeck.remove(0);
    }

    public void addSpamCard(){
        spamDeck.add(new Virus());
    }
    public ArrayList<PlayingCard> getSpamDeck(){
        return spamDeck;
    }
}
