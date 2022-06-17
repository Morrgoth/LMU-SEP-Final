package bb.roborally.data.messages;

import bb.roborally.game.User;

public class LoginError implements Message {
    private User user;
    private String message;

    public LoginError(User user, String message){
        this.user = user;
        this.message = message;
    }
    public LoginError(User user){
        this.user=user;
    }
    public LoginError() {}

    public User getUser() {
        return user;
    }

    public void setUser(User newUser) {
        this.user = newUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.LOGIN_ERROR, this);
    }
}
