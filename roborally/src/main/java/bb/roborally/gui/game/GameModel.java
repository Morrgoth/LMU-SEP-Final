package bb.roborally.gui.game;

import bb.roborally.data.messages.Envelope;
import bb.roborally.game.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameModel {

    private final ObservableList<String> chatMessages = FXCollections.observableArrayList();
    private StringProperty currentMessage = new SimpleStringProperty("");
    private User user = null;

    public ObservableList<String> getChatMessages() {
        return chatMessages;
    }

    public StringProperty currentMessageProperty() {
        return currentMessage;
    }

    public String getCurrentMessage() {
        return currentMessage.get();
    }

    public void setCurrentMessage(String message) {
        currentMessage.set(message);
    }

    public void resetCurrentMessage() {
        currentMessage.set("");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
