package bb.roborally.data.messages;

import bb.roborally.data.messages.type_adapters.LoginRequestTypeAdapter;
import bb.roborally.data.util.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        return new Envelope("LoginConfirmation", this);
    }
}
