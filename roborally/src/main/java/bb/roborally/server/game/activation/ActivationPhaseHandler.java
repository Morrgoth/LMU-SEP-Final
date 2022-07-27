package bb.roborally.server.game.activation;

import bb.roborally.protocol.gameplay.CurrentCards;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.PlayerQueue;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.PlayingCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivationPhaseHandler {

    private Server server;
    private Game game;
    private PlayerQueue playerQueue;
    private ServerBoard serverBoard;
    private ArrayList<User> alreadyOnBelts;
    private int register = 1;
    private final int REGISTER_COUNT = 5;

    public ActivationPhaseHandler(Server server, Game game) {
        this.server = server;
        this.game = game;
        this.playerQueue = game.getPlayerQueue();
        this.serverBoard = game.getBoard();
        this.alreadyOnBelts = game.getAlreadyOnBelts();
        //RebootHandler.getInstance().init(server, game);
    }

    public void start() throws IOException {
        while (register <= REGISTER_COUNT) {

            HashMap<Integer, String> cards = playerQueue.getCurrentCards(register);
            CurrentCards currentCards = new CurrentCards(cards);
            server.broadcast(currentCards);
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
        server.startProgrammingPhase();
    }

    public ServerBoard getBoard() {
        return serverBoard;
    }

    public void setBoard(ServerBoard serverBoard) {
        this.serverBoard = serverBoard;
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

    /*public void setRegister(int register) {
        this.register = register;
    }*/
