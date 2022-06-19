package bb.roborally.game;

import bb.roborally.game.cards.*;

import java.util.ArrayList;


/**
 * contains player specific items and Cards
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class PlayerInventory {
    private static int clientID = Player.getClientID();

    private Deck<PlayingCard> discardPile;
    private static Deck<PlayingCard> drawPile;

    private Deck<PlayingCard> hand;
    private final Deck<UpgradeCard> temporaryUpgradeCards;
    private final Deck<UpgradeCard> permanentUpgradeCards;

    public PlayerInventory(){
        this.temporaryUpgradeCards = new Deck<>();
        this.permanentUpgradeCards = new Deck<>();
        this.permanentUpgradeCards.setLimit(3);
        this.temporaryUpgradeCards.setLimit(3);
    }

    //nine programming-cards on hand
    public void buyUpgradeCard(){

    }

    public PlayingCard drawCard(){
        return null;
    }

    public static void addCard(DamageCard damageCard){
        drawPile.add(damageCard);
    }

    public Deck<UpgradeCard> getTemporaryUpgradeCards() {
        return temporaryUpgradeCards;
    }

    public Deck<UpgradeCard> getPermanentUpgradeCards() {
        return permanentUpgradeCards;
    }

    public Deck<PlayingCard> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(Deck<PlayingCard> drawPile) {
        this.drawPile = drawPile;
    }

    public static int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        PlayerInventory.clientID = clientID;
    }
}


