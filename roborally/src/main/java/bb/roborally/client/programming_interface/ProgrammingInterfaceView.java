package bb.roborally.client.programming_interface;

import bb.roborally.server.game.cards.PlayingCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ProgrammingInterfaceView {
    private final HBox container = new HBox();
    private final ComboBox<PlayingCard> register1ComboBox = new ComboBox<>();
    private final Button clearRegister1Button = new Button("Clear");
    private final ComboBox<PlayingCard> register2ComboBox = new ComboBox<>();
    private final Button clearRegister2Button = new Button("Clear");
    private final ComboBox<PlayingCard> register3ComboBox = new ComboBox<>();
    private final Button clearRegister3Button = new Button("Clear");
    private final ComboBox<PlayingCard> register4ComboBox = new ComboBox<>();
    private final Button clearRegister4Button = new Button("Clear");
    private final ComboBox<PlayingCard> register5ComboBox = new ComboBox<>();
    private final Button clearRegister5Button = new Button("Clear");
    private final Button resetProgramButton = new Button("Clear All");
    private final Button submitProgramButton = new Button("Submit");
    Callback<ListView<PlayingCard>, ListCell<PlayingCard>> registerComboBoxCellFactory = new Callback<ListView<PlayingCard>, ListCell<PlayingCard>>() {
        @Override
        public ListCell<PlayingCard> call(ListView<PlayingCard> stringListView) {
            return new ListCell<PlayingCard>() {
                @Override
                protected void updateItem(PlayingCard item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        if (!item.isMarked()) {
                            setText(item.getName());
                        }
                    }
                }
            };
        }
    };

    public ProgrammingInterfaceView() {
        VBox leftCol = new VBox();
        leftCol.setSpacing(10);
        VBox rightCol = new VBox();
        rightCol.setSpacing(10);
        HBox register1 = new HBox();
        register1.setSpacing(5);
        Label registerLabel1 = new Label("Register 1: ");
        register1ComboBox.setPrefWidth(150);
        register1ComboBox.setCellFactory(registerComboBoxCellFactory);
        register1.getChildren().addAll(registerLabel1, register1ComboBox, clearRegister1Button);
        HBox register2 = new HBox();
        register2.setSpacing(5);
        Label registerLabel2 = new Label("Register 2: ");
        register2ComboBox.setPrefWidth(150);
        register2ComboBox.setCellFactory(registerComboBoxCellFactory);
        register2.getChildren().addAll(registerLabel2, register2ComboBox, clearRegister2Button);
        HBox register3 = new HBox();
        register3.setSpacing(5);
        Label registerLabel3 = new Label("Register 3: ");
        register3ComboBox.setPrefWidth(150);
        register3ComboBox.setCellFactory(registerComboBoxCellFactory);
        register3.getChildren().addAll(registerLabel3, register3ComboBox, clearRegister3Button);
        leftCol.getChildren().addAll(register1, register2, register3);
        HBox register4 = new HBox();
        register4.setSpacing(5);
        Label registerLabel4 = new Label("Register 4: ");
        register4ComboBox.setPrefWidth(150);
        register4ComboBox.setCellFactory(registerComboBoxCellFactory);
        register4.getChildren().addAll(registerLabel4, register4ComboBox, clearRegister4Button);
        HBox register5 = new HBox();
        register5.setSpacing(5);
        Label registerLabel5 = new Label("Register 5: ");
        register5ComboBox.setPrefWidth(150);
        register5ComboBox.setCellFactory(registerComboBoxCellFactory);
        register5.getChildren().addAll(registerLabel5, register5ComboBox, clearRegister5Button);
        HBox controlPanel = new HBox();
        controlPanel.getChildren().addAll(resetProgramButton, submitProgramButton);
        controlPanel.setAlignment(Pos.CENTER);
        controlPanel.setSpacing(15);
        rightCol.getChildren().addAll(register4, register5, controlPanel);
        container.getChildren().addAll(leftCol, rightCol);
        container.setSpacing(20);
        container.setPadding(new Insets(10, 10, 10, 10));
    }

    public HBox getView() {
        return container;
    }

    public ComboBox<PlayingCard> getComboBox(int register) {
        if (register == 1) {
            return register1ComboBox;
        } else if (register == 2) {
            return  register2ComboBox;
        } else if (register == 3) {
            return register3ComboBox;
        } else if (register == 4) {
            return register4ComboBox;
        } else if (register == 5) {
            return register5ComboBox;
        } else {
            return null;
        }
    }

    public Button getClearRegisterButton(int register) {
        if (register == 1) {
            return clearRegister1Button;
        } else if (register == 2) {
            return  clearRegister2Button;
        } else if (register == 3) {
            return clearRegister3Button;
        } else if (register == 4) {
            return clearRegister4Button;
        } else if (register == 5) {
            return clearRegister5Button;
        } else {
            return null;
        }
    }

    public Button getResetProgramButton() {
        return resetProgramButton;
    }

    public Button getSubmitProgramButton() {
        return submitProgramButton;
    }

    public void reset() {
        register1ComboBox.setDisable(false);
        register2ComboBox.setDisable(false);
        register3ComboBox.setDisable(false);
        register4ComboBox.setDisable(false);
        register5ComboBox.setDisable(false);
    }
}
