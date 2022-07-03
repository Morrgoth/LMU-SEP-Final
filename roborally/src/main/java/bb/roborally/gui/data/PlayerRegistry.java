package bb.roborally.gui.data;

import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.game.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class PlayerRegistry {
    private int loggedInUserClientId = -1;
    private final BooleanProperty loggedInUserAdded = new SimpleBooleanProperty(false);
    private final BooleanProperty loggedInUserReady = new SimpleBooleanProperty(false);
    private final BooleanProperty loggedInUserMapSelector = new SimpleBooleanProperty(false);
    private final ObservableList<User> users = FXCollections.observableArrayList();
    public ObservableList<User> getObservableListUsers() {
        return users;
    }

    public void addUser(User user) {
        if (user.getClientID() == loggedInUserClientId) {
            loggedInUserAdded.set(true);
        }
        users.add(user);
    }

    public User getLoggedInUser() {
        Optional<User> optionalUser = users.stream().filter(user -> user.getClientID() == loggedInUserClientId).findFirst();
        return optionalUser.orElse(null);
    }

    public void setLoggedInUserClientId(int clientId) {
        loggedInUserClientId = clientId;
    }

    public int getLoggedInUserClientId() {
        return loggedInUserClientId;
    }

    public boolean existsLoggedInUser() {
        return loggedInUserClientId != -1;
    }

    public boolean isLoggedInUserAdded() {
        return loggedInUserAdded.get();
    }

    public BooleanProperty loggedInUserAddedProperty() {
        return loggedInUserAdded;
    }

    public boolean isLoggedInUserReady() {
        return loggedInUserReady.get();
    }

    public BooleanProperty loggedInUserReadyProperty() {
        return loggedInUserReady;
    }

    public boolean isLoggedInUserMapSelector() {
        return loggedInUserMapSelector.get();
    }

    public BooleanProperty loggedInUserMapSelectorProperty() {
        return loggedInUserMapSelector;
    }
}
