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
    private ArrayList<Robot> robots = new ArrayList<>();
    private final String[] availableMaps = new String[] {"DizzyHighway"};
    private boolean mapSelected = false;
    private String selectedMap;

    public Game(int minPlayer) {
        playerQueue = new PlayerQueue(minPlayer);
        initializeRobots();
    }

    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }

    public void setRobotUnavailable(int figureId) {
        for (Robot robot: robots) {
            if (robot.getFigureId() == figureId) {
                robot.setAvailable(false);
            }
        }
    }

    public boolean isRobotAvailable(int figureId) {
        for (Robot robot: robots) {
            if (robot.getFigureId() == figureId) {
                return robot.isAvailable();
            }
        }
        return false;
    }

    public Robot getRobotByFigureId(int figureId) {
        for (Robot robot: robots) {
            if (robot.getFigureId() == figureId) {
                return robot;
            }
        }
        return null;
    }

    private void initializeRobots() {
        Robot twonky = new Robot(1, "Twonky");
        Robot hulk90 = new Robot(2, "Hulk x90");
        Robot hammerBot = new Robot(3, "HammerBot");
        Robot smashBot = new Robot(4, "SmashBot");
        Robot zoomBot = new Robot(5, "ZoomBot");
        Robot spinBot = new Robot(6, "SpinBot");
        robots.add(twonky);
        robots.add(hulk90);
        robots.add(hammerBot);
        robots.add(smashBot);
        robots.add(zoomBot);
        robots.add(spinBot);
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
}
