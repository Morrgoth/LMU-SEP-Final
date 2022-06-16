package bb.roborally.server;

import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.util.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TimerTask;

public class AliveChecker extends TimerTask {

    private DataOutputStream dataOutputStream;
    private User user;

    private Server server;

    public AliveChecker(Server server, DataOutputStream dataOutputStream, User user) {
        this.server = server;
        this.user = user;
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void run() {
        if (user.getUserStatus() == User.UserStatus.VERIFIED) {
            user.setUserStatus(User.UserStatus.PENDING);
            try {
                dataOutputStream.writeUTF((new Alive()).toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (user.getUserStatus() == User.UserStatus.PENDING) {
            user.setUserStatus(User.UserStatus.EXPIRED);
            try {
                dataOutputStream.writeUTF((new Alive()).toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (user.getUserStatus() == User.UserStatus.EXPIRED) {
            try {
                server.clientList.removeClient(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
