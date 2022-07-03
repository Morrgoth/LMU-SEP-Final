package bb.roborally.gui.data;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.game.Robot;
import bb.roborally.game.User;
import bb.roborally.networking.NetworkConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class RoboRallyModel {
    private final PlayerRegistry playerRegistry = new PlayerRegistry();
    private final RobotRegistry robotRegistry = new RobotRegistry();
    private final ObservableList<String> chatMessages = FXCollections.observableArrayList();
    public RoboRallyModel() {}
    public PlayerRegistry getPlayerRegistry() {
        return playerRegistry;
    }
    public RobotRegistry getRobotRegistry() {
        return robotRegistry;
    }
    public ObservableList<String> getObservableListChatMessages() {
        return chatMessages;
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
            User user = new User(playerAdded.getClientID(), playerAdded.getName(), playerAdded.getFigure());
            user.setRobot(robotRegistry.getRobotByFigureId(playerAdded.getFigure()));
            user.getPlayerAddedProperty().set(true);
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
                }
            }
        }
    }

    public void process(ReceivedChat receivedChat) {
        chatMessages.add(receivedChat.getFrom() + ": " + receivedChat.getMessage());
    }
}
