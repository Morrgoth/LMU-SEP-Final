package bb.roborally.server.game.deck;

import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Virus;

import java.util.ArrayList;

public class SpamDeck {

    public SpamDeck(){}
    public void fillSpamDeck(ArrayList<PlayingCard> spamDeck){
        for(int i = 0; i < 37; i++){
            spamDeck.add(new Virus());
        }
    }


    public void drawSpamCard(ArrayList<PlayingCard> spamDeck){
        spamDeck.remove(0);
    }

    public void addSpamCard(ArrayList<PlayingCard> spamDeck){
        spamDeck.add(new Virus());
    }
}
