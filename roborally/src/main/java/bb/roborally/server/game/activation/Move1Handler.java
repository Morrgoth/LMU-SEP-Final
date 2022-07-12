package bb.roborally.server.game.activation;


import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import java.io.IOException;


public class Move1Handler {

    Server server;
    Game game;
    User user;

    public Move1Handler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;
    }

    public void handle() throws IOException {
        Robot robot = user.getRobot();
        Position position = user.getRobot().getPosition();
        Orientation orientation = user.getRobot().getRobotOrientation();
        int x = position.getX();
        int y = position.getY();

        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        if(movementCheck.checkIfBlocked(user, orientation)){
            server.broadcast(new Movement(user.getClientID(), x, y));
        }else{
            switch (orientation){
                case TOP:
                    robot.setPosition(new Position(x, y-1));
                    server.broadcast(new Movement(user.getClientID(), x, y-1));
                case LEFT:
                    robot.setPosition(new Position(x-1, y));
                    server.broadcast(new Movement(user.getClientID(), x-1, y));
                case BOTTOM:
                    robot.setPosition(new Position(x, y+1));
                    server.broadcast(new Movement(user.getClientID(), x, y+1));
                case RIGHT:
                    robot.setPosition(new Position(x+1, y));
                    server.broadcast(new Movement(user.getClientID(), x+1, y));
            }
            if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                server.broadcast(new Reboot(user.getClientID()));
            }else{
                movementCheck.pushRobot(server, game, user, orientation, 1);
            }
        }
    }
        //setzen der schritte nach vorne - bei Move1 = 1
        /*movementCheck.setNumberOfPositions(1);

        //Abfrage, ob eine Wand vor dem Roboter ist, wenn ja: neue Position ist alte Position
        if(movementCheck.wallForwardCheck(user)){
            robot.setPosition(new Position(x,y));
            server.broadcast(new Movement(user.getClientID(), x, y));
        } else {
            //for - schleife über alle schritte die einen schritt vorgegangen werden soll
            for(int i = 0; i < movementCheck.getNumberOfPositions(); i++){
                //Frage, ob sich einer oder mehrere  Roboter vor dem sich bewegenden Roboter sind, falls nein: bewegung nur für den einen sich bewegenden Roboter
                    //Abfrage über die Orientierung wohin der Roboter sich bewegen soll
                    switch (orientation) {
                        //oben
                        case TOP:
                            if(!movementCheck.robotForwardCheck(game, robot, orientation)) {
                                //neue Position: x , y -1
                                robot.setPosition(new Position(x, y - movementCheck.getNumberOfPositions()));
                                server.broadcast(new Movement(user.getClientID(), x, y - movementCheck.getNumberOfPositions()));
                            }else{
                                movementCheck.pushRobotForward();
                                robot.setPosition(new Position(x, y - movementCheck.getNumberOfPositions()));
                                server.broadcast(new Movement(user.getClientID(), x, y - movementCheck.getNumberOfPositions()));


                            //Frage ob ein Pit passiert wird --> Reboot
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            //Frage, ob sich der Roboter außerhalb des Boards befindet --> Reboot
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        //unten
                        case BOTTOM:
                            //neue Position: x, y + 1
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y + movementCheck.getNumberOfPositions()));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        //links
                        case LEFT:
                            //neue Position: x - 1, y
                            robot.setPosition(new Position(x - movementCheck.getNumberOfPositions(), y));
                            server.broadcast(new Movement(user.getClientID(), x - movementCheck.getNumberOfPositions(), y));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if(movementCheck.robotIsOffBoard(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        //rechts
                        case RIGHT:
                            //neue Position: x + 1, y
                            robot.setPosition(new Position(x + movementCheck.getNumberOfPositions(), y));
                            server.broadcast(new Movement(user.getClientID(), x + movementCheck.getNumberOfPositions(), y));
                            if (movementCheck.fallingInPit(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if (movementCheck.robotIsOffBoard(user)) {
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if(movementCheck.robotIsOffBoard(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }

                        }
                    //Falls sich Roboter vor dem sich bewegenden Roboter befingden (movementCheck.robotIsForward = true)
                    }else{
                    //Abfrage nach Orientierung des sich bewegenden Roboters
                    switch (orientation){
                        //oben
                        case TOP:
                            //Methode, die alle Roboter, die sich vor dem bewegenden Roboter befinden nach vorne schiebt, um die Anzahl der Angegebenen Felder: hier 1
                            movementCheck.pushRobotForward(game, user, orientation);
                            //setzen der neuen Position des bewegenden Roboters  - er bewegt sich ja auch eins vor --> x, y-1
                            robot.setPosition(new Position(x, y - movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y - movementCheck.getNumberOfPositions()));
                            //check: Pit
                            if(movementCheck.fallingInPit(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            //check: robotIsOffBoard
                            if(movementCheck.robotIsOffBoard(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        //unten
                        case BOTTOM:
                            movementCheck.pushRobotForward(game, user, orientation);
                            //x, y +1
                            robot.setPosition(new Position(x, y + movementCheck.getNumberOfPositions()));
                            server.broadcast(new Movement(user.getClientID(), x, y + movementCheck.getNumberOfPositions()));
                            if(movementCheck.fallingInPit(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if(movementCheck.robotIsOffBoard(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        //links
                        case LEFT:
                            movementCheck.pushRobotForward(game, user, orientation);
                            //x - 1, y
                            robot.setPosition(new Position(x - movementCheck.getNumberOfPositions(), y));
                            server.broadcast(new Movement(user.getClientID(), x - movementCheck.getNumberOfPositions(), y));
                            if(movementCheck.fallingInPit(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if(movementCheck.robotIsOffBoard(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        //rechts
                        case RIGHT:
                            //x + 1, y
                            movementCheck.pushRobotForward(game, user, orientation);
                            robot.setPosition(new Position(x + movementCheck.getNumberOfPositions(), y));
                            server.broadcast(new Movement(user.getClientID(), x + movementCheck.getNumberOfPositions() , y));
                            if(movementCheck.fallingInPit(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                            if(movementCheck.robotIsOffBoard(user)){
                                server.broadcast(new Reboot(user.getClientID()));
                            }
                        }
                    }
                }
            }*/
            /*switch (user.getRobot().getRobotOrientation()){
                case TOP:
                    user.getRobot().getPosition().setX(x);
                    user.getRobot().getPosition().setY(y-1);
                    server.broadcast(new Movement(user.getClientID(), x, y-1));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user.getRobot(),Orientation.TOP)){

                            
                        }
                    }
                case BOTTOM:
                    user.getRobot().setPosition(new Position(x, y + 1));
                    server.broadcast(new Movement(user.getClientID(), x, y + 1));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user,1)){

                        }
                    }
                case LEFT:
                    user.getRobot().setPosition(new Position(x - 1, y));
                    server.broadcast(new Movement(user.getClientID(), x - 1, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user,1)){

                        }
                    }
                case RIGHT:
                    user.getRobot().setPosition(new Position(x + 1, y));
                    server.broadcast(new Movement(user.getClientID(), x + 1, y));
                    if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                        server.broadcast(new Reboot(user.getClientID()));
                    }else{
                        if(movementCheck.robotForwardCheck(game, user,1)){

                        }
                    }

            }
        }*/

    }

    /*public void handle(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        MovementCheck movementCheck = new MovementCheck(game.getBoard());
        if (movementCheck.wallForwardCheck(user)){
            System.out.println("road is blocked by wall");
        } else{
        ArrayList<User> users = game.getPlayerQueue().getUsers();
        for (User otherUser : users) {
            if (movementCheck.robotForwardCheck(game, user)) {
                Move1Handler move1Handler = new Move1Handler(server,game,otherUser);
                move1Handler.handle(otherUser);
            }
        }
        if (robot.getRobotOrientation() == Orientation.LEFT) {
            Position nextPosition = new Position(position.getX() - 1, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            Position nextPosition = new Position(position.getX() + 1, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            Position nextPosition = new Position(position.getX(), position.getY() + 1);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            Position nextPosition = new Position(position.getX(), position.getY() + 1);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    }*/

