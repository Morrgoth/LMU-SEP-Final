package bb.roborally.client.player_list;

import bb.roborally.client.card.CardViewModel;
import bb.roborally.client.robot_selector.Robot;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Bence Ament
 */
public class PlayerViewModel {

    private final Player player;
    private final Robot robot;
    private final StringProperty playerName = new SimpleStringProperty();
    private PlayerView view;
    public PlayerViewModel(Player player) {
        this.player = player;
        this.robot = player.getRobot();
    }

    public void connect(PlayerView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        CardViewModel cardViewModel = new CardViewModel(player.getCurrentCard());
        cardViewModel.connect(view.getCurrentCardView());
        if (player.isLocal()) {
            playerName.bind(Bindings.concat("@", player.nameProperty(), " (You)"));
        } else {
            playerName.bind(Bindings.concat("@", player.nameProperty()));
        }
        view.getPlayerNameLabel().textProperty().bind(playerName);
        view.getCheckpointLabel().textProperty().bindBidirectional(player.getPlayerInventory().checkpointCountProperty(), new NumberStringConverter());
        view.getRobotImageView().setImage(robot.getBoardRobotImage());
        view.getRobotNameLabel().setText(robot.getName());
    }

    private void setupListeners() {

    }


}
