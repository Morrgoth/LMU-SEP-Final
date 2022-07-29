package bb.roborally.server.game;

import bb.roborally.protocol.Position;
import bb.roborally.server.game.deck.ProgrammingDeck;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The class that represents the User.
 * @author Bence Ament
 * @author Veronika Heckel
 * @author Tolga Engin
 */
public class User {
    private String name;
    private int clientID;
    private boolean isAI;
    private Robot robot;
    private boolean online = false;
    private final BooleanProperty playerAdded = new SimpleBooleanProperty(false);
    private final BooleanProperty ready = new SimpleBooleanProperty(false);
    private boolean startingPointSet = false;
    private Position startingPoint;
    private UserStatus userStatus = UserStatus.VERIFIED;
    private PlayerInventory playerInventory = new PlayerInventory();
    private final Program program = new Program();
    private boolean mustReboot = false;

    public boolean getMustReboot(){
        return mustReboot;
    }

    public Program getProgram() {
        return program;
    }

    public boolean isMustReboot() {
        return mustReboot;
    }

    public void setMustReboot(boolean mustReboot) {
        this.mustReboot = mustReboot;
    }

    public Position getStartingPoint() {
        return startingPoint;
    }
    public int getStartingPointX() {
        return startingPoint.getX();
    }
    public int getStartingPointY() {
        return startingPoint.getY();
    }

    public void setStartingPoint(Position startingPoint) {
        this.startingPoint = startingPoint;
    }
    public void setStartingPoint (int x,int y){
        startingPoint = new Position(x,y);
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public enum UserStatus {
        PENDING,
        VERIFIED,
        EXPIRED
    }

    public User () {

    }
    public User(int clientID) {
        setClientID(clientID);
    }
    public User(int clientID, boolean isAI) {
        setClientID(clientID);
        this.isAI = isAI;
        this.online = true;
    }
    public User(int clientID, String name) {
        setClientID(clientID);
        this.name = name;
        this.online = true;
    }
    public User(int clientID, String name,Position startingPoint) {
        setClientID(clientID);
        this.name = name;
        this.startingPoint = startingPoint;
    }

    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
        this.online = true;
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
