package bb.roborally.server.game;


import bb.roborally.server.game.deck.ProgrammingDeck;

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

    private final ProgrammingDeck programmingDeck = new ProgrammingDeck();
    private int energy;
    private int checkPointTokens;

    public PlayerInventory(){
        //this.temporaryUpgradeCards = new ProgrammingDeck<>();
        //this.permanentUpgradeCards = new ProgrammingDeck<>();
        //this.permanentUpgradeCards.setLimit(3);
        //this.temporaryUpgradeCards.setLimit(3);
        setEnergy(5);
    }

    //nine programming-cards on hand
    public void buyUpgradeCard(){

    }
    public int getCheckPointTokens() {
        return checkPointTokens;
    }
    public void addCheckPointTokens() {
        this.checkPointTokens += 1;
    }

    public ProgrammingDeck getProgrammingDeck() {
        return programmingDeck;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void increaseEnergyCubeAmountBy(int increase) {
        this.energy += increase;
    }

    public void decreaseEnergyCubeAmountBy(int decrease) { this.energy -= decrease; }

    //public PlayingCard drawCard(){
    //    return null;
    //}
//
    //public static void addCard(DamageCard.CardType damageCard){
    //    drawPile.add(damageCard);
    //}
//
    //public ProgrammingDeck<UpgradeCard> getTemporaryUpgradeCards() {
    //    return temporaryUpgradeCards;
    //}
//
    //public ProgrammingDeck<UpgradeCard> getPermanentUpgradeCards() {
    //    return permanentUpgradeCards;
    //}
//
    //public ProgrammingDeck<PlayingCard> getDrawPile() {
    //    return drawPile;
    //}
//
    //public void setDrawPile(ProgrammingDeck<PlayingCard> drawPile) {
    //    this.drawPile = drawPile;
    //}
//
    //public static int getClientID() {
    //    return clientID;
    //}
//
    //public void setClientID(int clientID) {
    //    PlayerInventory.clientID = clientID;
    //}
}

