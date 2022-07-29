package bb.roborally.client.popup_reboot;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Zeynab Baiani
 * @author Muqiu Wang
 */
public class RebootView {
    private final VBox view = new VBox();
    private final Button submitButton = new Button("Submit");
    private final ComboBox<String> rebootCombo = new ComboBox<>();

    public RebootView(){
        Label label = new Label("Select your Robot Direction: ");
        rebootCombo.setItems(FXCollections.observableArrayList("top", "right", "bottom", "left"));
        view.getChildren().addAll(label,rebootCombo, submitButton);
        view.setSpacing(20);
        view.setStyle("-fx-background-color: #ffffff;");
    }

    public VBox getView() {
        return view;
    }

    public ComboBox<String> getRebootCombo() {
        return rebootCombo;
    }

    public String getSelectedDirection(){
        return rebootCombo.getValue();
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
