package bb.roborally.server.game.deck;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Virus;
import bb.roborally.server.game.cards.Worm;

import java.util.ArrayList;

public class WormDeck {

    public void fillWormDeck(ArrayList<PlayingCard> wormDeck){
        for(int i = 0; i < 6; i++){
            wormDeck.add(new Worm());
        }
    }

    public void drawWormsCard(ArrayList<PlayingCard> wormDeck){
        wormDeck.remove(0);
    }

    public void addWormCard(ArrayList<PlayingCard> wormDeck){
        wormDeck.add(new Worm());
    }
}
