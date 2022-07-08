package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;

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

    }

}
