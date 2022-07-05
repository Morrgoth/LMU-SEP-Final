package bb.roborally.game;


import java.util.ArrayList;

/**
 * main class to initialize the game and to follow general game logic
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Game {

    private final PlayerQueue playerQueue;
    private final RobotList robotList;
    private final String[] availableMaps = new String[] {"DizzyHighway"};
    private boolean mapSelected = false;
    private String selectedMap;

    public Game(int minPlayer) {
        playerQueue = new PlayerQueue(minPlayer);
        robotList = new RobotList();
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
}
