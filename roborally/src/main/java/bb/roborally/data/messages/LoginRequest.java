package bb.roborally.data.messages;

import bb.roborally.game.User;

public class LoginRequest implements Message {
    private User user;
    public LoginRequest(User user){
        this.user = user;
    }
    public LoginRequest(){
    }

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
        return new Envelope(Envelope.MessageType.LOGIN_REQUEST, this);
    }
}
