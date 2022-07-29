package bb.roborally.server.game.deck;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.server.game.cards.Virus;
import bb.roborally.server.game.cards.Worm;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 */
public class WormDeck {

    ArrayList<PlayingCard> wormDeck = new ArrayList<>();

    public WormDeck(){
        fillWormDeck();
    }
    public void fillWormDeck(){
        for(int i = 0; i < 6; i++){
            wormDeck.add(new Worm());
        }
    }

    public void reset() {
        wormDeck.clear();
        fillWormDeck();
    }

    public Worm drawWormsCard(){
        if(wormDeck.get(0) != null){
            return (Worm) wormDeck.remove(0);
        }
        return null;
    }

    public void addWormCard(){
        wormDeck.add(new Worm());
    }

    public ArrayList<PlayingCard> getWormDeck() {
        return wormDeck;
    }
}
