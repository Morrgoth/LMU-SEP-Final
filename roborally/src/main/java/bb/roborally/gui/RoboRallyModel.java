package bb.roborally.gui;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.util.User;
import bb.roborally.networking.NetworkConnection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class RoboRallyModel {
    private final ObjectProperty<User> loggedInUser = new SimpleObjectProperty<>();
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<String> userStrings = FXCollections.observableArrayList();
    public RoboRallyModel() {}

    public ObjectProperty<User> loggedInUserProperty() {
        return loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser.get();
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser.set(loggedInUser);
    }

    public ObservableList<String> userStringsProperty() {
        return userStrings;
    }
    public void process(Envelope envelope) {
        if (envelope.getMessageType() == Envelope.MessageType.PLAYER_ADDED) {
            process((PlayerAdded) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.ALIVE) {
            process((Alive) envelope.getMessageBody());
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
        if (playerAdded.getClientID() == loggedInUser.get().getClientID()) {
            loggedInUser.get().setName(playerAdded.getName());
            loggedInUser.get().setFigure(playerAdded.getFigure());
        } else {
            users.add(new User(playerAdded.getClientID(), playerAdded.getName(), playerAdded.getFigure()));
        }
        userStrings.add(playerAdded.getClientID() + ": " + playerAdded.getName() + " robot: " + playerAdded.getFigure());
    }
}
