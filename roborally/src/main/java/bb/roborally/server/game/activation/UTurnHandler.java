package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;

public class UTurnHandler {
    Server server;
    Game game;
    User user;

    public UTurnHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException {
        Robot robot = user.getRobot();
        //server.broadcastOnly(new PlayCard("U-Turn"), user.getClientID());
        server.broadcast(new CardPlayed(user.getClientID(), "U-Turn"));
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            robot.setRobotOrientation(Orientation.RIGHT);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            robot.setRobotOrientation(Orientation.LEFT);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            robot.setRobotOrientation(Orientation.BOTTOM);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            robot.setRobotOrientation(Orientation.TOP);
            server.broadcast(new PlayerTurning(user.getClientID(), "clockwise"));
        }
    }
}
