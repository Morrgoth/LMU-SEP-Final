package bb.roborally.client.programming_interface;

import bb.roborally.client.notification.Notification;
import bb.roborally.protocol.gameplay.SelectedCard;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.client.networking.NetworkConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

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
            view.getComboBox(finalI).getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayingCard>() {
                @Override
                public void changed(ObservableValue<? extends PlayingCard> observableValue, PlayingCard oldVal, PlayingCard newVal) {
                    if (newVal != null) {
                        if (!newVal.isMarked()) {
                            newVal.setMarked(true);
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
                    SelectedCard selectedCard1 = new SelectedCard(view.getComboBox(1).getValue().getName(), 1);
                    SelectedCard selectedCard2 = new SelectedCard(view.getComboBox(2).getValue().getName(), 2);
                    SelectedCard selectedCard3 = new SelectedCard(view.getComboBox(3).getValue().getName(), 3);
                    SelectedCard selectedCard4 = new SelectedCard(view.getComboBox(4).getValue().getName(), 4);
                    SelectedCard selectedCard5 = new SelectedCard(view.getComboBox(5).getValue().getName(), 5);
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
        FilteredList<PlayingCard> filteredList = new FilteredList<>(playerHand.getYourCards(),
                card -> !card.isMarked());
        for (int i = 1; i <= 5; i++) {
            view.getComboBox(i).setItems(filteredList);
        }
        playerHand.resetProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if (newVal) {
                    for (int i = 1; i <= 5; i++) {
                        if (view.getComboBox(i).getValue() != null) {
                            view.getComboBox(i).getValue().setMarked(false);
                            view.getComboBox(i).getSelectionModel().clearSelection();
                            view.getComboBox(i).setDisable(false);
                        }
                    }
                    playerHand.resetProperty().set(false);
                }
            }
        });
    }
}
