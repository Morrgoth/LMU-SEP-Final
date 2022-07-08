package bb.roborally.server;

import bb.roborally.protocol.connection.Alive;
import bb.roborally.server.game.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.TimerTask;

public class AliveChecker extends TimerTask {
    private final Server server;
    private final Socket socket;
    private final User user;
    private final DataOutputStream dataOutputStream;

    public AliveChecker(Server server, Socket socket, User user) {
        this.server = server;
        this.user = user;
        this.socket = socket;
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                socket.close();
                server.logout(user);
                this.cancel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
