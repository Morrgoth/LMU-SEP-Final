package bb.roborally.protocol;

/**
 * creating a certain position as reference for each item on the board. Each item on the board has a certain position. This class
 * makes it possible to compare them and to make updates during the game.
 *
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Philipp Keyzman
 */
public class Position {

    public static final int NAN = -99999999;
    // ORIENTATION : LEFT,RIGHT
    private int x = NAN;
    //ORIENTATION : FRONT,BACK
    private int y = NAN;

    public Position() {

    }
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        set(position);
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

    public void set(Position position) {
        setX(position.getX());
        setY(position.getY());
    }

    public boolean isValid() {
        return (x != NAN) && (y != NAN);
    }

    public void invalidate() {
        setX(NAN);
        setY(NAN);
    }

    public static double calculateDistance(Position pos1, Position pos2) {
        double x = pos2.getX() - pos1.getX();
        double y = pos2.getY() - pos1.getY();
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position other)) {
            return false;
        }

        return (this.x == other.getX()) && (this.y == other.getY());
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
