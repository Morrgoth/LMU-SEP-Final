package bb.roborally.client.popupReboot;

import bb.roborally.client.robot_selector.Robot;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RebootView {
    private final VBox view = new VBox();
    private final Button submitButton = new Button("Submit");
    private final ComboBox<String> rebootCombo = new ComboBox<>();

    public RebootView(){
        Label label = new Label("Select your Robot Direction: ");
        view.getChildren().addAll(label,rebootCombo);
        view.setSpacing(20);
    }

    public VBox getView() {
        return view;
    }

    public ComboBox<String> getRebootCombo() {
        return rebootCombo;
    }
}
