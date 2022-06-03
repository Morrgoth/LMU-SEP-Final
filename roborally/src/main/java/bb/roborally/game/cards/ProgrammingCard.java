package bb.roborally.game.cards;


/**
 * all kind of Programming Cards
 */
public class ProgrammingCard extends PlayingCard{

    public enum CardType{
        TURN_LEFT,

        TURN_RIGHT,

        FORWARD_ONE,

        FORWARD_TWO,

        FORWARD_THREE,

        REPEAT,

        U_TURN,

        POWER_UP,

        MOVE_BACKWARDS
    }
}
