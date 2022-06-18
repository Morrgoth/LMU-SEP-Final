package bb.roborally.game;

/**
 * contains all attributes and methods that a player can access to make actions in the game
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @author  Philipp Keyzman
 */
public class Player {

    private String name;
    private static PlayerInventory playerInventory;
    private Robot robot;
    private boolean hasToReboot;

    public Player(){
        playerInventory = new PlayerInventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
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
