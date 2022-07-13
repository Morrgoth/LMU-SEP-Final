package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Board;

import java.util.ArrayList;

public class RebootHandler {

    private static RebootHandler rebootHandler = null;
    ArrayList<User> users = new ArrayList<>();
    Server server;
    Game game;
    User user;
    Board board;

    private RebootHandler() {}

    public void handle() {
        MovementCheck movementCheck = new MovementCheck(board);
        if(movementCheck.fallingInPit(user)==true){     //HOW TO CHECK WORM CARD
                //REBOOT
        }
    }

    public void init(Server server, Game game) {

    }

    public void addUser(User user) {
        user.setMustReboot(true);
        users.add(user);
    }

    public void reboot() {
        for (User user1: users) {
            if (user.isMustReboot()) {
                // User gets 2 Spam cards
                // Position is set to either StartPoint or Reboot point
                // Choosing reboot direction -> random
                // Set mustReboot to false
            }
        }
    }

    public static RebootHandler getInstance() {
        return null;
    }


    // after rebooting =>take 2 spam cardsand place them in your discard pile
    // && you cant cmplete remaining registers and discard programming and damage cards
    // && place robot on the reboot token and select whichever direction youb want your robot to face but if you started it from start board place the robot on where you started the game
//eger baska robotlar aynı round da rebootlarsa o öbür robotu push eder
// ayrıca öbür robotları shoot edemez ve upgrade kartı kullanamazsın.






}




