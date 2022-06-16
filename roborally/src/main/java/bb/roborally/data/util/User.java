package bb.roborally.data.util;

public class User {
    private String name;
    private int clientID;
    private int figure;
    private boolean isAI;

    private UserStatus userStatus = UserStatus.VERIFIED;

    public enum UserStatus {
        PENDING,
        VERIFIED,
        EXPIRED
    }

    public User (String name){
        this.name = name;
    }

    public User(int clientID) {
        this.clientID = clientID;
    }

    public User(int clientID, boolean isAI) {
        this.clientID = clientID;
        this.isAI = isAI;
    }

    public User(int clientID, String name, int robotIndex) {
        this.clientID = clientID;
        this.name = name;
        this.figure = robotIndex;
    }
    public User (){}

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * @return The username of the User
     */
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
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
}
