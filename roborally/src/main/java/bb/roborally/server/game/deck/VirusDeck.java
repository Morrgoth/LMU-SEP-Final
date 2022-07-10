package bb.roborally.server.game.deck;

import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Virus;

import java.util.ArrayList;

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

    public void drawVirusCard(){
        virusDeck.remove(0);
    }

    public void addVirusCard(){
        virusDeck.add(new Virus());
    }

    public ArrayList<PlayingCard> getVirusDeck(){
        return virusDeck;
    }

}
