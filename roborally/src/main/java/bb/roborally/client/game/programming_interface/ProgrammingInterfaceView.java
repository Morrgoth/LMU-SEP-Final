package bb.roborally.client.game.programming_interface;

import bb.roborally.server.game.cards.PlayingCard;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ProgrammingInterfaceView {
    private HBox container;
    private ComboBox<PlayingCard> register1ComboBox;
    private Button clearRegister1Button;
    private ComboBox<PlayingCard> register2ComboBox;
    private Button clearRegister2Button;
    private ComboBox<PlayingCard> register3ComboBox;
    private Button clearRegister3Button;
    private ComboBox<PlayingCard> register4ComboBox;
    private Button clearRegister4Button;
    private ComboBox<PlayingCard> register5ComboBox;
    private Button clearRegister5Button;
    private Button resetProgramButton;
    private Button submitProgramButton;
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
        buildUI();
    }
    private void buildUI() {
        container = new HBox();
        VBox leftCol = new VBox();
        VBox rightCol = new VBox();
        HBox register1 = new HBox();
        Label registerLabel1 = new Label("Register 1");
        register1ComboBox = new ComboBox<>();
        register1ComboBox.setPrefWidth(150);
        register1ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister1Button = new Button("Clear");
        register1.getChildren().addAll(registerLabel1, register1ComboBox, clearRegister1Button);
        HBox register2 = new HBox();
        Label registerLabel2 = new Label("Register 2");
        register2ComboBox = new ComboBox<>();
        register2ComboBox.setPrefWidth(150);
        register2ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister2Button = new Button("Clear");
        register2.getChildren().addAll(registerLabel2, register2ComboBox, clearRegister2Button);
        HBox register3 = new HBox();
        Label registerLabel3 = new Label("Register 3");
        register3ComboBox = new ComboBox<>();
        register3ComboBox.setPrefWidth(150);
        register3ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister3Button = new Button("Clear");
        register3.getChildren().addAll(registerLabel3, register3ComboBox, clearRegister3Button);
        leftCol.getChildren().addAll(register1, register2, register3);
        HBox register4 = new HBox();
        Label registerLabel4 = new Label("Register 4");
        register4ComboBox = new ComboBox<>();
        register4ComboBox.setPrefWidth(150);
        register4ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister4Button = new Button("Clear");
        register4.getChildren().addAll(registerLabel4, register4ComboBox, clearRegister4Button);
        HBox register5 = new HBox();
        Label registerLabel5 = new Label("Register 5");
        register5ComboBox = new ComboBox<>();
        register5ComboBox.setPrefWidth(150);
        register5ComboBox.setCellFactory(registerComboBoxCellFactory);
        clearRegister5Button = new Button("Clear");
        register5.getChildren().addAll(registerLabel5, register5ComboBox, clearRegister5Button);
        HBox controlPanel = new HBox();
        resetProgramButton = new Button("Clear All");
        submitProgramButton = new Button("Submit");
        controlPanel.getChildren().addAll(resetProgramButton, submitProgramButton);
        rightCol.getChildren().addAll(register4, register5, controlPanel);
        container.getChildren().addAll(leftCol, rightCol);
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
}
