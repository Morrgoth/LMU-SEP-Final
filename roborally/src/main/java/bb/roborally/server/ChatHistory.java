package bb.roborally.server;

import bb.roborally.protocol.chat.ReceivedChat;

import java.util.ArrayList;

/**
 * @author Bence Ament
 */
public class ChatHistory {
    private ArrayList<ReceivedChat> publicMessages = new ArrayList<>();

    public void addMessage(ReceivedChat receivedChat) {
        if (!receivedChat.isPrivate()) {
            publicMessages.add(receivedChat);
        }
    }

    public ArrayList<ReceivedChat> getPublicMessages() {
        return publicMessages;
    }
}
