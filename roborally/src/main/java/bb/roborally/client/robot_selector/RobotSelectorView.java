package bb.roborally.client.robot_selector;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class RobotSelectorView {
    private Robot selectedRobot = null;

    private final HBox view = new HBox();
    private final RobotView robotView1 = new RobotView(1);
    private final RobotView robotView2 = new RobotView(2);
    private final RobotView robotView3 = new RobotView(3);
    private final RobotView robotView4 = new RobotView(4);
    private final RobotView robotView5 = new RobotView(5);
    private final RobotView robotView6 = new RobotView(6);

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
        ListCell<RobotView> avalaibelRobots = new ListCell<RobotView>();
        avalaibelRobots.setItem(robotView1);
        avalaibelRobots.setItem(robotView2);
        avalaibelRobots.setItem(robotView3);
        avalaibelRobots.setItem(robotView4);
        avalaibelRobots.setItem(robotView5);
        avalaibelRobots.setItem(robotView6);
        for(int i:avalaibelRobots){
            if(selectedRobot.isAvailable()==false){
                avalaibelRobots.removeItem(i);
            }
        }
        robotComboBox.setCellFactory(robotComboBoxCellFactory);
        Label label = new Label("Pick a robot: ");
        for(int i:avalaibelRobots){
            view.getChildren().addAll(label, robotComboBox, i.getView());
        }
    }

    public HBox getView() {
        return view;
    }

    public ComboBox<Robot> getRobotComboBox() {
        return robotComboBox;
    }

    public Robot getSelectedRobot() {
        return selectedRobot;
    }

    public void setSelectedRobot(Robot selectedRobot) {
        this.selectedRobot = selectedRobot;
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
