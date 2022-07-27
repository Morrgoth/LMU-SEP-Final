package bb.roborally.client.programming_interface;

import bb.roborally.client.card.Card;
import bb.roborally.protocol.gameplay.YourCards;
import bb.roborally.server.game.cards.PlayingCard;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PlayerHand {
    private final ObservableList<Card> yourCards = FXCollections.observableArrayList();
    private ProgramModel programModel = new ProgramModel();


    public ObservableList<Card> getYourCards() {
        return yourCards;
    }
    private final BooleanProperty reset = new SimpleBooleanProperty(false);

    public void update(YourCards message) {
        this.yourCards.clear();
        yourCards.addAll(Card.toCards(message.getCardsInHand()));
    }

    public boolean isProgramReady() {
            if(programModel.isReady()){
                return true;
            }
        return false;
        //final int PROGRAM_LENGTH = 5;
        //return yourCards.filtered(PlayingCard::isMarked).size() == PROGRAM_LENGTH;
    }

    public ArrayList<Card> getProgram() {
        ArrayList<Card> program = new ArrayList<>();
        for (Card card: yourCards) {
            if (card.isMarked()) {
                program.add(card);
            }
        }
        return program;
    }

    public boolean isReset() {
        return reset.get();
    }

    public BooleanProperty resetProperty() {
        return reset;
    }
}
