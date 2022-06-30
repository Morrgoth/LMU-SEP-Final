package bb.roborally.gui;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.game.User;
import bb.roborally.networking.NetworkConnection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class RoboRallyModel {
    private User loggedInUser = new User();
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<String> userStrings = FXCollections.observableArrayList();

    private final ObservableList<String> chatMessages = FXCollections.observableArrayList();
    public RoboRallyModel() {}


    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public ObservableList<String> userStringsProperty() {
        return userStrings;
    }
    public ObservableList<String> chatMessagesProperty() {return chatMessages;}
    public void process(Envelope envelope) {
        if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
            process((PlayerAdded) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
            process((Alive) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.PLAYER_STATUS) {
            process((PlayerStatus) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.RECEIVED_CHAT) {
            process((ReceivedChat) envelope.getMessageBody());
        }
    }

    public void process(Alive alive) {
        try {
            NetworkConnection.getInstance().getDataOutputStream().writeUTF(alive.toJson());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(PlayerAdded playerAdded) {
        if (playerAdded.getClientID() == loggedInUser.getClientID()) {
            loggedInUser.setName(playerAdded.getName());
            loggedInUser.setFigure(playerAdded.getFigure());
            loggedInUser.getPlayerAddedProperty().set(true);
        } else {
            users.add(new User(playerAdded.getClientID(), playerAdded.getName(), playerAdded.getFigure()));
        }
        userStrings.add(playerAdded.getClientID() + ": " + playerAdded.getName() + " robot: " + playerAdded.getFigure());
    }

    public void process(PlayerStatus playerStatus) {
        if (playerStatus.getClientID() == loggedInUser.getClientID()) {
            loggedInUser.setReady(playerStatus.isReady());
        } else {
            // Update other Players
        }
    }

    public void process(ReceivedChat receivedChat) {
        chatMessages.add(receivedChat.getFrom() + ": " + receivedChat.getMessage());
    }
}
