package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.cards.TurnRight;

import java.io.IOException;

public class TurnRightHandler {
    Server server;
    Game game;
    User user;

    public TurnRightHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException {
        Robot robot = user.getRobot();
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            robot.setRobotOrientation(Orientation.TOP);
            PlayerTurning playerTurning = new PlayerTurning(user.getClientID(), "clockwise");
            server.broadcast(playerTurning);
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            robot.setRobotOrientation(Orientation.BOTTOM);
            PlayerTurning playerTurning = new PlayerTurning(user.getClientID(), "clockwise");
            server.broadcast(playerTurning);
        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            robot.setRobotOrientation(Orientation.RIGHT);
            PlayerTurning playerTurning = new PlayerTurning(user.getClientID(), "clockwise");
            server.broadcast(playerTurning);
        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            robot.setRobotOrientation(Orientation.LEFT);
            PlayerTurning playerTurning = new PlayerTurning(user.getClientID(), "clockwise");
            server.broadcast(playerTurning);
        }
    }
}
