package bb.roborally.data.messages;

import bb.roborally.game.User;

public class ChatMessage implements Message{
    private User sender;
    private String message;
    //private boolean isPrivate = false;

    //public ChatMessage(User sender, String message, boolean isPrivate){
    //    this.sender = sender;
    //    this.message = message;
    //    this.isPrivate = isPrivate;
    //}

    public ChatMessage(User sender, String message){
        this.sender = sender;
        this.message = message;
    }

    public ChatMessage(){}

    /**
     * @return The User, that sent the message.
     */
    public User getSender(){
        return sender;
    }

    /**
     * @return The contents of the message.
     */
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method is only intended for deserialization.
     * @param user The User, that sent the message.
     */
    public void setSender(User user) {
        this.sender = user;
    }

    //public boolean isPrivate() {
    //    return isPrivate;
    //}
    
    //public void setIsPrivate(boolean isPrivate) {
    //    this.isPrivate = isPrivate;
    //}

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.CHAT_MESSAGE, this);
    }
}
