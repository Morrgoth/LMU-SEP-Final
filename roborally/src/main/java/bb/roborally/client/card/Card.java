package bb.roborally.client.card;

import bb.roborally.server.game.cards.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Card {
    private boolean marked = false;
    private final StringProperty cardName = new SimpleStringProperty("");
    private String type = null;

    public void setType(String type) {
        this.type = type;
        if ("MoveI".equals(type)) {
            cardName.set("Move I");
        } else if ("MoveII".equals(type)) {
            cardName.set("Move II");
        } else if ("MoveIII".equals(type)) {
            cardName.set("Move III");
        } else if ("TurnLeft".equals(type)) {
            cardName.set("Turn Left");
        } else if ("TurnRight".equals(type)) {
            cardName.set("Turn Right");
        } else if ("UTurn".equals(type)) {
            cardName.set("U Turn");
        } else if ("BackUp".equals(type)) {
            cardName.set("Back Up");
        } else if ("PowerUp".equals(type)) {
            cardName.set("Power Up");
        } else {
            cardName.set(type);
        }
    }

    public static Card fromString(String type) {
        Card card = new Card();
        card.setType(type);
        return card;

    }

    public static ArrayList<Card> toCards(String[] cards) {
        ArrayList<Card> hand = new ArrayList<>();
        for (String cardName: cards) {
            hand.add(fromString(cardName));
        }
        return hand;
    }

    public String getType() {
        return type;
    }

    public boolean isEmpty() {
        return type.isEmpty();
    }

    public String getCardName() {
        return cardName.get();
    }

    public StringProperty cardNameProperty() {
        return cardName;
    }

    public String getResourcePath() {
        if ("MoveI".equals(type)) {
            return "/programmingCardsImage/move1.png";
        } else if ("MoveII".equals(type)) {
            return "/programmingCardsImage/move2.png";
        } else if ("MoveIII".equals(type)) {
            return "/programmingCardsImage/move3.png";
        } else if ("TurnLeft".equals(type)) {
            return "/programmingCardsImage/turnLeft.png";
        } else if ("TurnRight".equals(type)) {
            return "/programmingCardsImage/turnRight.png";
        } else if ("UTurn".equals(type)) {
            return "/programmingCardsImage/uTurn.png";
        } else if ("BackUp".equals(type)) {
            return "/programmingCardsImage/backUp.png";
        } else if ("PowerUp".equals(type)) {
            return "/programmingCardsImage/powerUp.png";
        } else {
            return "/programmingCardsImage/empty.png";
        }
    }

    public static boolean isMarked() {
        return marked;
    }
}



