package bb.roborally.client.robot_selector;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
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
        robotComboBox.setCellFactory(robotComboBoxCellFactory);
        view.getChildren().addAll(robotView1.getView(), robotView2.getView(), robotView3.getView(),
                robotView4.getView(), robotView5.getView(), robotView6.getView());
        view.setSpacing(25);
        view.setAlignment(Pos.CENTER);
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

    public RobotView getRobot(int id) {
        if (id == 1) {
            return getRobotView1();
        } else if (id == 2) {
            return getRobotView2();
        } else if (id == 3) {
            return getRobotView3();
        } else if (id == 4) {
            return getRobotView4();
        } else if (id == 5) {
            return getRobotView5();
        } else if (id == 6) {
            return getRobotView6();
        }
        return null;
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
