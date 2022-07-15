package bb.roborally.client.robot_selector;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class RobotSelectorView {

    private final VBox view = new VBox();
    private final RobotView robotView1 = new RobotView();
    private final RobotView robotView2 = new RobotView();
    private final RobotView robotView3 = new RobotView();
    private final RobotView robotView4 = new RobotView();
    private final RobotView robotView5 = new RobotView();
    private final RobotView robotView6 = new RobotView();

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
                            setText(item.getId() + ": " + item.getName());
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

    public RobotView getRobotView1() {
        return robotView1;
    }

    public RobotView getRobotView2() {
        return robotView2;
    }

    public RobotView getRobotView3() {
        return robotView3;
    }

    public RobotView getRobotView4() {
        return robotView4;
    }

    public RobotView getRobotView5() {
        return robotView5;
    }

    public RobotView getRobotView6() {
        return robotView6;
    }
}
