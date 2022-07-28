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
    private static int register;
    private static final int REGISTER_COUNT = 5;




    public ActivationPhaseHandler(Server server, Game game) {
        this.server = server;
        this.game = game;
        this.playerQueue = game.getPlayerQueue();
        this.serverBoard = game.getBoard();
        //RebootHandler.getInstance().init(server, game);
    }

    public void start() throws IOException {
        setRegister(1);
        while (register <= REGISTER_COUNT) {
            if(register == REGISTER_COUNT) {
                setRegister(1);
                //ProgrammingPhase wieder aufrufen fuer alle Clients
            }
            HashMap<Integer, String> cards = playerQueue.getCurrentCards(register);
            CurrentCards currentCards = new CurrentCards(cards);
            server.broadcast(currentCards);
            PlayingCardHandler playingCardHandler = new PlayingCardHandler(server, game, register);
            for (User user : game.getUsersOrderedByDistance()) {
                PlayingCard currentCard = PlayingCard.fromString(cards.get(user.getClientID()));
                playingCardHandler.handle(user, currentCard);
            }
            TileActivationHandler tileActivationHandler = new TileActivationHandler(server, game, register);
            tileActivationHandler.handle();
            register += 1;
            setRegister(register);
        }
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
        return register;
    }
    public static void setRegister(int reg) {
        register = reg;
    }
}


