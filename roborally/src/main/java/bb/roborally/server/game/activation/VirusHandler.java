package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;

import java.util.ArrayList;

public class VirusHandler {

    Server server;
    Game game;
    User user;

    public VirusHandler(Server server, Game game, User user){
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle(){
        Robot robot1 = user.getRobot();
        ArrayList<Robot> robots = new ArrayList<>();
        Position position1 = robot1.getPosition();
        //Position position2 = robot2.getPosition();

        //Check 6 Fields above Robot if another Robot in Range
        for(int i = 1; i < position1.getY(); i++){
            MovementCheck movementCheck = new MovementCheck();
            movementCheck.robotForwardCheck(user, user);
            //robots.add();


            }
        }



    }

