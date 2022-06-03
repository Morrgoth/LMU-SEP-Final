package bb.roborally.game;

import bb.roborally.game.cards.*;


/**
 * contains player specific items and Cards
 */
public class PlayerInventory {
    private Deck<PlayingCard> discardPile;
    private Deck<PlayingCard> drawPile;
    private int energyCubeAmount;
    private int checkPointTokens;

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


    public int getEnergyCubeAmount() {
        return energyCubeAmount;
    }

    public void setEnergyCubeAmount(int energyCubeAmount) {
        this.energyCubeAmount = energyCubeAmount;
    }

    public int getCheckPointTokens() {
        return checkPointTokens;
    }

    public void setCheckPointTokens(int checkPointTokens) {
        this.checkPointTokens = checkPointTokens;
    }

    public Deck<UpgradeCard> getTemporaryUpgradeCards() {
        return temporaryUpgradeCards;
    }

    public Deck<UpgradeCard> getPermanentUpgradeCards() {
        return permanentUpgradeCards;
    }
}


