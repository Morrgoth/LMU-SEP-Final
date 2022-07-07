package bb.roborally.gui.data;

import bb.roborally.data.messages.Error;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.gameplay.*;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.map.SelectMap;
import bb.roborally.game.Position;
import bb.roborally.game.User;
import bb.roborally.game.board.Board;
import bb.roborally.networking.NetworkConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class RoboRallyModel {
    private final StringProperty errorMessage = new SimpleStringProperty("");
    private final PlayerRegistry playerRegistry = new PlayerRegistry();
    private final RobotRegistry robotRegistry = new RobotRegistry();
    private final ObservableList<String> chatMessages = FXCollections.observableArrayList();
    private final ObservableList<String> availableMaps = FXCollections.observableArrayList();
    private final BooleanProperty gameStarted = new SimpleBooleanProperty(false);
    private Board gameBoard;
    private final StringProperty phase = new SimpleStringProperty("");
    private final PlayerHand playerHand = new PlayerHand();
    public RoboRallyModel() {}
    public StringProperty errorMessageProperty() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage.set(errorMessage);
    }
    public PlayerRegistry getPlayerRegistry() {
        return playerRegistry;
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
    public StringProperty phaseProperty() {
        return phase;
    }
    public void process(Alive alive) {
        try {
            NetworkConnection.getInstance().getDataOutputStream().writeUTF(alive.toJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(PlayerAdded playerAdded) {
        if (!playerRegistry.existsLoggedInUser() && playerAdded.getClientID() == playerRegistry.getLoggedInUser().getClientID()) {
            User loggedInUser = new User();
            loggedInUser.setClientID(playerAdded.getClientID());
            loggedInUser.setName(playerAdded.getName());
            loggedInUser.setRobot(robotRegistry.getRobotByFigureId(playerAdded.getFigure()));
            playerRegistry.addUser(loggedInUser);
        } else {
            User user = new User(playerAdded.getClientID(), playerAdded.getName());
            user.setRobot(robotRegistry.getRobotByFigureId(playerAdded.getFigure()));
            user.playerAddedProperty().set(true);
            playerRegistry.addUser(user);
            robotRegistry.makeUnavailable(playerAdded.getFigure());
        }
    }

    public void process(PlayerStatus playerStatus) {
        if (playerRegistry.getObservableListUsers().stream().anyMatch(user1 -> user1.getClientID() == playerStatus.getClientID())) {
            User user = playerRegistry.getObservableListUsers().stream().filter(user1 -> user1.getClientID() == playerStatus.getClientID()).findAny().get();
            if (user.isReady() != playerStatus.isReady()) {
                if (playerStatus.isReady()) {
                    playerRegistry.getObservableListUsers().remove(user);
                    user.setReady(true);
                    playerRegistry.getObservableListUsers().add(0, user);
                } else {
                    playerRegistry.getObservableListUsers().remove(user);
                    user.setReady(false);
                    playerRegistry.getObservableListUsers().add(user);
                }
                if (playerStatus.getClientID() == playerRegistry.getLoggedInUserClientId()) {
                    playerRegistry.loggedInUserReadyProperty().set(playerStatus.isReady());
                    if (!playerStatus.isReady()) {
                        playerRegistry.loggedInUserMapSelectorProperty().set(false);
                    }
                }
            }
        }
    }

    public void process(SelectMap selectMap) {
        for (String map: selectMap.getAvailableMaps()) {
            if (!availableMaps.contains(map)) {
                availableMaps.add(map);
            }
        }
        playerRegistry.loggedInUserMapSelectorProperty().set(true);
    }

    public void process(Board board) {
        setGameBoard(board);
        gameStarted.set(true);
    }

    public void process(ReceivedChat receivedChat) {
        if (receivedChat.isPrivate()) {
            chatMessages.add(playerRegistry.getUserByClientId(receivedChat.getFrom()).getName() + "[Private]: " + receivedChat.getMessage());
        } else {
            chatMessages.add(playerRegistry.getUserByClientId(receivedChat.getFrom()).getName() + ": " + receivedChat.getMessage());
        }
    }

    public void process(ActivePhase activePhase) {
        if (activePhase.getPhase() == 0) {
            phase.set("Build-up Phase");
        } else if (activePhase.getPhase() == 1) {
            phase.set("Upgrade Phase");
        } else if (activePhase.getPhase() == 2) {
            phase.set("Programming Phase");
        } else if (activePhase.getPhase() == 3) {
            phase.set("Activation Phase");
        }
    }

    public void process(StartingPointTaken startingPointTaken) {
        gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).pop();
        if (startingPointTaken.getClientID() == playerRegistry.getLoggedInUserClientId()) {
            playerRegistry.getLoggedInUser().getRobot().setPosition(new Position(startingPointTaken.getX(),
                    startingPointTaken.getY()));
            gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).push(playerRegistry.getLoggedInUser()
                    .getRobot().getRobotElement());
            playerRegistry.getLoggedInUser().setStartingPointSet(true);
        } else {
            playerRegistry.getUserByClientId(startingPointTaken.getClientID()).getRobot().setPosition(new Position(
                    startingPointTaken.getX(), startingPointTaken.getY()));
            gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).push(
                    playerRegistry.getUserByClientId(startingPointTaken.getClientID()).getRobot().getRobotElement());
            playerRegistry.getUserByClientId(startingPointTaken.getClientID()).setStartingPointSet(true);
        }
    }

    public void process(YourCards yourCards) {
        playerHand.update(yourCards);
    }

    public void process(NotYourCards notYourCards) {
        // Ignore for now
    }

    public void process(ShuffleCoding shuffleCoding) {
        // Ignore for now
    }

    public void process(Error error) {
        errorMessage.set(error.getError());
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public User getLoggedInUser() {
        return playerRegistry.getLoggedInUser();
    }
    public PlayerHand getPlayerHand() {
        return playerHand;
    }
}
