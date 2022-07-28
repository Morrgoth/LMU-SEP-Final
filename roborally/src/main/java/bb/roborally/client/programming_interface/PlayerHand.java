package bb.roborally.client.programming_interface;

import bb.roborally.client.card.Card;
import bb.roborally.protocol.gameplay.YourCards;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

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
}
