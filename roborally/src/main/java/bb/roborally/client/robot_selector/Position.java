package bb.roborally.client.robot_selector;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Position {

    private final int DEFAULT = -1;
    private final IntegerProperty x = new SimpleIntegerProperty(DEFAULT);
    private final IntegerProperty y = new SimpleIntegerProperty(DEFAULT);
    private final BooleanBinding set = Bindings.and(x.greaterThan(DEFAULT), y.greaterThan(DEFAULT));

    public Position() {

    }

    public Position(int x, int y) {
        set(x, y);
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getX() {
        return x.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public int getY() {
        return y.get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void set(int x, int y) {
        setX(x);
        setY(y);
    }

    public Boolean isSet() {
        return set.get();
    }

    public BooleanBinding setProperty() {
        return set;
    }
}
