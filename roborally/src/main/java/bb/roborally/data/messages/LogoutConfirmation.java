package bb.roborally.data.messages;

import bb.roborally.game.User;

public class LogoutConfirmation implements Message {
    private User user;
    public LogoutConfirmation(User user){
        this.user=user;
    }
    public LogoutConfirmation() {}

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
        return new Envelope(Envelope.MessageType.LOGOUT_CONFIRMATION, this);
    }
}
