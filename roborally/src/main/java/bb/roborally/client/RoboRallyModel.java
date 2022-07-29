package bb.roborally.client;

import bb.roborally.client.card.Card;
import bb.roborally.client.map_selector.MapRegistry;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.notification.Notification;
import bb.roborally.client.phase_info.PhaseModel;
import bb.roborally.client.player_list.Player;
import bb.roborally.client.player_list.PlayerQueue;
import bb.roborally.client.popup.Popup;
import bb.roborally.client.popup_damage.DamageView;
import bb.roborally.client.popup_damage.DamageViewModel;
import bb.roborally.client.popup_reboot.RebootView;
import bb.roborally.client.popup_reboot.RebootViewModel;
import bb.roborally.client.programming_interface.PlayerHand;
import bb.roborally.client.robot_selector.Orientation;
import bb.roborally.client.robot_selector.RobotRegistry;
import bb.roborally.protocol.Error;
import bb.roborally.protocol.chat.ReceivedChat;
import bb.roborally.protocol.connection.HelloClient;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.protocol.game_events.*;
import bb.roborally.protocol.gameplay.*;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.GameStarted;
import bb.roborally.protocol.map.MapSelected;
import bb.roborally.protocol.map.SelectMap;
import bb.roborally.protocol.map.tiles.StartPoint;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
/**
 * @author Tolga Engin
 * @author Bence Ament
 * @author Zeynab Baiani
 * @author Muqiu Wang
 */
public class RoboRallyModel {
    private final StringProperty ip = new SimpleStringProperty("");
    private int port;
    private final BooleanBinding ipSet = ip.isEmpty().not();
    private final StringProperty errorMessage = new SimpleStringProperty("");
    private final PlayerQueue playerQueue = new PlayerQueue();
    private final RobotRegistry robotRegistry = new RobotRegistry();
    private final MapRegistry mapRegistry = new MapRegistry();
    private final ObservableList<String> chatMessages = FXCollections.observableArrayList();
    private final ObservableList<String> availableMaps = FXCollections.observableArrayList();
    private final ObservableList<String> damage = FXCollections.observableArrayList("Virus", "Trojan", "Worm");
    private final BooleanProperty gameStarted = new SimpleBooleanProperty(false);
    private Board gameBoard = new Board();
    private final PhaseModel phase = new PhaseModel();
    private final PlayerHand playerHand = new PlayerHand();
    private HashMap<Integer, String> activeCards = null;
    private Orientation startOrientation = Orientation.RIGHT;
    private final BooleanProperty timerRunning = new SimpleBooleanProperty(false);
    public RoboRallyModel() {}
    public StringProperty errorMessageProperty() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage.set(errorMessage);
    }
    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }
    public RobotRegistry getRobotRegistry() {
        return robotRegistry;
    }

    public MapRegistry getMapRegistry() {
        return mapRegistry;
    }

    public ObservableList<String> getObservableListChatMessages() {
        return chatMessages;
    }
    public ObservableList<String> getObservableListAvailableMaps() {
        return availableMaps;
    }
    public BooleanProperty gameStartedProperty() {
        return gameStarted;
    }
    public PhaseModel getPhase() {
        return phase;
    }

    public void process(HelloClient helloClient) {
        if (playerQueue.getLocalPlayer().isIdSet()) {
            HelloServer helloServer = new HelloServer(playerQueue.getLocalPlayerId(), false);
            NetworkConnection.getInstance().send(helloServer);
        } else {
            HelloServer helloServer = new HelloServer(false);
            NetworkConnection.getInstance().send(helloServer);
        }
    }

    public void process(Welcome welcome) {
        ViewManager.openStartMenuView();
        playerQueue.setLocalPlayerId(welcome.getClientID());
    }

    public void process(PlayerAdded playerAdded) {
        playerQueue.addPlayer(playerAdded.getClientID(), playerAdded.getName(),
                robotRegistry.getRobotByFigureId(playerAdded.getFigure()));
        robotRegistry.makeRobotUnavailable(playerAdded.getFigure());
    }

    public void process(PlayerStatus playerStatus) {
        playerQueue.setPlayerReady(playerStatus.getClientID(), playerStatus.isReady());
    }

    public void process(SelectMap selectMap) {
        for (String map: selectMap.getAvailableMaps()) {
            if (!availableMaps.contains(map)) {
                availableMaps.add(map);
            }
        }
        playerQueue.getLocalPlayer().mapSelectorProperty().set(true);
    }

    public void process(MapSelected mapSelected) {
        gameBoard.setName(mapSelected.getMap());
        if (mapSelected.getMap().equals("Death Trap")) {
            startOrientation = Orientation.LEFT;
        }
    }

    public void process(GameStarted gameStarted) {
        gameBoard.setCells(gameStarted.board().getCells());
        this.gameStarted.set(true);
    }

    public void process(ReceivedChat receivedChat) {
        if (receivedChat.isPrivate()) {
            chatMessages.add(playerQueue.getPlayerById(receivedChat.getFrom()).getName() + "(" + receivedChat.getFrom() + ")" + "[Private]: " + receivedChat.getMessage());
        } else {
            chatMessages.add(playerQueue.getPlayerById(receivedChat.getFrom()).getName() + "(" + receivedChat.getFrom() + ")" + ": " + receivedChat.getMessage());
        }
    }

    public void process(ActivePhase activePhase) {
        if (activePhase.getPhase() == 0) {
            phase.setPhase(0);
        } else if (activePhase.getPhase() == 1) {
            phase.setPhase(1);
        } else if (activePhase.getPhase() == 2) {
            phase.setPhase(2);
        } else if (activePhase.getPhase() == 3) {
            phase.setPhase(3);
        }
    }

    public void process(TimerStarted timerStarted) {
        timerRunning.set(true);
    }

    public void process(TimerEnded timerEnded) {
        timerRunning.set(false);
    }

    public void process(CurrentPlayer currentPlayer) {
        if (currentPlayer.getClientID() == playerQueue.getLocalPlayerId()) {
            if (phase.getPhase() == PhaseModel.Phase.BUILD_UP) {
                Notification.getInstance().show_medium(Notification.Kind.INFO, "Choose one of the available Start Points.");
                phase.buildUpActiveProperty().set(true);
            } else if (phase.getPhase() == PhaseModel.Phase.ACTIVATION) {
                PlayCard playCard = new PlayCard(activeCards.get(playerQueue.getLocalPlayerId()));
                NetworkConnection.getInstance().send(playCard);
            }
        }
    }

    public void process(StartingPointTaken startingPointTaken) {
        if (startingPointTaken.getClientID() == playerQueue.getLocalPlayerId()) {
            playerQueue.getLocalPlayer().getRobot().setStartOrientation(startOrientation);
            playerQueue.getLocalPlayer().getRobot().setStartPosition(startingPointTaken.getX(), startingPointTaken.getY());
            ((StartPoint)gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).getTile("StartPoint"))
                    .setTaken(true);
            phase.buildUpActiveProperty().set(false);
        } else {
            playerQueue.getPlayerById(startingPointTaken.getClientID()).getRobot().setStartOrientation(startOrientation);
            playerQueue.getPlayerById(startingPointTaken.getClientID()).getRobot().setStartPosition(startingPointTaken.getX(),
                    startingPointTaken.getY());
            ((StartPoint) gameBoard.get(startingPointTaken.getX(), startingPointTaken.getY()).getTile("StartPoint"))
                    .setTaken(true);
        }
    }

    public void process(YourCards yourCards) {
        playerHand.update(yourCards);
        playerHand.resetProperty().set(true);
    }

    public void process(NotYourCards notYourCards) {
        //
    }

    public void process(ShuffleCoding shuffleCoding) {
        // Ignore for now
    }

    public void process(CardsYouGotNow cardsYouGotNow) {
        int register = 1;
        for (String card: cardsYouGotNow.getCards()) {
            playerHand.getProgram().setRegister(register++, Card.fromString(card));
        }
    }

    public void process(CurrentCards currentCards) {
        for (Integer id: currentCards.getActiveCards().keySet()) {
            playerQueue.getPlayerById(id).getCurrentCard().setType(currentCards.getActiveCards().get(id));
        }
        this.activeCards = currentCards.getActiveCards();
    }

    public void process(Error error) {
        Notification.getInstance().show_medium(Notification.Kind.ERROR, error.getError());
    }

    public void process(Movement movement) {
        playerQueue.getPlayerById(movement.getClientID()).getRobot().setNextPosition(movement.getX(), movement.getY());
        //if (!playerQueue.getPlayerById(movement.getClientID()).isRebooting()){
        //    // TODO: implement rebooting
        //} else{
        //    System.out.println("movement");
        //    playerQueue.getPlayerById(movement.getClientID()).getRobot().setPosition(movement.getX(), movement.getY());
        //}
    }

    public void process(PlayerTurning playerTurning) {
        playerQueue.getPlayerById(playerTurning.getClientID()).getRobot().setNextOrientation(Orientation.toOrientation(playerTurning.getRotation()));
    }

    public void process(DrawDamage drawDamage) {

    }

    public void process(Animation animation){
        //playerQueue.getPlayerById(animation.getType());
    }


    public void process(PickDamage pickDamage) {
        DamageViewModel damageViewModel = new DamageViewModel();
        DamageView damageView = new DamageView(pickDamage.getCount(), pickDamage.getAvailablePiles());
        damageViewModel.connect(damageView);
        Popup.open(damageView.getView());
    }

    public void process(Reboot reboot) {
        playerQueue.getPlayerById(reboot.getClientID()).getRobot().setOrientation(Orientation.TOP);
        playerQueue.getPlayerById(reboot.getClientID()).setRebooting(true);
        RebootViewModel rebootViewModel = new RebootViewModel();
        RebootView rebootView = new RebootView();
        rebootViewModel.connect(rebootView);
        Popup.open(rebootView.getView());
    }

    public void process(Energy energy){
        playerQueue.getPlayerById(energy.getClientID()).getPlayerInventory().setEnergyCubeCount(energy.getCount());
    }

    public void process(CheckPointReached checkPointReached){
        playerQueue.getPlayerById(checkPointReached.getClientID()).getPlayerInventory().incrementCheckpointCount();
    }

    public void process(GameFinished gameFinished) {
        ViewManager.openStartMenuView();
        String winner = playerQueue.getPlayerById(gameFinished.getClientID()).getName();
        Notification.getInstance().show_medium(Notification.Kind.INFO, "The game ended! The winner is: " + winner
            + ". Congratulations!");
        reset();
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getLocalPlayer() {
        return playerQueue.getLocalPlayer();
    }
    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    private void reset() {
        // TODO: reset every game-related part of the model
    }

    public StringProperty ipProperty() {
        return ip;
    }

    public String getIp() {
        return ip.get();
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Boolean getIpSet() {
        return ipSet.get();
    }

    public BooleanBinding ipSetProperty() {
        return ipSet;
    }

    public Orientation getStartOrientation() {
        return startOrientation;
    }

    public boolean isTimerRunning() {
        return timerRunning.get();
    }

    public BooleanProperty timerRunningProperty() {
        return timerRunning;
    }
}
