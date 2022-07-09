package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;

import java.io.IOException;
import java.util.ArrayList;

public class GreenConveyorBeltActivator {
    private Server server;
    private Game game;

    public GreenConveyorBeltActivator(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public void activate() {
        Animation animation = new Animation("GreenConveyorBelt");
        try {
            server.broadcast(animation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Cell> greenConveyorBelt = game.getBoard().getGreenConveyorBelts();
        for(User user: game.getPlayerQueue().getUsers()){
            int counter = 0;
            Position position = user.getRobot().getPosition();
            while(counter < greenConveyorBelt.size()){
                if(greenConveyorBelt.get(counter).getPosition().equals(position)){
                    Robot robot = user.getRobot();
                    int x = robot.getPosition().getX();
                    int y = robot.getPosition().getY();
                    Movement movement;
                    switch (greenConveyorBelt.get(counter).getTiles().get(1).getOrientations().get(0)){
                        case TOP -> robot.setPosition(new Position(x, y-1));
                        case LEFT -> robot.setPosition(new Position(x-1, y));
                        case RIGHT -> robot.setPosition(new Position(x+1, y));
                        case BOTTOM -> robot.setPosition(new Position(x, y+1));
                    }
                    movement = new Movement(user.getClientID(), robot.getPosition().getX(), robot.getPosition().getY());
                    try {
                        server.broadcast(movement);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    //TODO: Add Checkers(Pit-->Restart, EnergySpace-->EnergyCube+1, out of the valid fields)
                }
                counter += 1;
            }
        }
    }
}
