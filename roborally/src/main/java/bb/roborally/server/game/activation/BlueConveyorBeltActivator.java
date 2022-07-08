package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;

import java.io.IOException;
import java.util.ArrayList;

public class BlueConveyorBeltActivator {

    private Server server;
    private Game game;

    public BlueConveyorBeltActivator(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public void activate() {
        Animation animation = new Animation("BlueConveyorBelt");
        try {
            server.broadcast(animation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Cell> belts = game.getBoard().getBlueConveyorBelts();
        for (User user: game.getPlayerQueue().getUsers()) {
            boolean isOnTile = false;
            int counter = 0;
            while (counter < belts.size() && !isOnTile) {
                if (belts.get(counter).getPosition().equals(user.getRobot().getPosition())) {
                    isOnTile = true;
                }
                counter += 1;
            }
            if (isOnTile) {
                // TODO: move the Robot
            }
        }
    }
}
