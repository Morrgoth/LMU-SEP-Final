package bb.roborally.gui.game;

import bb.roborally.data.messages.ChatMessage;
import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.LoginConfirmation;
import bb.roborally.data.messages.LogoutConfirmation;
import bb.roborally.data.util.User;
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


    public void process(Envelope envelope) {
        if (envelope.getMessageType() == Envelope.MessageType.CHAT_MESSAGE) {
            process((ChatMessage) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.LOGOUT_CONFIRMATION) {
            process((LogoutConfirmation) envelope.getMessageBody());
        } else if (envelope.getMessageType() == Envelope.MessageType.LOGIN_CONFIRMATION) {
            process((LoginConfirmation) envelope.getMessageBody());
        }
    }

    private void process(LoginConfirmation loginConfirmation) {
        chatMessages.add(loginConfirmation.getUser().getName() + " joined the room");
    }

    private void process(ChatMessage chatMessage) {
        chatMessages.add(chatMessage.getSender().getName() + ": " + chatMessage.getMessage());
    }

    private void process(LogoutConfirmation logoutConfirmation) {
        chatMessages.add(logoutConfirmation.getUser().getName() + " left the room");
    }
}
