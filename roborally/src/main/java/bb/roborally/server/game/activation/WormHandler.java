package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.protocol.game_events.RebootDirection;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;


import java.io.IOException;

/**
 * @author Veronika Heckel
 */
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

    public void handle() throws IOException{

        Reboot reboot = new Reboot(user.getClientID());
        server.broadcast(reboot);

        MovementCheck movementCheck = new MovementCheck(game.getBoard());
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.TOP)){
            RebootDirection rebootDirection = new RebootDirection("top");
            server.broadcast(rebootDirection);
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.BOTTOM)){
            RebootDirection rebootDirection = new RebootDirection("bottom");
            server.broadcast(rebootDirection);
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.LEFT)){
            RebootDirection rebootDirection = new RebootDirection("left");
            server.broadcast(rebootDirection);
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.RIGHT)){
            RebootDirection rebootDirection = new RebootDirection("right");
            server.broadcast(rebootDirection);
        }


        PlayingCard playedCardIsWorm = user.getProgram().getCardInRegister(register);
        game.getVirusDeck().getVirusDeck().add(playedCardIsWorm);
        user.getProgram().resetOneRegister(register);

        PlayCard playCard = new PlayCard("Worm");
        server.broadcastOnly(playCard, user.getClientID());

        CardPlayed cardPlayed = new CardPlayed(user.getClientID(), "Worm");
        server.broadcastExcept(cardPlayed, user.getClientID());
    }
}

