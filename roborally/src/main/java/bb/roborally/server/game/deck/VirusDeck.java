package bb.roborally.server.game.deck;

import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Virus;

import java.util.ArrayList;

public class VirusDeck {

    public void fillVirusDeck(ArrayList<PlayingCard> virusDeck){
        for(int i = 0; i < 18; i++){
            virusDeck.add(new Virus());
        }
    }
}
