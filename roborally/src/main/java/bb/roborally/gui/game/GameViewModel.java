package bb.roborally.gui.game;

import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.gameplay.SelectedCard;
import bb.roborally.data.messages.gameplay.SetStartingPoint;
import bb.roborally.game.User;
import bb.roborally.game.board.Cell;
import bb.roborally.game.cards.PlayingCard;
import bb.roborally.game.tiles.StartPoint;
import bb.roborally.gui.data.RoboRallyModel;
import bb.roborally.networking.NetworkConnection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;

public class GameViewModel {
    private final RoboRallyModel roboRallyModel;
    private final GameView view;


    public GameViewModel(RoboRallyModel roboRallyModel, GameView gameView) {
        this.roboRallyModel = roboRallyModel;
        view = gameView;
        setUpListeners();
        observeModelAndUpdate();
        view.getChatListView().setItems(roboRallyModel.getObservableListChatMessages());
    }

    private void setUpListeners() {

        view.getChatListView().getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                view.getChatListView().scrollTo(view.getChatListView().getItems().size() - 1);
            }
        });
        view.getSendButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String message = view.getMessageField().getText();
                sendMessage(message);
            }
        });

        view.getMessageField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String message = view.getMessageField().getText().trim();
                    sendMessage(message);
                }
            }
        });

        view.getClearTargetButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getUserComboBox().getSelectionModel().clearSelection();
            }
        });

        view.getPhases().textProperty().bind(roboRallyModel.phaseProperty());
    }

    private void sendMessage(String message) {
        if (!message.equals("")) {
            view.getMessageField().setText("");
            SendChat sendChat;
            if (view.getUserComboBox().getValue() == null) {
                sendChat = new SendChat(message, -1);
            } else {
                User target = (User) view.getUserComboBox().getValue();
                sendChat = new SendChat(message, target.getClientID());
            }
            try {
                NetworkConnection.getInstance().getDataOutputStream().writeUTF(sendChat.toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            roboRallyModel.setErrorMessage("Error: You cannot send empty messages!");
        }
    }

    private void observeModelAndUpdate() {
        view.getUserComboBox().setItems(roboRallyModel.getPlayerRegistry().getObservableListUsers());
        view.getGameBoardView().populateBoard(roboRallyModel.getGameBoard());
        view.getErrorMessage().textProperty().bind(roboRallyModel.errorMessageProperty());

        roboRallyModel.errorMessageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (!newVal.equals("")) {
                    view.showErrorPopup();
                    ( new Thread() { public void run() {
                        // do something
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                roboRallyModel.setErrorMessage("");
                            }
                        });

                    } } ).start();
                } else {
                    view.hideErrorPopup();
                }
            }
        });
        roboRallyModel.phaseProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.equals("Build-up Phase")) {
                    prepareBuildUpPhase();
                } else if (newVal.equals("Upgrade Phase")) {
                    // Without Upgrade Phase for now
                } else if (newVal.equals("Programming Phase")) {
                    pullDownBuildUpPhase();
                    prepareProgrammingPhase();
                } else if (newVal.equals("Activation Phase")) {

                }
            }
        });
    }

    private void prepareBuildUpPhase() {
        Image greenOverlay = new Image(getClass().getResource("/extra/green.png").toExternalForm());
        for (final Cell startPoint: roboRallyModel.getGameBoard().getStartPoints()) {
            ImageView imageView = new ImageView(greenOverlay);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            startPoint.push(imageView);
            startPoint.getStackPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    StartPoint startPointTile = (StartPoint)startPoint.getTile("StartPoint");
                    if (!roboRallyModel.getLoggedInUser().isStartingPointSet() && !startPointTile.isTaken()) {
                        startPointTile.setTaken(true);
                        SetStartingPoint setStartingPoint =
                                new SetStartingPoint(startPoint.getPosition().getY(),
                                        startPoint.getPosition().getX());
                        try {
                            NetworkConnection.getInstance().getDataOutputStream().writeUTF(setStartingPoint.toJson());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
        }
    }

    private void pullDownBuildUpPhase() {
        for (final Cell startPoint: roboRallyModel.getGameBoard().getStartPoints()) {
            StartPoint startPointTile = (StartPoint) startPoint.getTile("StartPoint");
            if (!startPointTile.isTaken()) {
                startPoint.pop();
            }
        }
    }

    private void prepareProgrammingPhase() {
        view.getProgrammingInterface().getChildren().clear();
        view.getRegister1ComboBox().getSelectionModel().clearSelection();
        view.getRegister2ComboBox().getSelectionModel().clearSelection();
        view.getRegister3ComboBox().getSelectionModel().clearSelection();
        view.getRegister4ComboBox().getSelectionModel().clearSelection();
        view.getRegister5ComboBox().getSelectionModel().clearSelection();
        view.getRegister1ComboBox().setItems(roboRallyModel.getPlayerHand().getYourCards());
        view.getRegister2ComboBox().setItems(roboRallyModel.getPlayerHand().getYourCards());
        view.getRegister3ComboBox().setItems(roboRallyModel.getPlayerHand().getYourCards());
        view.getRegister4ComboBox().setItems(roboRallyModel.getPlayerHand().getYourCards());
        view.getRegister5ComboBox().setItems(roboRallyModel.getPlayerHand().getYourCards());
        view.getProgrammingInterface().getChildren().addAll(view.getProgrammingInterfaceLeftCol(),
                view.getProgrammingInterfaceRightCol());
        view.getRegister1ComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayingCard>() {
            @Override
            public void changed(ObservableValue<? extends PlayingCard> observableValue, PlayingCard oldVal, PlayingCard newVal) {
                if (newVal != null) {
                    if (!newVal.isMarked()) {
                        newVal.setMarked(true);
                        view.getRegister1ComboBox().setDisable(true);
                    } else {
                        view.getRegister1ComboBox().getSelectionModel().clearSelection();
                    }
                }
            }
        });
        view.getRegister2ComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayingCard>() {
            @Override
            public void changed(ObservableValue<? extends PlayingCard> observableValue, PlayingCard oldVal, PlayingCard newVal) {
                if (newVal != null) {
                    if (!newVal.isMarked()) {
                        newVal.setMarked(true);
                        view.getRegister2ComboBox().setDisable(true);
                    } else {
                        view.getRegister2ComboBox().getSelectionModel().clearSelection();
                    }
                }
            }
        });
        view.getRegister3ComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayingCard>() {
            @Override
            public void changed(ObservableValue<? extends PlayingCard> observableValue, PlayingCard oldVal, PlayingCard newVal) {
                if (newVal != null) {
                    if (!newVal.isMarked()) {
                        newVal.setMarked(true);
                        view.getRegister3ComboBox().setDisable(true);
                    } else {
                        view.getRegister3ComboBox().getSelectionModel().clearSelection();
                    }
                }
            }
        });
        view.getRegister4ComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayingCard>() {
            @Override
            public void changed(ObservableValue<? extends PlayingCard> observableValue, PlayingCard oldVal, PlayingCard newVal) {
                if (newVal != null) {
                    if (!newVal.isMarked()) {
                        newVal.setMarked(true);
                        view.getRegister4ComboBox().setDisable(true);
                    } else {
                        view.getRegister4ComboBox().getSelectionModel().clearSelection();
                    }
                }
            }
        });
        view.getRegister5ComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayingCard>() {
            @Override
            public void changed(ObservableValue<? extends PlayingCard> observableValue, PlayingCard oldVal, PlayingCard newVal) {
                if (newVal != null) {
                    if (!newVal.isMarked()) {
                        newVal.setMarked(true);
                        view.getRegister5ComboBox().setDisable(true);
                    } else {
                        view.getRegister5ComboBox().getSelectionModel().clearSelection();
                    }
                }
            }
        });
        view.getClearRegister1Button().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRegister1ComboBox().getValue() != null) {
                    view.getRegister1ComboBox().getValue().setMarked(false);
                    view.getRegister1ComboBox().getSelectionModel().clearSelection();
                    view.getRegister1ComboBox().setDisable(false);
                }
            }
        });
        view.getClearRegister2Button().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRegister2ComboBox().getValue() != null) {
                    view.getRegister2ComboBox().getValue().setMarked(false);
                    view.getRegister2ComboBox().getSelectionModel().clearSelection();
                    view.getRegister2ComboBox().setDisable(false);
                }
            }
        });
        view.getClearRegister3Button().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRegister3ComboBox().getValue() != null) {
                    view.getRegister3ComboBox().getValue().setMarked(false);
                    view.getRegister3ComboBox().getSelectionModel().clearSelection();
                    view.getRegister3ComboBox().setDisable(false);
                }
            }
        });
        view.getClearRegister4Button().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRegister4ComboBox().getValue() != null) {
                    view.getRegister4ComboBox().getValue().setMarked(false);
                    view.getRegister4ComboBox().getSelectionModel().clearSelection();
                    view.getRegister4ComboBox().setDisable(false);
                }
            }
        });
        view.getClearRegister5Button().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRegister5ComboBox().getValue() != null) {
                    view.getRegister5ComboBox().getValue().setMarked(false);
                    view.getRegister5ComboBox().getSelectionModel().clearSelection();
                    view.getRegister5ComboBox().setDisable(false);
                }
            }
        });

        view.getResetProgramButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRegister1ComboBox().getValue() != null) {
                    view.getRegister1ComboBox().getValue().setMarked(false);
                    view.getRegister1ComboBox().getSelectionModel().clearSelection();
                    view.getRegister1ComboBox().setDisable(false);
                }
                if (view.getRegister2ComboBox().getValue() != null) {
                    view.getRegister2ComboBox().getValue().setMarked(false);
                    view.getRegister2ComboBox().getSelectionModel().clearSelection();
                    view.getRegister2ComboBox().setDisable(false);
                }
                if (view.getRegister3ComboBox().getValue() != null) {
                    view.getRegister3ComboBox().getValue().setMarked(false);
                    view.getRegister3ComboBox().getSelectionModel().clearSelection();
                    view.getRegister3ComboBox().setDisable(false);
                }
                if (view.getRegister4ComboBox().getValue() != null) {
                    view.getRegister4ComboBox().getValue().setMarked(false);
                    view.getRegister4ComboBox().getSelectionModel().clearSelection();
                    view.getRegister4ComboBox().setDisable(false);
                }
                if (view.getRegister5ComboBox().getValue() != null) {
                    view.getRegister5ComboBox().getValue().setMarked(false);
                    view.getRegister5ComboBox().getSelectionModel().clearSelection();
                    view.getRegister5ComboBox().setDisable(false);
                }
            }
        });

        view.getSubmitProgramButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (roboRallyModel.getPlayerHand().isProgramReady()) {
                    SelectedCard selectedCard1 = new SelectedCard(view.getRegister1ComboBox().getValue().getName(), 1);
                    SelectedCard selectedCard2 = new SelectedCard(view.getRegister2ComboBox().getValue().getName(), 2);
                    SelectedCard selectedCard3 = new SelectedCard(view.getRegister3ComboBox().getValue().getName(), 3);
                    SelectedCard selectedCard4 = new SelectedCard(view.getRegister4ComboBox().getValue().getName(), 4);
                    SelectedCard selectedCard5 = new SelectedCard(view.getRegister5ComboBox().getValue().getName(), 5);
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
}
