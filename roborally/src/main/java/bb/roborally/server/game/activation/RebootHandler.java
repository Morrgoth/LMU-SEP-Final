package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.StartPoint;

import java.util.ArrayList;
import java.util.Random;

public class RebootHandler {

    private static RebootHandler rebootHandler = null;
    ArrayList<User> users = new ArrayList<>();
    Server server;
    Game game;
    User user;
    Board board;


    private RebootHandler() {}

    public void handle(){}

    public void init(Server server, Game game) {}

    public void addUser(User user) {
        user.setMustReboot(true);
        users.add(user);
    }

    public void reboot() {
        for (User user1: users) {
            if (user.isMustReboot()) {

                // User gets 2 Spam cards
                for (int i = 0; i<2; i++ ){
                    user.getProgrammingDeck().getDiscardPile().add(game.getSpamDeck().getSpamDeck().remove(0));
                }

                // Position is set to either StartPoint or Reboot point
                switch (Cell.AMap){
                    case true:
                        Position startPoint = new Position(0,0);
                        user.getRobot().setPosition(startPoint);
                    case false:
                        user.getRobot().setPosition(rebootPoint);


                }

                // Choosing reboot direction -> random**********************************
                    user.getRobot().setRobotOrientation(Random); //random for now

                // Set mustReboot to false
                user.setMustReboot(false);
            }
        }
    }

    public static RebootHandler getInstance() {
        return null;
    }

}




