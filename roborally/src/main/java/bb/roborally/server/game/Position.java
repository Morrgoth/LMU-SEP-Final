package bb.roborally.server.game;

import javafx.geometry.Pos;

import java.util.ArrayList;

/**
 * creating a certain position as reference for each item on the board. Each item on the board has a certain position. This class
 * makes it possible to compare them and to make updates during the game.
 *
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Philipp Keyzman
 */
public class Position {
    // ORIENTATION : LEFT,RIGHT
    private int x;
    //ORIENTATION : FRONT,BACK
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || ! (obj instanceof Position)) {
            return false;
        }

        Position other = (Position) obj;

        return (this.x == other.getX()) && (this.y == other.getY());
    }
}
