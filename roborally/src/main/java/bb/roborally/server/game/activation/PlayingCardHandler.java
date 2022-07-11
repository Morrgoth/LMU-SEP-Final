package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.PlayerQueue;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.cards.*;

import java.io.IOException;

public class PlayingCardHandler {
    private Server server;
    private Game game;
    private int register;
    private PlayerQueue playerQueue;
    private Board board;

    public PlayingCardHandler(Server server, Game game, int register) {
        this.server = server;
        this.game = game;
        this.register = register;
        this.playerQueue = game.getPlayerQueue();
        this.board = game.getBoard();
    }


    public void handle(User user, PlayingCard playingCard) {
        if (playingCard instanceof Again) {
            // TODO: AgainHandler
            AgainHandler againHandler = new AgainHandler(server, game,user, register);
            againHandler.handle();
            /*playerQueue.getUserById(1).getRobot().setPosition(new Position(1, 1));
            CardPlayed cardPlayed = new CardPlayed();
            try {
                server.broadcastExcept(cardPlayed,1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PlayCard playCard = new PlayCard();
            try {
                server.broadcast();
            }*/
        } else if (playingCard instanceof BackUp) {
            // TODO: BackupHandler
        } else if (playingCard instanceof Move1) {
            Move1Handler move1Handler = new Move1Handler(server, game, user);
            move1Handler.handle(user);
        } else if (playingCard instanceof Move2) {
            // TODO Move2Handler
        } else if (playingCard instanceof Move3) {
            // TODO Move3Handler
        } else if (playingCard instanceof PowerUp) {
            // TODO PowerUpHandler
        } else if (playingCard instanceof Spam) {
            // TODO SpamHandler
        } else if (playingCard instanceof Trojan) {
            // TODO TrojanHandler
        } else if (playingCard instanceof TurnLeft) {
            // TODO TurnLeftHandler
        } else if (playingCard instanceof TurnRight) {
            // TODO TurnRightHandler
        } else if (playingCard instanceof UTurn) {
            // TODO UTurnHandler
        } else if (playingCard instanceof Virus) {
            // TODO VirusHandler
        } else if (playingCard instanceof Worm) {
            // TODO WormHandler
        }
    }
}
