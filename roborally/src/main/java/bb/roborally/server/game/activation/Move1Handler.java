package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.ArrayList;


public class Move1Handler {

    Server server;
    Game game;
    User user;


    ArrayList<Orientation> storeOrientations = new ArrayList<>();

    public Move1Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException {

        //Liste aller spieler im Spiel
        ArrayList<User> usersInGame = game.getPlayerQueue().getUsers();

        //erster Spieler wird entfernt, da von ihm aus geschoben wird (er bewegt sich ja selbst durch z.B. Move1Handler

        int x = usersInGame.get(0).getRobot().getPosition().getX();
        int y = usersInGame.get(0).getRobot().getPosition().getY();


        storeOrientationsInList(storeOrientations, usersInGame.get(0).getRobot().getRobotOrientation());

        usersInGame.remove(usersInGame.get(0));

        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);



        //Durchlauf durch alle User im Spiel
        for (User user1 : usersInGame) {
            int x1 = user1.getRobot().getPosition().getX();
            int y1 = user1.getRobot().getPosition().getY();

            if (storeOrientations.get(0) == Orientation.TOP) {
                    if (movementCheck.checkIfBlockedAlt(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))) {
                        server.broadcast(new Movement(user.getClientID(), x, y));
                    } else {
                        usersInGame.get(0).getRobot().setPosition(new Position(x, y - 1));
                        server.broadcast(new Movement(user.getClientID(), x, y - 1));
                    }
                    if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    if (movementCheck.robotForwardCheckForOneStep(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))){
                        handle();
                    }

            } else if (storeOrientations.get(0) == Orientation.LEFT) {
                    if (movementCheck.checkIfBlockedAlt(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))) {
                        server.broadcast(new Movement(user.getClientID(), x, y));
                    } else {
                        usersInGame.get(0).getRobot().setPosition(new Position(x - 1, y));
                        server.broadcast(new Movement(user.getClientID(), x - 1, y));
                    }
                    if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                        server.broadcast(new Reboot(user.getClientID()));
                    }
                    if (movementCheck.robotForwardCheckForOneStep(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))){
                    handle();
                }

            } else if (storeOrientations.get(0) == Orientation.BOTTOM) {
                if (movementCheck.checkIfBlockedAlt(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))) {
                    server.broadcast(new Movement(user.getClientID(), x, y));
                } else {
                    usersInGame.get(0).getRobot().setPosition(new Position(x, y + 1));
                }
                if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                    server.broadcast(new Reboot(user.getClientID()));
                }
                if (movementCheck.robotForwardCheckForOneStep(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))){
                    handle();
                }

            } else if (storeOrientations.get(0) == Orientation.RIGHT){
                if (movementCheck.checkIfBlockedAlt(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))) {
                    server.broadcast(new Movement(user.getClientID(), x, y));
                } else {
                    usersInGame.get(0).getRobot().setPosition(new Position(x + 1, y));
                }
                if (movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)) {
                    server.broadcast(new Reboot(user.getClientID()));
                }
                if (movementCheck.robotForwardCheckForOneStep(usersInGame.get(0).getRobot().getPosition(), storeOrientations.get(0))){
                    handle();
                }
            }
        }
    }

    public void storeOrientationsInList(ArrayList<Orientation> storeOrientations, Orientation orientation){
        storeOrientations.add(orientation);
    }

    public void getOrientations(User user, Orientation orientation){


    }
}





