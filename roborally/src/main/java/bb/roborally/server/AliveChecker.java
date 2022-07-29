package bb.roborally.server;

import bb.roborally.protocol.connection.Alive;
import bb.roborally.server.game.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.TimerTask;

/**
 * A thread to send Alive messages to the client repeatedly every five seconds
 *
 * @author Bence Ament
 */
public class AliveChecker extends TimerTask {
    private final Server server;
    private final Socket socket;
    private final User user;
    private final PrintWriter outputStream;

    public AliveChecker(Server server, Socket socket, User user) {
        this.server = server;
        this.user = user;
        this.socket = socket;
        try {
            this.outputStream = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        if (user.getUserStatus() == User.UserStatus.VERIFIED) {
            user.setUserStatus(User.UserStatus.PENDING);
            server.broadcastOnly(new Alive(), user.getClientID());
        } else if (user.getUserStatus() == User.UserStatus.PENDING) {
            user.setUserStatus(User.UserStatus.EXPIRED);
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
