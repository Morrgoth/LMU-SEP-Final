package bb.roborally.server.game.deck;

import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Virus;

import java.util.ArrayList;

public class TrojanDeck {
    public void fillTrojanDeck(ArrayList<PlayingCard> trojanDeck){
        for(int i = 0; i < 12; i++){
            trojanDeck.add(new Virus());
        }
    }
}
