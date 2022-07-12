package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Movement;
import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.TOP;

public class Move2Handler {

        Server server;
        Game game;
        User user;

        public Move2Handler(Server server, Game game, User user) {
            this.server = server;
            this.game = game;
            this.user = user;
        }

        public void handle() throws IOException{

            Move1Handler move1Handler = new Move1Handler(server, game, user);
            for(int i = 0; i < 2; i++){
                move1Handler.handle();
            }



            /*Position position = user.getRobot().getPosition();
            int x = position.getX();
            int y = position.getY();


            Move1Handler move1Handler = new Move1Handler(server, game, user);
            move1Handler.handle();
            switch(user.getRobot().getRobotOrientation()){
                case TOP:
                    user.getRobot().setPosition(new Position(x,y-1));
                    move1Handler.handle();

                case BOTTOM:
                    user.getRobot().setPosition(new Position(x,y+1));
                    move1Handler.handle();
                case LEFT:
                    user.getRobot().setPosition(new Position(x-1,y));
                    move1Handler.handle();
                case RIGHT:
                    user.getRobot().setPosition(new Position(x-1,y));
                    move1Handler.handle();

            }*/



            /*MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
            if(movementCheck.wallForwardCheck(user)){
                server.broadcast(new Movement(user.getClientID(), x, y));
            }else{
                switch (user.getRobot().getRobotOrientation()){
                    case TOP:
                        user.getRobot().setPosition(new Position(x, y-2));
                        server.broadcast(new Movement(user.getClientID(), x, y-2));
                        if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                            server.broadcast(new Reboot(user.getClientID()));
                        }else{
                            if(movementCheck.robotForwardCheck(game, user)){

                            }
                        }
                    case BOTTOM:
                        user.getRobot().setPosition(new Position(x, y + 2));
                        server.broadcast(new Movement(user.getClientID(), x, y + 1));
                        if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                            server.broadcast(new Reboot(user.getClientID()));
                        }else{
                            if(movementCheck.robotForwardCheck(game, user)){

                            }
                        }
                    case LEFT:
                        user.getRobot().setPosition(new Position(x - 1, y));
                        server.broadcast(new Movement(user.getClientID(), x - 1, y));
                        if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                            server.broadcast(new Reboot(user.getClientID()));
                        }else{
                            if(movementCheck.robotForwardCheck(game, user)){

                            }
                        }
                    case RIGHT:
                        user.getRobot().setPosition(new Position(x + 1, y));
                        server.broadcast(new Movement(user.getClientID(), x + 1, y));
                        if(movementCheck.fallingInPit(user) || movementCheck.robotIsOffBoard(user)){
                            server.broadcast(new Reboot(user.getClientID()));
                        }else{
                            if(movementCheck.robotForwardCheck(game, user)){

                            }
                        }

                }
            }

        }*/
  /*  public void handle(User user) {
        Robot robot = user.getRobot();
        Position position = robot.getPosition();
        MovementCheck movementCheck = new MovementCheck(game.getBoard());
        if (movementCheck.wallForwardCheck(user)){
            System.out.println("road is blocked by wall");
        } else{
            ArrayList<User> users = game.getPlayerQueue().getUsers();
            for (User otherUser : users) {
                if (movementCheck.robotForwardCheck(game, otherUser)) {
                    Move2Handler move2Handler = new Move2Handler(server,game,otherUser);
                    move2Handler.handle(otherUser);
                }
            }
            if (robot.getRobotOrientation() == Orientation.LEFT) {
            Position nextPosition = new Position(position.getX() - 2, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (robot.getRobotOrientation() == Orientation.RIGHT) {
            Position nextPosition = new Position(position.getX() + 2, position.getY());
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.TOP) {
            Position nextPosition = new Position(position.getX(), position.getY() + 2);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (robot.getRobotOrientation() == Orientation.BOTTOM) {
            Position nextPosition = new Position(position.getX(), position.getY() - 2);
            robot.setPosition(nextPosition);
            Movement movement = new Movement(user.getClientID(), nextPosition.getX(), nextPosition.getY());
            try {
                server.broadcast(movement);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }*/
    }
}

