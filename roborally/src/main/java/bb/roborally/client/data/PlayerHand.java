package bb.roborally.client.data;

import bb.roborally.protocol.gameplay.YourCards;
import bb.roborally.server.game.cards.PlayingCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PlayerHand {
    private final ObservableList<PlayingCard> yourCards = FXCollections.observableArrayList();
    public ObservableList<PlayingCard> getYourCards() {
        return yourCards;
    }

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
        for (PlayingCard playingCard: yourCards) {
            if (playingCard.isMarked()) {
                program.add(playingCard);
            }
        }
        return program;
    }
}
