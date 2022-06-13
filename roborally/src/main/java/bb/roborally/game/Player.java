package bb.roborally.game;

/**
 * contains all attributes and methods that a player can access to make actions in the game
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Player {

    private String name;
    private final PlayerInventory playerInventory;
    private Robot roboter;
    private boolean hasToReboot;

    public Player(){
        this.playerInventory = new PlayerInventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Robot getRoboter() {
        return roboter;
    }

    public void setRoboter(Robot roboter) {
        this.roboter = roboter;
    }

    public boolean isHasToReboot() {
        return hasToReboot;
    }

    public void setHasToReboot(boolean hasToReboot) {
        this.hasToReboot = hasToReboot;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }
}
