package bb.roborally.client.player_list;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class PlayerView {

    private final HBox container = new HBox();

    public PlayerView() {

    }

    public Parent getView() {
        return container;
    }
}
