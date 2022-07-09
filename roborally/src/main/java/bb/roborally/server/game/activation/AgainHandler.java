package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.*;

import java.io.IOException;

public class AgainHandler {

    private Server server;
    private Game game;
    private User user;
    private int register;

    public AgainHandler(Server server, Game game, User user, int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }

    public void handle() {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        PlayingCard prevCard = user.getProgram().getCardInRegister(register - 1);

        if (prevCard instanceof BackUp){
            BackUpHandler backUpHandler = new BackUpHandler(server, game,user);
            backUpHandler.handle();
            Position nextPosition = new Position(position.getX() , position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (prevCard instanceof Move1) {
            Move1Handler move1Handler = new Move1Handler(server,game,user);
            move1Handler.handle();
            Position nextPosition = new Position(position.getX() , position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (prevCard instanceof Move2) {

        } else if (prevCard instanceof Move3) {

        } else if (prevCard instanceof PowerUp) {

        }else if (prevCard instanceof TurnLeft) {

        } else if (prevCard instanceof TurnRight) {

        } else if (prevCard instanceof UTurn) {

        } else if (prevCard instanceof Spam) {

        } else if (prevCard instanceof Trojan) {

        } else if (prevCard instanceof Virus) {

        } else if (prevCard instanceof Worm) {

        }

    }

}
