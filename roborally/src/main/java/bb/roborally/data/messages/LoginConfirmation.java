package bb.roborally.data.messages;

import bb.roborally.game.User;

public class LoginConfirmation implements Message {
    private User user;
    public LoginConfirmation(User user){
        this.user=user;
    }
    public LoginConfirmation() {}

    public User getUser() {
        return user;
    }

    public void setUser(User newUser) {
        this.user = newUser;
    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.LOGIN_CONFIRMATION, this);
    }
}
