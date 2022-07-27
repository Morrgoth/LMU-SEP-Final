package bb.roborally.server.game;


import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.board.ServerCell;
import bb.roborally.server.game.deck.SpamDeck;
import bb.roborally.server.game.deck.TrojanDeck;
import bb.roborally.server.game.deck.VirusDeck;
import bb.roborally.server.game.deck.WormDeck;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * main class to initialize the game and to follow general game logic
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Game {

    public enum Phase {
        BUILD_UP,
        PROGRAMMING,
        ACTIVATION
    }

    private Phase phase = Phase.BUILD_UP;
    private final PlayerQueue playerQueue;
    private final RobotList robotList = new RobotList();
    private final String[] availableMaps = new String[] {"DizzyHighway", "DeathTrap", "ExtraCrispy",
            "LostBearings", "Twister"};
    private boolean mapSelected = false;
    private String selectedMap;
    private ServerBoard serverBoard = null;
    private final SpamDeck spamDeck = new SpamDeck() ;
    private final TrojanDeck trojanDeck = new TrojanDeck();
    private final VirusDeck virusDeck = new VirusDeck();
    private boolean timerStarted = false;
    private final WormDeck wormDeck = new WormDeck();

    public Game(int minPlayer) {
        playerQueue = new PlayerQueue(minPlayer);
    }

    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }

    public String[] getAvailableMaps() {
        return availableMaps;
    }

    public boolean isMapSelected() {
        return mapSelected;
    }

    public void setMapSelected(boolean mapSelected) {
        this.mapSelected = mapSelected;
    }

    public String getSelectedMap() {
        return selectedMap;
    }

    public void setSelectedMap(String selectedMap) {
        this.selectedMap = selectedMap;
    }

    public RobotList getRobotList() {
        return robotList;
    }

    public ServerBoard getBoard() {
        return serverBoard;
    }

    public void setBoard(ServerBoard serverBoard) {
        this.serverBoard = serverBoard;
    }

    public SpamDeck getSpamDeck() {
        return spamDeck;
    }

    public TrojanDeck getTrojanDeck() {
        return trojanDeck;
    }

    public VirusDeck getVirusDeck() {
        return virusDeck;
    }

    public WormDeck getWormDeck() {
        return wormDeck;
    }

    public ArrayList<User> getUsersOrderedByDistance() {
        ArrayList<User> userOrderedByDistance = new ArrayList<>();
        HashMap<User, Integer> usersWithDistancePower = new HashMap<>();
        int[] usersDistancesPower = new int[playerQueue.getUsers().size()];
        ServerCell antenna = serverBoard.getAntenna();
        int xAntenna = antenna.getPosition().getX();
        int yAntenna = antenna.getPosition().getY();

        //save the users with corresponding distances(power) from Antenna
        for(int i = 0; i<playerQueue.getUsers().size(); i++){
            int x = playerQueue.getUsers().get(i).getRobot().getPosition().getX();
            int y = playerQueue.getUsers().get(i).getRobot().getPosition().getY();
            int xDifferToPower = (int) Math.pow(x - xAntenna, 2);
            int yDifferToPower = (int) Math.pow(y - yAntenna, 2);
            usersWithDistancePower.put(playerQueue.getUsers().get(i), xDifferToPower + yDifferToPower);
            usersDistancesPower[i] = xDifferToPower + yDifferToPower;
        }
        //sort the distances in ascending order
        for (int i = 0; i < usersDistancesPower.length; i++) {
            for (int j = 0; j < usersDistancesPower.length; j++) {
                if (usersDistancesPower[i] < usersDistancesPower[j]) {
                    int temp = usersDistancesPower[i];
                    usersDistancesPower[i] = usersDistancesPower[j];
                    usersDistancesPower[j] = temp;
                }
            }
        }
        //add the users into the list ordered by distance
        for(int i = 0; i<usersDistancesPower.length; i++){
            for(User key: usersWithDistancePower.keySet()){
                if(usersWithDistancePower.get(key) == usersDistancesPower[i]){
                    userOrderedByDistance.add(key);
                }
            }
        }
        return userOrderedByDistance;
    }

    public ArrayList<Position> getUsersPositions(){
        ArrayList<Position> usersPositions = new ArrayList<>();
        for(User user: playerQueue.getUsers()){
            usersPositions.add(user.getRobot().getPosition());
        }
        return usersPositions;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
