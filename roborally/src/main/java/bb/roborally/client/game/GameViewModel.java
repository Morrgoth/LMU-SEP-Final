package bb.roborally.client.game;

import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.board.BoardViewModel;
import bb.roborally.client.board.CellView;
import bb.roborally.client.chat.ChatViewModel;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.phase_info.PhaseInfoViewModel;
import bb.roborally.client.player_inventory.PlayerInventoryViewModel;
import bb.roborally.client.player_list.PlayerListViewModel;
import bb.roborally.client.programming_interface.ProgrammingInterfaceViewModel;
import bb.roborally.client.timer.TimerViewModel;
import bb.roborally.protocol.gameplay.SetStartingPoint;
import bb.roborally.protocol.map.tiles.StartPoint;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameViewModel {
    private final RoboRallyModel roboRallyModel;
    private GameView view;

    public GameViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(GameView gameView) {
        view = gameView;
        observeModelAndUpdate();
        setUpListeners();
    }

    private void setUpListeners() {

    }

    private void observeModelAndUpdate() {
        BoardViewModel boardViewModel = new BoardViewModel(roboRallyModel);
        boardViewModel.connect(view.getGameBoardView());
        PlayerInventoryViewModel playerInventoryModel = new PlayerInventoryViewModel(roboRallyModel.getPlayerQueue().getLocalPlayer());
        playerInventoryModel.connect(view.getPlayerInventoryView());
        PhaseInfoViewModel phaseInfoViewModel = new PhaseInfoViewModel(roboRallyModel.getPhase());
        phaseInfoViewModel.connect(view.getPhase());
        ChatViewModel chatViewModel = new ChatViewModel(roboRallyModel);
        chatViewModel.connect(view.getChat());
        ProgrammingInterfaceViewModel programmingInterfaceViewModel = new ProgrammingInterfaceViewModel(roboRallyModel.getPlayerHand());
        programmingInterfaceViewModel.connect(view.getProgrammingInterface());
        PlayerListViewModel playerListViewModel = new PlayerListViewModel(roboRallyModel.getPlayerQueue());
        playerListViewModel.connect(view.getPlayers());
        TimerViewModel timerViewModel = new TimerViewModel(roboRallyModel.timerRunningProperty());
        timerViewModel.connect(view.getTimer());
        roboRallyModel.getPhase().phaseNameProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.equals("Build-Up Phase")) {
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
        for (final CellView startPoint: view.getGameBoardView().getStartPoints()) {
            ImageView imageView = new ImageView(greenOverlay);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            startPoint.push(imageView);
            startPoint.getView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    StartPoint startPointTile = (StartPoint)startPoint.getCell().getTile("StartPoint");
                    if (!roboRallyModel.getLocalPlayer().getRobot().getStartPosition().isSet() && !startPointTile.isTaken()) {
                        startPointTile.setTaken(true);
                        SetStartingPoint setStartingPoint =
                                new SetStartingPoint(startPoint.getPosition().getY(),
                                        startPoint.getPosition().getX());
                        NetworkConnection.getInstance().send(setStartingPoint);

                    }
                }
            });
        }
    }

    private void pullDownBuildUpPhase() {
        for (final CellView startPoint: view.getGameBoardView().getStartPoints()) {
            startPoint.pop();
        }
        BoardViewModel boardViewModel = new BoardViewModel(roboRallyModel);
        boardViewModel.connect(view.getGameBoardView());
    }

    private void prepareProgrammingPhase() {
        view.setControlToProgrammingInterface();
    }
}
