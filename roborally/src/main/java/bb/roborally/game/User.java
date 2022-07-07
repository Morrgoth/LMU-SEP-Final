package bb.roborally.game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class User {
    private String name;
    private int clientID;
    private boolean isAI;
    private Robot robot;
    private final BooleanProperty playerAdded = new SimpleBooleanProperty(false);
    private final BooleanProperty ready = new SimpleBooleanProperty(false);
    private boolean startingPointSet = false;
    private UserStatus userStatus = UserStatus.VERIFIED;
    private PlayerInventory playerInventory = new PlayerInventory();
    private final Program program = new Program();

    public Program getProgram() {
        return program;
    }

    public enum UserStatus {
        PENDING,
        VERIFIED,
        EXPIRED
    }

    public User () {

    }
    public User(int clientID) {
        this.clientID = clientID;
    }
    public User(int clientID, boolean isAI) {
        this.clientID = clientID;
        this.isAI = isAI;
    }
    public User(int clientID, String name) {
        this.clientID = clientID;
        this.name = name;
    }

    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BooleanProperty playerAddedProperty() {
        return playerAdded;
    }
    public boolean isPlayerAdded() {
        return playerAdded.get();
    }
    public void setPlayerAdded(boolean playerAdded) {
        this.playerAdded.set(playerAdded);
    }
    public BooleanProperty readyProperty() {
        return ready;
    }
    public boolean isReady() {
        return ready.get();
    }
    public void setReady(boolean ready) {
        this.ready.set(ready);
    }
    public Robot getRobot() {
        return robot;
    }
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    public boolean isAI() {
        return isAI;
    }
    public void setAI(boolean AI) {
        isAI = AI;
    }
    public UserStatus getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
    public boolean isStartingPointSet() {
        return startingPointSet;
    }
    public void setStartingPointSet(boolean startingPointSet) {
        this.startingPointSet = startingPointSet;
    }
    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }
    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }
    public ProgrammingDeck getProgrammingDeck() {
        return playerInventory.getProgrammingDeck();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        User other = (User) obj;

        return this.getClientID() == other.getClientID();
    }
    @Override
    public int hashCode() {
        return clientID;
    }
    @Override
    public String toString() {
        return getName() + "(" + getClientID() + ")";
    }
}
