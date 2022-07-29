package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.*;

import java.io.IOException;

/**
 * @author Zeynab Baiani
 */
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

    public void handle() throws IOException {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        PlayingCard prevCard = user.getProgram().getCardInRegister(register - 1);

        if (prevCard instanceof BackUp){
            BackUpHandler backUpHandler = new BackUpHandler(server, game,user);
            backUpHandler.handle();
        } else if (prevCard instanceof Move1) {
            Move1Handler move1Handler = new Move1Handler(server,game,user);
            move1Handler.handle();
        } else if (prevCard instanceof Move2) {
            Move2Handler move2Handler = new Move2Handler(server,game,user);
            move2Handler.handle();
        } else if (prevCard instanceof Move3) {
            Move3Handler move3Handler = new Move3Handler(server,game,user);
            move3Handler.handle();
        } else if (prevCard instanceof PowerUp) {
            PowerUpHandler powerUpHandler = new PowerUpHandler(server,game,user);
            powerUpHandler.handle();
        }else if (prevCard instanceof TurnLeft) {
            TurnLeftHandler turnLeftHandler = new TurnLeftHandler(server,game,user);
            turnLeftHandler.handle();
        } else if (prevCard instanceof TurnRight) {
            TurnRightHandler turnRightHandler = new TurnRightHandler(server,game,user);
            turnRightHandler.handle();
        } else if (prevCard instanceof UTurn) {
            UTurnHandler uTurnHandler = new UTurnHandler(server,game,user);
            uTurnHandler.handle();
        } else if (prevCard instanceof Spam) {
            SpamHandler spamHandler = new SpamHandler(server,game,user,register);
            spamHandler.handle();
        } else if (prevCard instanceof Trojan) {
            TrojanHandler trojanHandler = new TrojanHandler(server,game,user,register);
            trojanHandler.handle();
        } else if (prevCard instanceof Virus) {
            VirusHandler virusCardHandler = new VirusHandler(server,game,user,register);
            virusCardHandler.handle();
        } else if (prevCard instanceof Worm) {
            WormHandler wormHandler = new WormHandler(server,game,user,register);
            wormHandler.handle();
        }

    }

}
