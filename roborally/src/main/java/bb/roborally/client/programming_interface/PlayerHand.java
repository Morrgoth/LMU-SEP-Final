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
    private final ObservableList<PlayingCard> yourCards = FXCollections.observableArrayList();
    public ObservableList<PlayingCard> getYourCards() {
        return yourCards;
    }
    private final BooleanProperty reset = new SimpleBooleanProperty(false);

    public void update(YourCards message) {
        this.yourCards.clear();
        yourCards.addAll(PlayingCard.toPlayingCards(message.getCardsInHand()));
    }

    public boolean isProgramReady() {
        final int PROGRAM_LENGTH = 5;
        return yourCards.filtered(PlayingCard::isMarked).size() == PROGRAM_LENGTH;
    }

    public ArrayList<PlayingCard> getProgram() {
        ArrayList<PlayingCard> program = new ArrayList<>();
        for (PlayingCard card: yourCards) {
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
