package bb.roborally.client.game;

import bb.roborally.protocol.chat.SendChat;
import bb.roborally.protocol.gameplay.SetStartingPoint;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.StartPoint;
import bb.roborally.client.data.RoboRallyModel;
import bb.roborally.client.game.programming_interface.ProgrammingInterfaceView;
import bb.roborally.client.game.programming_interface.ProgrammingInterfaceViewModel;
import bb.roborally.client.networking.NetworkConnection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
        ProgrammingInterfaceViewModel model = new ProgrammingInterfaceViewModel(view.getProgrammingInterfaceView(), roboRallyModel);
        view.getControlBox().getChildren().add(view.getProgrammingInterfaceView().getView());
    }
}
