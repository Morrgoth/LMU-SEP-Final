package bb.roborally.server.game.deck;


import bb.roborally.protocol.gameplay.ReplaceCard;
import bb.roborally.server.game.cards.*;

import java.util.ArrayList;
import java.util.Collections;


/**
 * creates each CardDeck and sets the limitnumber of Cards in each Deck
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class ProgrammingDeck {

    private final int HAND_CARD_COUNT = 9;
    private final ArrayList<PlayingCard> deck = new ArrayList<>();

    public ProgrammingDeck() {
        initializeProgrammingDeck();
        shuffle();
    }
    public void reset() {
        initializeProgrammingDeck();
    }

    public void shuffle(){
        for (PlayingCard card: deck) {
            card.setDiscarded(false);
        }
        Collections.shuffle(deck);
    }

    public PlayingCard draw(){
        for (PlayingCard card: deck) {
            if (card.isAvailable()) {
                card.setActive(true);
                return card;
            }
        }
        return null;
    }

    public boolean isReshuffleNeeded() {
        return getAvailableCardCount() < HAND_CARD_COUNT;
    }

    public ArrayList<PlayingCard> drawHand() {
        ArrayList<PlayingCard> handCards = new ArrayList<>();
        for(int i = 0; i < HAND_CARD_COUNT; i++) {
            PlayingCard card = draw();
            if (card != null) {
                handCards.add(card);
            } else {
                shuffle();
                handCards.add(draw());
            }
        }
        return handCards;
    }

    public void addCard(PlayingCard playingCard) {
        deck.add(playingCard);
    }

    public void addCard(PlayingCard playingCard, boolean discarded){
        if (discarded) {
            playingCard.setActive(false);
            playingCard.setDiscarded(true);
        }
        deck.add(playingCard);
    }

    public void removeCard(PlayingCard playingCard) {
        deck.remove(playingCard);
    }

    public int getAvailableCardCount() {
        int count = 0;
        for (PlayingCard card: deck) {
            if (card.isAvailable()) {
                count += 1;
            }
        }
        return count;
    }

    public ArrayList<PlayingCard> getAvailableCards() {
        ArrayList<PlayingCard> cards = new ArrayList<>();
        for (PlayingCard card: deck) {
            if (card.isAvailable()) {
                cards.add(card);
            }
        }
        return cards;
    }

    public ArrayList<PlayingCard> getDiscardPile() {
        ArrayList<PlayingCard> cards = new ArrayList<>();
        for (PlayingCard card: deck) {
            if (card.isDiscarded()) {
                cards.add(card);
            }
        }
        return cards;
    }

    public ArrayList<PlayingCard> getActiveCards() {
        ArrayList<PlayingCard> cards = new ArrayList<>();
        for (PlayingCard card: deck) {
            if (card.isActive()) {
                cards.add(card);
            }
        }
        return cards;
    }

    public String[] generateRandomProgram() {
        ArrayList<PlayingCard> activeCards = getActiveCards();
        Collections.shuffle(activeCards);
        String[] program = new String[5];
        for (int i = 0; i < 5; i++) {
            program[i] = activeCards.get(i).getName();
        }
        return program;
    }

    private void initializeProgrammingDeck() {
        deck.clear();
        for (int i = 0; i < 5; i++) {
            deck.add(new Move1());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new Move2());
        }
        deck.add(new Move3());
        for (int i = 0; i < 3; i++) {
            deck.add(new TurnRight());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new TurnLeft());
        }
        deck.add(new BackUp());
        deck.add(new PowerUp());
        deck.add(new Again());
        deck.add(new Again());
        deck.add(new UTurn());
    }

    public ReplaceCard replaceCard(int register) {
        // TODO: implement
        return null;
    }
}
