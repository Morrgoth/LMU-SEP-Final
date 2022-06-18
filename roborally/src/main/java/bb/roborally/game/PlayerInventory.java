package bb.roborally.game;

import bb.roborally.game.cards.*;


/**
 * contains player specific items and Cards
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class PlayerInventory {
    private Deck<PlayingCard> discardPile;
    private Deck<PlayingCard> drawPile;

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

    public void addCard(DamageCard damageCard){

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
}


