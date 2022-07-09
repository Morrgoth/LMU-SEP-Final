package bb.roborally.client.game.programming_interface;

import bb.roborally.protocol.gameplay.SelectedCard;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.client.data.RoboRallyModel;
import bb.roborally.client.networking.NetworkConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProgrammingInterfaceViewModel {

    private final ProgrammingInterfaceView view;
    private final RoboRallyModel roboRallyModel;

    public ProgrammingInterfaceViewModel(ProgrammingInterfaceView programmingInterfaceView, RoboRallyModel roboRallyModel) {
        this.view = programmingInterfaceView;
        this.roboRallyModel = roboRallyModel;
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
                        } else {
                            view.getComboBox(finalI).getSelectionModel().clearSelection();
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
                if (roboRallyModel.getPlayerHand().isProgramReady()) {
                    SelectedCard selectedCard1 = new SelectedCard(view.getComboBox(1).getValue().getName(), 1);
                    SelectedCard selectedCard2 = new SelectedCard(view.getComboBox(2).getValue().getName(), 2);
                    SelectedCard selectedCard3 = new SelectedCard(view.getComboBox(3).getValue().getName(), 3);
                    SelectedCard selectedCard4 = new SelectedCard(view.getComboBox(4).getValue().getName(), 4);
                    SelectedCard selectedCard5 = new SelectedCard(view.getComboBox(5).getValue().getName(), 5);
                    try {
                        NetworkConnection.getInstance().getDataOutputStream().writeUTF(selectedCard1.toJson());
                        NetworkConnection.getInstance().getDataOutputStream().writeUTF(selectedCard2.toJson());
                        NetworkConnection.getInstance().getDataOutputStream().writeUTF(selectedCard3.toJson());
                        NetworkConnection.getInstance().getDataOutputStream().writeUTF(selectedCard4.toJson());
                        NetworkConnection.getInstance().getDataOutputStream().writeUTF(selectedCard5.toJson());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    roboRallyModel.setErrorMessage("Your program is not yet complete!");
                }
            }
        });
    }

    private void observeModelAndUpdate() {
        FilteredList<PlayingCard> filteredList = new FilteredList<>(roboRallyModel.getPlayerHand().getYourCards(),
                card -> !card.isMarked());
        for (int i = 1; i <= 5; i++) {
            view.getComboBox(i).setItems(filteredList);
        }
    }
}
