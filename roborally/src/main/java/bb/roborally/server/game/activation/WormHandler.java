package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.protocol.game_events.RebootDirection;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;

import java.io.IOException;

public class WormHandler {

    Server server;
    Game game;
    User user;

    int register;

    public WormHandler(Server server, Game game, User user, int register){
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }

    public void handle(){
        Reboot reboot = new Reboot(user.getClientID());
        try {
            server.broadcast(reboot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        MovementCheck movementCheck = new MovementCheck();
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.TOP)){
            RebootDirection rebootDirection = new RebootDirection("top");
            try{
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.BOTTOM)){
            RebootDirection rebootDirection = new RebootDirection("bottom");
            try {
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.LEFT)){
            RebootDirection rebootDirection = new RebootDirection("left");
            try {
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.RIGHT)){
            RebootDirection rebootDirection = new RebootDirection("right");
            try {
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        PlayingCard playedCardisWorm = user.getProgram().getCardInRegister(register);
        playedCardisWorm.setMarked(true);
        for(int i = 0; i < user.getProgram().getProgram().length; i++){
            if(user.getProgram().getProgram()[i] == playedCardisWorm){
                user.getProgram().resetOneRegister(playedCardisWorm);
            }
        }
        game.getVirusDeck().getVirusDeck().add(playedCardisWorm);
        playedCardisWorm.setMarked(false);
    }
}

