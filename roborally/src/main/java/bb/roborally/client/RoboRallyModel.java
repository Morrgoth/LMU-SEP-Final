package bb.roborally.client;

import bb.roborally.client.phase_info.PhaseModel;
import bb.roborally.client.player_list.Player;
import bb.roborally.client.programming_interface.PlayerHand;
import bb.roborally.client.player_list.PlayerQueue;
import bb.roborally.client.robot_selector.RobotRegistry;
import bb.roborally.client.notification.Notification;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.chat.ReceivedChat;
import bb.roborally.protocol.connection.Alive;
import bb.roborally.protocol.connection.HelloClient;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.gameplay.*;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.map.SelectMap;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.tiles.StartPoint;
import bb.roborally.client.networking.NetworkConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class RoboRallyModel {
    private final StringProperty errorMessage = new SimpleStringProperty("");
    private final PlayerQueue playerQueue = new PlayerQueue();
    private final RobotRegistry robotRegistry = new RobotRegistry();
    private final ObservableList<String> chatMessages = FXCollections.observableArrayList();
    private final ObservableList<String> availableMaps = FXCollections.observableArrayList();
    private final BooleanProperty gameStarted = new SimpleBooleanProperty(false);
    private Board gameBoard;
    private final PhaseModel phase = new PhaseModel();
    private final PlayerHand playerHand = new PlayerHand();
    public RoboRallyModel() {}
    public StringProperty errorMessageProperty() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage.set(errorMessage);
    }
    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }
    public RobotRegistry getRobotRegistry() {
        return robotRegistry;
    }
    public ObservableList<String> getObservableListChatMessages() {
        return chatMessages;
    }
    public ObservableList<String> getObservableListAvailableMaps() {
        return availableMaps;
    }
    public BooleanProperty gameStartedProperty() {
        return gameStarted;
    }
    public PhaseModel getPhase() {
        return phase;
    }

    public void process(HelloClient helloClient) {
        HelloServer helloServer = new HelloServer(false);
        NetworkConnection.getInstance().send(helloServer);
    }

    public void process(Welcome welcome) {
        ViewManager.openStartMenuView();
        playerQueue.setLocalPlayerId(welcome.getClientID());
    }

    public void process(PlayerAdded playerAdded) {
        playerQueue.addPlayer(playerAdded.getClientID(), playerAdded.getName(),
                robotRegistry.getRobotByFigureId(playerAdded.getFigure()));
        robotRegistry.makeUnavailable(playerAdded.getFigure());
    }

    public void process(PlayerStatus playerStatus) {
        playerQueue.setPlayerReady(playerStatus.getClientID(), playerStatus.isReady());
    }

    public void process(SelectMap selectMap) {
        for (String map: selectMap.getAvailableMaps()) {
            if (!availableMaps.contains(map)) {
                availableMaps.add(map);
            }
        }
        playerQueue.getLocalPlayer().mapSelectorProperty().set(true);
    }

    public void process(Board board) {
        setGameBoard(board);
        gameStarted.set(true);
    }

    public void process(ReceivedChat receivedChat) {
        if (receivedChat.isPrivate()) {
            chatMessages.add(playerQueue.getPlayerById(receivedChat.getFrom()).getName() + "[Private]: " + receivedChat.getMessage());
        } else {
            chatMessages.add(playerQueue.getPlayerById(receivedChat.getFrom()).getName() + ": " + receivedChat.getMessage());
        }
    }

    public void process(ActivePhase activePhase) {
        if (activePhase.getPhase() == 0) {
            phase.setPhase(0);
        } else if (activePhase.getPhase() == 1) {
            phase.setPhase(1);
        } else if (activePhase.getPhase() == 2) {
            phase.setPhase(2);
        } else if (activePhase.getPhase() == 3) {
            phase.setPhase(3);
        }
    }

    public void process(CurrentPlayer currentPlayer) {
        if (currentPlayer.getClientID() == playerQueue.getLocalPlayerId()) {
            Notification.getInstance().show_medium(Notification.Kind.INFO, "Choose one of the available Start Points.");
            phase.buildUpActiveProperty().set(true);
        }
    }

    public void process(StartingPointTaken startingPointTaken) {
        gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).pop();
        if (startingPointTaken.getClientID() == playerQueue.getLocalPlayerId()) {
            playerQueue.getLocalPlayer().getRobot().setStartPosition(startingPointTaken.getX(), startingPointTaken.getY());
            gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).push(playerQueue.getLocalPlayer()
                    .getRobot().getRobotElement());
            ((StartPoint)gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).getTile("StartPoint"))
                    .setTaken(true);
            phase.buildUpActiveProperty().set(false);
        } else {
            playerQueue.getPlayerById(startingPointTaken.getClientID()).getRobot().setStartPosition(startingPointTaken.getX(),
                    startingPointTaken.getY());
            gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).push(
                    playerQueue.getPlayerById(startingPointTaken.getClientID()).getRobot().getRobotElement());
            ((StartPoint) gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).getTile("StartPoint"))
                    .setTaken(true);
        }
    }

    public void process(YourCards yourCards) {
        playerHand.update(yourCards);
    }

    public void process(NotYourCards notYourCards) {
        //
    }

    public void process(ShuffleCoding shuffleCoding) {
        // Ignore for now
    }

    public void process(CardsYouGotNow cardsYouGotNow) {
        //
    }

    public void process(CurrentCards currentCards) {
        for (Integer id: currentCards.getActiveCards().keySet()) {
            playerQueue.getPlayerById(id).getCurrentCard().setType(currentCards.getActiveCards().get(id));
        }
    }



    public void process(Error error) {
        Notification.getInstance().show_medium(Notification.Kind.ERROR, error.getError());
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getLocalPlayer() {
        return playerQueue.getLocalPlayer();
    }
    public PlayerHand getPlayerHand() {
        return playerHand;
    }
}
