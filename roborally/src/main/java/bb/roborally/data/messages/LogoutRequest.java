package bb.roborally.data.messages;

import bb.roborally.data.util.User;

public class LogoutRequest implements Message {
    private User user;

    public LogoutRequest(User user) {
        this.user = user;
    }

    public LogoutRequest() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toJson() {
        return this.toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope("LogoutRequest", this);
    }
}
