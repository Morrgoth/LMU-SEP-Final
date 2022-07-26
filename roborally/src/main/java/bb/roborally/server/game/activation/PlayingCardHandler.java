package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.PlayerQueue;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.*;

import java.io.IOException;

public class PlayingCardHandler {
    private Server server;
    private Game game;
    private int register;
    private PlayerQueue playerQueue;
    private ServerBoard serverBoard;

    public PlayingCardHandler(Server server, Game game, int register) {
        this.server = server;
        this.game = game;
        this.register = register;
        this.playerQueue = game.getPlayerQueue();
        this.serverBoard = game.getBoard();
    }


    public void handle(User user, PlayingCard playingCard) throws IOException{
        if (playingCard instanceof Again) {
            AgainHandler againHandler = new AgainHandler(server, game,user, register);
            againHandler.handle();
        } else if (playingCard instanceof BackUp) {
            BackUpHandler backUpHandler = new BackUpHandler(server, game, user);
            backUpHandler.handle();
        } else if (playingCard instanceof Move1) {
            Move1Handler move1Handler = new Move1Handler(server, game, user);
            move1Handler.handle();
        } else if (playingCard instanceof Move2) {
            Move2Handler move2Handler = new Move2Handler(server, game, user);
            move2Handler.handle();
        } else if (playingCard instanceof Move3) {
            Move3Handler move3Handler = new Move3Handler(server, game, user);
            move3Handler.handle();
        } else if (playingCard instanceof PowerUp) {
            PowerUpHandler powerUpHandler = new PowerUpHandler(server, game, user);
            powerUpHandler.handle();
        } else if (playingCard instanceof Spam) {
            SpamHandler spamHandler = new SpamHandler(server, game, user, register);
            spamHandler.handle();
        } else if (playingCard instanceof Trojan) {
            TrojanHandler trojanHandler = new TrojanHandler(server, game, user,register);
            trojanHandler.handle();
        } else if (playingCard instanceof TurnLeft) {
            TurnLeftHandler turnLeftHandler = new TurnLeftHandler(server, game, user);
            turnLeftHandler.handle();
        } else if (playingCard instanceof TurnRight) {
            TurnRightHandler turnRightHandler = new TurnRightHandler(server, game, user);
            turnRightHandler.handle();
        } else if (playingCard instanceof UTurn) {
            UTurnHandler uTurnHandler = new UTurnHandler(server, game, user);
            uTurnHandler.handle();
        } else if (playingCard instanceof Virus) {
            VirusHandler virusHandler = new VirusHandler(server, game, user, register);
            virusHandler.handle();
        } else if (playingCard instanceof Worm) {
            WormHandler wormHandler = new WormHandler(server, game, user, register);
            wormHandler.handle();
        }
    }
}
