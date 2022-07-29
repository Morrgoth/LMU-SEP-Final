package bb.roborally.client.programming_interface;

import bb.roborally.client.card.Card;
import bb.roborally.protocol.gameplay.YourCards;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 *
 * @author Bence Ament
 * @author Tolga Engin
 */
public class PlayerHand {

    private final ArrayList<Card> yourCards = new ArrayList<>();
    private final ObservableList<Card> selectableCards = FXCollections.observableArrayList(yourCards);
    private final ProgramModel programModel = new ProgramModel();

    public ArrayList<Card> getYourCards() {
        return yourCards;
    }

    public ObservableList<Card> getSelectableCards() {
        return selectableCards;
    }

    private final BooleanProperty reset = new SimpleBooleanProperty(false);

    /**
     * @param message
     * sets the newly received cards of the user
     */
    public void update(YourCards message) {
        this.yourCards.clear();
        this.yourCards.addAll(Card.toCards(message.getCardsInHand()));
        this.selectableCards.clear();
        this.selectableCards.addAll(yourCards);
    }

    public boolean isProgramReady() {
        return programModel.isReady();
    }

    public ProgramModel getProgram() {
        return programModel;
    }

    public boolean isReset() {
        return reset.get();
    }

    public BooleanProperty resetProperty() {
        return reset;
    }

    public void removeSelectable(Card selected) {
        int i = 0;
        for (Card card: selectableCards) {
            if (card.getCardName().equals(selected.getCardName())) {
                selectableCards.remove(i);
                return;
            }
            i += 1;
        }
    }
}
