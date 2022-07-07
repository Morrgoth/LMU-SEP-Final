package bb.roborally.gui.data;

import bb.roborally.data.messages.gameplay.YourCards;
import bb.roborally.game.cards.PlayingCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerHand {
    private final ObservableList<PlayingCard> yourCards = FXCollections.observableArrayList();
    private final ObservableList<PlayingCard> yourProgram = FXCollections.observableArrayList();

    public ObservableList<PlayingCard> getYourCards() {
        return yourCards;
    }

    public ObservableList<PlayingCard> getYourProgram() {
        return yourProgram;
    }

    public void update(YourCards message) {
        this.yourProgram.clear();
        this.yourCards.clear();
        yourCards.addAll(PlayingCard.toPlayingCards(message.getCardsInHand()));
    }

    public void addToProgram(PlayingCard playingCard) {
        final int programLength = 5;
        if (playingCard.isAvailable()) {
            if (yourProgram.size() < programLength) {
                playingCard.setActive(true);
                yourProgram.add(playingCard);
            }

        }
    }

    public void removeFromProgram(PlayingCard playingCard) {
        yourProgram.removeIf(card -> playingCard == card);
        playingCard.setActive(false);
    }
}
