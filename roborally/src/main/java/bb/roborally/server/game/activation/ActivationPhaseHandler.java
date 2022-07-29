package bb.roborally.server.game.activation;

import bb.roborally.protocol.Error;
import bb.roborally.protocol.gameplay.ActivePhase;
import bb.roborally.protocol.gameplay.CurrentCards;
import bb.roborally.protocol.gameplay.CurrentPlayer;
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

    private final Server server;
    private final Game game;
    private PlayerQueue playerQueue;
    private ServerBoard serverBoard;
    private static int register = 0;
    private static final int REGISTER_COUNT = 5;
    private HashMap<Integer, String> currentCards = new HashMap<Integer, String>();
    private final ArrayList<User> usersOrderedByDistance = new ArrayList<>();

    public ActivationPhaseHandler(Server server, Game game) {
        this.server = server;
        this.game = game;
        this.playerQueue = game.getPlayerQueue();
        this.serverBoard = game.getBoard();
        //RebootHandler.getInstance().init(server, game);
    }

    public void start() throws IOException {
        reset();
        ActivePhase activePhase = new ActivePhase(3);
        server.broadcast(activePhase);
        playNextRegister();
    }

    public boolean hasNextRegister() {
        return register < REGISTER_COUNT;
    }

    public void playNextRegister() {
        if(register == 0){
            for(User user: playerQueue.getUsers()){
                user.setStartingPoint(user.getRobot().getPosition());
            }
        }
        register += 1;
        currentCards.putAll(playerQueue.getCurrentCards(register));
        usersOrderedByDistance.addAll(game.getUsersOrderedByDistance());
        CurrentCards currentCardsMessage = new CurrentCards(currentCards);
        server.broadcast(currentCardsMessage);
        notifyNextPlayer();
    }

    public boolean hasNextPlayer() {
        return currentCards.size() > 0;
    }

    public void playNextPlayer(int clientId) {
        //System.out.println(hasNextPlayer());
        if (hasNextPlayer() && clientId == usersOrderedByDistance.get(0).getClientID()) {
            usersOrderedByDistance.remove(0);
            String card = currentCards.remove(clientId);
            PlayingCardHandler playingCardHandler = new PlayingCardHandler(server, game, register);
            PlayingCard currentCard = PlayingCard.fromString(card);
            try {
                playingCardHandler.handle(playerQueue.getUserById(clientId), currentCard);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (hasNextPlayer()) {
                notifyNextPlayer();
            } else {
                TileActivationHandler tileActivationHandler = new TileActivationHandler(server, game, register);
                try {
                    tileActivationHandler.handle();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (hasNextRegister()) {
                    playNextRegister();
                } else {
                    try {
                        server.startProgrammingPhase();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            server.broadcastOnly(new Error("It is not your turn"), clientId);
        }
    }

    public void notifyNextPlayer() {
        CurrentPlayer currentPlayer = new CurrentPlayer(usersOrderedByDistance.get(0).getClientID());
        server.broadcast(currentPlayer);
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

    public void reset() {
        setRegister(0);
    }
}


