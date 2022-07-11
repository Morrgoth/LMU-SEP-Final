package bb.roborally.server.game;


import bb.roborally.protocol.Message;
import bb.roborally.protocol.game_events.Energy;
import bb.roborally.server.game.deck.ProgrammingDeck;
import bb.roborally.server.game.EnergyCube;

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

    public ProgrammingDeck getProgrammingDeck() {
        return programmingDeck;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

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


