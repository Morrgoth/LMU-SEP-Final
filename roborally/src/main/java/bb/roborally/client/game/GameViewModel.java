package bb.roborally.client.game;

import bb.roborally.client.chat.ChatViewModel;
import bb.roborally.protocol.gameplay.SetStartingPoint;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.StartPoint;
import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.programming_interface.ProgrammingInterfaceViewModel;
import bb.roborally.client.networking.NetworkConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    }

    private void setUpListeners() {
        view.getPhases().textProperty().bind(roboRallyModel.phaseProperty());
    }

    private void observeModelAndUpdate() {
        view.getGameBoardView().populateBoard(roboRallyModel.getGameBoard());
        ChatViewModel chatViewModel = new ChatViewModel(roboRallyModel, view.getChatView());

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
