package bb.roborally.server;

import bb.roborally.map.*;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.Message;
import bb.roborally.protocol.chat.ReceivedChat;
import bb.roborally.protocol.chat.SendChat;
import bb.roborally.protocol.connection.Alive;
import bb.roborally.protocol.game_events.PlayerTurning;
import bb.roborally.protocol.gameplay.*;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.lobby.PlayerValues;
import bb.roborally.protocol.lobby.SetStatus;
import bb.roborally.protocol.map.GameStarted;
import bb.roborally.protocol.map.MapSelected;
import bb.roborally.protocol.map.SelectMap;
import bb.roborally.protocol.map.tiles.StartPoint;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.PlayingCardHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.PlayingCard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private final ClientList clientList = new ClientList();
    private final Game game = new Game(1); // TODO: cmd arg, 1 for testing purposes
    private final ChatHistory chatHistory = new ChatHistory();
    public static void main(String[] args) {
        Server server = new Server();
        server.registerUsers();
    }

    public Server() {
        setupLogger();
    }

    public Game getGame() {
        return game;
    }

    /**
     * Waits for and handles the Login Requests of Users.
     */
    public void registerUsers() {
        try {
            int PORT = 6868;
            ServerSocket server = new ServerSocket(PORT);
            InetAddress inetAddress = InetAddress.getLocalHost();
            LOGGER.info("Server started running on " + inetAddress.getHostAddress() + ":" + PORT);
            while (true) {
                Socket clientSocket = server.accept();
                if(clientSocket != null) {
                    ServerThread serverThread = new ServerThread(this, clientSocket);
                    serverThread.start();
                }
            }
        }
        catch(Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void logout(User user) {
        clientList.clearClientList();
        LOGGER.info("User (" + user.getClientID() + ") has left.");
        game.getPlayerQueue().remove(user);

    }

    public void broadcast(Message message) {
        for (Socket socket: clientList.getAllClients()) {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println(message.toJson());
                LOGGER.info("Outgoing: " + message.toJson());
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public void broadcast(Message message, long delayMillis) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Socket socket: clientList.getAllClients()) {
                    PrintWriter printWriter = null;
                    try {
                        printWriter = new PrintWriter(socket.getOutputStream(), true);
                        printWriter.println(message.toJson());
                        LOGGER.info("Outgoing: " + message.toJson());
                    } catch (IOException e) {
                        LOGGER.severe(e.getMessage());
                    }
                }
            }
        }.start();
    }

    /**
     * This method can be used to broadcast messages to subsets of all users.
     * @throws IOException
     */
    public void broadcastOnly(Message message, int targetClientId) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(clientList.getClient(targetClientId).getOutputStream(), true);
            printWriter.println(message.toJson());
            LOGGER.info("Outgoing: " + message.toJson());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void broadcastOnly(Message message, int targetClientId, long delayMillis) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                PrintWriter printWriter = null;
                try {
                    printWriter = new PrintWriter(clientList.getClient(targetClientId).getOutputStream(), true);
                    printWriter.println(message.toJson());
                    LOGGER.info("Outgoing: " + message.toJson());
                } catch (IOException e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        }.start();
    }

    public void broadcastExcept(Message message, int exceptClientId) {
        for (Socket socket: clientList.getAllClientsExcept(exceptClientId)) {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(message.toJson());
                LOGGER.info("Outgoing: " + message.toJson());
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public void updateUser(int clientId) throws IOException {
        for (Message message: game.getPlayerQueue().generatePlayersUpdate()) {
            broadcastOnly(message, clientId);
        }
        for (ReceivedChat receivedChat: chatHistory.getPublicMessages()) {
            broadcastOnly(receivedChat, clientId);
        }
    }

    public void process(Alive alive, User user) {
        user.setUserStatus(User.UserStatus.VERIFIED);
    }

    public void process(PlayerValues playerValues, User user) throws IOException {
        if (game.getRobotList().isRobotAvailable(playerValues.getFigure())) {
            user.setName(playerValues.getName());
            user.setRobot(game.getRobotList().getRobotByFigureId(playerValues.getFigure()));
            game.getRobotList().makeUnavailable(playerValues.getFigure());
            PlayerAdded playerAdded = new PlayerAdded(user.getClientID(), user.getName(), playerValues.getFigure());
            game.getPlayerQueue().add(user);
            broadcast(playerAdded);
        } else {
            Error error = new Error("Robot is already taken! Choose another one.");
            broadcastOnly(error, user.getClientID());
        }
    }

    public void process(SetStatus setStatus, User user) throws IOException {
        user.setReady(setStatus.isReady());
        PlayerStatus playerStatus = new PlayerStatus(user.getClientID(), user.isReady());
        game.getPlayerQueue().update(playerStatus);
        broadcast(playerStatus);
        // Send the SelectMap message if necessary
        if (game.getPlayerQueue().isMapSelectorAvailable() && !game.getPlayerQueue().isMapSelectorNotified()) {
            SelectMap selectMap = new SelectMap(game.getAvailableMaps());
            broadcastOnly(selectMap, game.getPlayerQueue().getMapSelectorClientId());
            game.getPlayerQueue().setMapSelectorNotified(true);
        }
    }

    public void process(SendChat sendChat, User user) throws IOException {
        if (sendChat.getTo() == -1) {
            ReceivedChat receivedChat = new ReceivedChat(sendChat.getMessage(), user.getClientID(), false);
            chatHistory.addMessage(receivedChat);
            broadcast(receivedChat);
        } else {
            ReceivedChat receivedChat = new ReceivedChat(sendChat.getMessage(), user.getClientID(), true);
            broadcastOnly(receivedChat, user.getClientID());
            broadcastOnly(receivedChat, sendChat.getTo());
        }
    }

    public void process(MapSelected mapSelected, User user) throws IOException {
        if (game.getPlayerQueue().isGameReadyToStart()) {
            if (user.getClientID() == game.getPlayerQueue().getMapSelectorClientId()) {
                if (Arrays.stream(game.getAvailableMaps()).anyMatch(map -> map.equals(mapSelected.getMap()))) {
                    game.setMapSelected(true);
                    game.setSelectedMap(mapSelected.getMap());
                    if (game.getSelectedMap().equals("Dizzy Highway")) {
                        GameStarted dizzyHighWay = new DizzyHighwayBuilder().build();
                        game.setBoard(new ServerBoard(dizzyHighWay.board()));
                        broadcast(dizzyHighWay);
                    } else if (game.getSelectedMap().equals("Death Trap")) {
                        GameStarted deathTrap = new DeathTrapBuilder().build();
                        game.setBoard(new ServerBoard(deathTrap.board()));
                        broadcast(deathTrap);
                    } else if (game.getSelectedMap().equals("Extra Crispy")) {
                        GameStarted extraCrispy = new ExtraCrispyBuilder().build();
                        game.setBoard(new ServerBoard(extraCrispy.board()));
                        broadcast(extraCrispy);
                    } else if (game.getSelectedMap().equals("Lost Bearings")) {
                        GameStarted lostBearings = new LostBearingsBuilder().build();
                        game.setBoard(new ServerBoard(lostBearings.board()));
                        broadcast(lostBearings);
                    } else if (game.getSelectedMap().equals("Twister")) {
                        GameStarted twister = new TwisterBuilder().build();
                        game.setBoard(new ServerBoard(twister.board()));
                        broadcast(twister);
                    }
                    startBuildUpPhase();
                }
            }
        } else {
            Error error = new Error("Error: There are not enough ready players in the lobby!");
            broadcastOnly(error, user.getClientID());
        }
    }

    private void startBuildUpPhase() {
        broadcast(new ActivePhase(0));
        game.setPhase(Game.Phase.BUILD_UP);
        game.getPlayerQueue().resetBuildUpPhaseCurrentUserId();
        game.getPlayerQueue().setNextBuildUpPhaseCurrentUser();
        broadcastOnly(new CurrentPlayer(game.getPlayerQueue().getBuildUpPhaseCurrentUserId()),
                game.getPlayerQueue().getBuildUpPhaseCurrentUserId());
    }

    public void process(SetStartingPoint setStartingPoint, User user) throws IOException {
        if (!user.isStartingPointSet()) {
            if (user.getClientID() == game.getPlayerQueue().getBuildUpPhaseCurrentUserId()) {
                int x = setStartingPoint.getX();
                int y = setStartingPoint.getY();
                if (game.getBoard().get(x, y).hasTile("StartPoint")) {
                    StartPoint startPoint = (StartPoint) game.getBoard().get(x, y).getTile("StartPoint");
                    if (!startPoint.isTaken()) {
                        user.getRobot().setPosition(new Position(x, y));
                        user.setStartingPointSet(true);
                        startPoint.setTaken(true);
                        StartingPointTaken startingPointTaken = new StartingPointTaken(x, y, user.getClientID());
                        broadcast(startingPointTaken);
                        if (game.getPlayerQueue().isBuildUpPhaseFinished()) {
                            startProgrammingPhase();
                        } else {
                            game.getPlayerQueue().setNextBuildUpPhaseCurrentUser();
                            broadcastOnly(new CurrentPlayer(game.getPlayerQueue().getBuildUpPhaseCurrentUserId()),
                                    game.getPlayerQueue().getBuildUpPhaseCurrentUserId());
                        }
                    }
                }
            } else {
                broadcastOnly(new Error("It is not your turn."), user.getClientID());
            }
        } else {
            broadcastOnly(new Error("StartingPoint has already been set."), user.getClientID());
        }
    }

    public void startProgrammingPhase() throws IOException {
        ActivePhase activePhase = new ActivePhase(2);
        broadcast(activePhase);
        for (User user: game.getPlayerQueue().getUsers()) {
            user.getProgram().reset();
            ArrayList<PlayingCard> hand = user.getProgrammingDeck().drawHand();
            YourCards yourCards = new YourCards(hand);
            broadcastOnly(yourCards, user.getClientID());
            if (user.getProgrammingDeck().isReshuffleNeeded()) {
                ShuffleCoding shuffleCoding = new ShuffleCoding(user.getClientID());
                broadcast(shuffleCoding);
            }
            NotYourCards notYourCards = new NotYourCards(user.getClientID(), hand.size());
            broadcastExcept(notYourCards, user.getClientID());
        }
    }

    public void process(SelectedCard selectedCard, User user) throws IOException {
        PlayingCard card = PlayingCard.fromString(selectedCard.getCard());
        user.getProgram().add(card, selectedCard.getRegister());
        CardSelected cardSelected;
        if (card == null) {
            cardSelected = new CardSelected(user.getClientID(), selectedCard.getRegister(), false);
        } else {
            cardSelected = new CardSelected(user.getClientID(), selectedCard.getRegister(), true);
        }
        broadcast(cardSelected);
        if (user.getProgram().isReady() && !game.isTimerRunning()) {
            game.setTimerRunning(true);
            SelectionFinished selectionFinished = new SelectionFinished(user.getClientID());
            broadcast(selectionFinished);
            TimerStarted timerStarted = new TimerStarted();
            broadcast(timerStarted);
            (new Thread() { public void run() {
                try {
                    Thread.sleep(30000);
                    if (game.isTimerRunning()) {
                        int[] incompleteProgramUsers = game.getPlayerQueue().getIncompleteProgramUserIds();
                        TimerEnded timerEnded = new TimerEnded(incompleteProgramUsers);
                        broadcast(timerEnded);
                        for (int clientId: incompleteProgramUsers) {
                            String[] randomProgram = game.getPlayerQueue().getUserById(clientId).getProgrammingDeck()
                                    .generateRandomProgram();
                            CardsYouGotNow cardsYouGotNow = new CardsYouGotNow(randomProgram);
                            game.getPlayerQueue().getUserById(clientId).getProgram().set(randomProgram);
                            broadcastOnly(cardsYouGotNow, clientId);
                        }
                        game.setTimerRunning(false);
                        ActivePhase activePhase = new ActivePhase(3);
                        broadcast(activePhase);
                        ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(Server.this, game);
                        activationPhaseHandler.start();
                    }
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            } }).start();
        }

        if (game.getPlayerQueue().areAllProgramsReady()) {
            game.setTimerRunning(false);
            ActivePhase activePhase = new ActivePhase(3);
            broadcast(activePhase);
            ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(Server.this, game);
            activationPhaseHandler.start();
        }
    }




    public ClientList getClientList() {
        return clientList;
    }

    private static void setupLogger(){
        LOGGER.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("log/server.log");
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
