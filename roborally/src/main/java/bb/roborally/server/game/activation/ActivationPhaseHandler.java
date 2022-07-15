package bb.roborally.server.game.activation;

import bb.roborally.protocol.gameplay.CurrentCards;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.PlayerQueue;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivationPhaseHandler {

    private Server server;
    private Game game;
    private PlayerQueue playerQueue;
    private Board board;
    private ArrayList<User> alreadyOnBelts;
    private int register = 1;
    private final int REGISTER_COUNT = 5;

    public ActivationPhaseHandler(Server server, Game game) {
        this.server = server;
        this.game = game;
        this.playerQueue = game.getPlayerQueue();
        this.board = game.getBoard();
        this.alreadyOnBelts = game.getAlreadyOnBelts();
        //RebootHandler.getInstance().init(server, game);
    }

    public void start() throws IOException {
        while (register <= REGISTER_COUNT) {

            HashMap<Integer, String> cards = playerQueue.getCurrentCards(register);
            CurrentCards currentCards = new CurrentCards(cards);
            try {
                server.broadcast(currentCards);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PlayingCardHandler playingCardHandler = new PlayingCardHandler(server, game, register);
            for (User user : game.getUsersOrderedByDistance()) {
                PlayingCard currentCard = PlayingCard.fromString(cards.get((Integer) user.getClientID()));
                playingCardHandler.handle(user, currentCard);
            }
            TileActivationHandler tileActivationHandler = new TileActivationHandler(server, game, register, alreadyOnBelts);
            tileActivationHandler.handle();
            register += 1;
        }
        // RebootHandler.getInstance().reboot();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }

    public void setPlayerQueue(PlayerQueue playerQueue) {
        this.playerQueue = playerQueue;
    }

    public static int getRegister() {
        return 0;
    }
}
        //  return register;
        //}

    /*public void setRegister(int register) {
        this.register = register;
    }*/
