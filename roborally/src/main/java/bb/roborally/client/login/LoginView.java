package bb.roborally.client.login;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Bence Ament
 * @author Zeynab Baiani
 * @author Muqiu Wang
 */
public class LoginView {

    private final GridPane view = new GridPane();
    private final TextField ipField = new TextField();
    private final TextField portField = new TextField();
    private final Button submitButton = new Button("Submit");

    public LoginView() {
        view.setAlignment(Pos.CENTER);
        view.setVgap(10);
        Label label = new Label("Connect");
        view.addRow(0, label);
        view.addRow(1, ipField);
        view.addRow(2, portField);
        view.addRow(3, submitButton);
    }

    public GridPane getView() {
        return view;
    }

    public TextField getIpField() {
        return ipField;
    }

    public TextField getPortField() {
        return portField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
