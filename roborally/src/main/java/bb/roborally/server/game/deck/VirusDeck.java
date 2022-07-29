package bb.roborally.server.game.deck;

import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.server.game.cards.Virus;

import java.util.ArrayList;

/**
 * @author Veronika Heckel
 */
public class VirusDeck {
    ArrayList<PlayingCard> virusDeck = new ArrayList<>();
    public VirusDeck(){
        fillVirusDeck();
    }

    public void fillVirusDeck(){
        for(int i = 0; i < 18; i++){
            virusDeck.add(new Virus());
        }
    }

    public Virus drawVirusCard(){
        if(virusDeck.get(0) != null){
            return (Virus) virusDeck.remove(0);
        }
        return null;
    }

    public ArrayList<PlayingCard> addVirusCard(){
        virusDeck.add(new Virus());
        return null;

    }

    public ArrayList<PlayingCard> getVirusDeck(){
        return virusDeck;
    }

    public void reset() {
        virusDeck.clear();
        fillVirusDeck();
    }

}
