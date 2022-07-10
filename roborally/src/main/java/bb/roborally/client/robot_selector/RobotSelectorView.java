package bb.roborally.client.robot_selector;

import bb.roborally.server.game.Robot;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class RobotSelectorView {

    private final VBox view = new VBox();
    private final ComboBox<Robot> robotComboBox = new ComboBox<>();
    Callback<ListView<Robot>, ListCell<Robot>> robotComboBoxCellFactory = new Callback<ListView<Robot>, ListCell<Robot>>() {
        @Override
        public ListCell<Robot> call(ListView<Robot> l) {
            return new ListCell<Robot>() {
                @Override
                protected void updateItem(Robot item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        if (item.isAvailable()) {
                            setText(item.getFigureId() + ": " + item.getName());
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            } ;
        }
    };

    public RobotSelectorView() {
        robotComboBox.setCellFactory(robotComboBoxCellFactory);
        Label label = new Label("Pick a robot: ");
        view.getChildren().addAll(label, robotComboBox);
    }

    public VBox getView() {
        return view;
    }

    public ComboBox<Robot> getRobotComboBox() {
        return robotComboBox;
    }

    public Robot getSelectedRobot() {
        return robotComboBox.getValue();
    }

    public void disable(boolean disable) {
        robotComboBox.setDisable(disable);
    }
}
