package bb.roborally.client.programming_interface;

import bb.roborally.client.card.Card;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.notification.Notification;
import bb.roborally.protocol.gameplay.SelectedCard;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ProgrammingInterfaceViewModel {
    private ProgrammingInterfaceView view;
    private final PlayerHand playerHand;

    public ProgrammingInterfaceViewModel(PlayerHand playerHand) {
        this.playerHand = playerHand;
    }

    public void connect(ProgrammingInterfaceView programmingInterfaceView) {
        this.view = programmingInterfaceView;
        setupListeners();
        observeModelAndUpdate();
    }

    private void setupListeners() {
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            view.getComboBox(finalI).getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {
                @Override
                public void changed(ObservableValue<? extends Card> observableValue, Card oldVal, Card newVal) {
                    if (newVal != null) {
                        if (!newVal.isMarked()) {
                            newVal.setMarked(true);
                            playerHand.getProgram().setRegister(finalI, newVal);
                            view.getComboBox(finalI).setDisable(true);
                        }
                    }
                }
            });
            view.getClearRegisterButton(finalI).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (view.getComboBox(finalI).getValue() != null) {
                        view.getComboBox(finalI).getValue().setMarked(false);
                        playerHand.getProgram().resetRegister(finalI);
                        view.getComboBox(finalI).getSelectionModel().clearSelection();
                        view.getComboBox(finalI).setDisable(false);
                    }
                }
            });
        }

        view.getResetProgramButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int i = 1; i <= 5; i++) {
                    if (view.getComboBox(i).getValue() != null) {
                        view.getComboBox(i).getValue().setMarked(false);
                        playerHand.getProgram().resetRegister(i);
                        view.getComboBox(i).getSelectionModel().clearSelection();
                        view.getComboBox(i).setDisable(false);
                    }
                }
            }
        });

        view.getSubmitProgramButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (playerHand.isProgramReady()) {
                    SelectedCard selectedCard1 = new SelectedCard(view.getComboBox(1).getValue().getType(), 1);
                    SelectedCard selectedCard2 = new SelectedCard(view.getComboBox(2).getValue().getType(), 2);
                    SelectedCard selectedCard3 = new SelectedCard(view.getComboBox(3).getValue().getType(), 3);
                    SelectedCard selectedCard4 = new SelectedCard(view.getComboBox(4).getValue().getType(), 4);
                    SelectedCard selectedCard5 = new SelectedCard(view.getComboBox(5).getValue().getType(), 5);
                    NetworkConnection.getInstance().send(selectedCard1);
                    NetworkConnection.getInstance().send(selectedCard2);
                    NetworkConnection.getInstance().send(selectedCard3);
                    NetworkConnection.getInstance().send(selectedCard4);
                    NetworkConnection.getInstance().send(selectedCard5);
                } else {
                    Notification.getInstance().show_medium(Notification.Kind.WARNING, "Your program is not yet ready");
                }
            }
        });
    }

    private void observeModelAndUpdate() {
        for (int i = 1; i <= 5; i++) {
            view.getComboBox(i).setItems(playerHand.getSelectableCards());
            int finalI = i;
            playerHand.getProgram().getCard(i).filledProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1 && view.getComboBox(finalI).getValue() == null) {
                        view.getComboBox(finalI).getSelectionModel().select(playerHand.getProgram().getCard(finalI));
                        view.getComboBox(finalI).setDisable(true);
                    }
                    if (!t1) {
                        view.getComboBox(finalI).getSelectionModel().clearSelection();
                    }
                }
            });
        }
        playerHand.resetProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    view.reset();
                    playerHand.getYourCards().clear();
                    playerHand.getProgram().reset();
                    playerHand.resetProperty().set(false);
                }
            }
        });

    }
}