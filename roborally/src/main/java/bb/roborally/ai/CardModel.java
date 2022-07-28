package bb.roborally.ai;

public record CardModel(CardType type) {
    public enum CardType {
        AGAIN("Again"), BACKUP("BackUp"), MOVE1("MoveI"), MOVE2("MoveII"),
        MOVE3("MoveIII"), POWERUP("PowerUp"), SPAM("Spam"), TROJAN("Trojan"),
        TURN_LEFT("TurnLeft"), TURN_RIGHT("TurnRight"), U_TURN("UTurn"),
        VIRUS("Virus"), WORM("Worm");

        private final String type;

        CardType(final String type) {
            this.type = type;
        }

        public static CardType fromString(String card) {
            for(CardType type : values())
                if(type.toString().equals(card)) return type;
            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    public static CardModel[] fromStringArray(String[] cards) {
        CardModel[] cardModels = new CardModel[cards.length];
        for (int i = 0; i < cards.length; i++) {
            cardModels[i] = new CardModel(CardType.fromString(cards[i]));
        }
        return cardModels;
    }
}
